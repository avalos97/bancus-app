import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Account } from '../../../interface/account';
import { CustomerService } from '../../../service/customer.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { GenericResponse } from 'src/app/shared/interface/generic-response';

@Component({
  selector: 'app-create-acount',
  templateUrl: './create-acount.component.html',
  styleUrls: ['./create-acount.component.scss']
})
export class CreateAcountComponent {

  form: FormGroup = this.fb.group({
    accountId: [null],
    number: ['', Validators.required],
    balance: [0.0],
  });

  account!: Account;


  constructor(
    private service: CustomerService,
    private dialogRef: MatDialogRef<CreateAcountComponent>,
    @Inject(MAT_DIALOG_DATA) private data: {account: Account, customerId: number},
    private toast: ToastrService,
    private fb: FormBuilder,
  ) {
  }

  ngOnInit(): void {
    this.setAccount();
  }

  setAccount() {
    this.account = this.data.account;
    if (this.account?.accountId) {
      this.form.reset({
        ...this.account,
      });
    }
  }

  accept(): void {
    const formValue = { ...this.form.value };
    if (this.form.invalid) {//validamos que no haya error en los campos
      this.form.markAllAsTouched();//simulamos haber tocado los campos para mostrar los errores
      return;
    }
    this.account = formValue;
    (this.account.accountId) ? this.updateCustomerAccount() : this.saveCustomerAccount();
  }

  saveCustomerAccount(): void {
    this.service.saveCustomerAccount(this.data.customerId, this.account).subscribe({
      next: (response: GenericResponse<Account[]>) => {
        this.toast.success('account created successfully', 'Success');
        this.closeModal(true);
      },
      error: (error) => {
        this.toast.error(error.error.message, 'Error');
      }
    }
    );
  }

  updateCustomerAccount(): void {
    this.service.updateCustomerAccount(this.data.customerId,this.account.accountId, this.account).subscribe({
      next: (response: GenericResponse<Account[]>) => {
        this.toast.success('Product updated successfully', 'Success');
        this.closeModal(true);
      },
      error: (error) => {
        this.toast.error(error.error.message, 'Error');
      }
    }
    );
  }

  closeModal(status?: boolean) {
    this.form.reset();
    this.dialogRef.close(status);
  }

  ErrorRequiredMsg(field: string): string {
    const errors = this.form.get(field)?.errors;
    const touch = this.form.get(field)?.touched;

    if ((errors?.['required']) && touch) {
      return `Campo requerido`
    }
    return '';
  }

}
