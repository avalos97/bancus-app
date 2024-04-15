import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CustomerService } from '../service/customer.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Customer } from '../interface/customer';
import { GenericResponse } from 'src/app/shared/interface/generic-response';
import { Account } from '../interface/account';

@Component({
  selector: 'app-create-customer',
  templateUrl: './create-customer.component.html',
  styleUrls: ['./create-customer.component.scss']
})
export class CreateCustomerComponent {

  id:string | null = null;

  form: FormGroup = this.fb.group({
    customerId: [null],
    name: ['', Validators.required],
    address: ['', Validators.required],
    phone: ['', Validators.required],
    accounts:[]
  });

  accounts: Account[] = [];

  constructor(
    private customerService: CustomerService,
    private route: ActivatedRoute,
    private router: Router,
    private toast: ToastrService,
    private fb: FormBuilder,
  ) {

    const routeParams = this.route.snapshot.paramMap;
    this.id = routeParams.get('id') ? routeParams.get('id') : null;
  }

  ngOnInit(): void {
    this.loadCustomer();
  }

  loadCustomer() {
    if(this.id){
      this.customerService.getCustomerById(+this.id).subscribe({
        next: (response: GenericResponse<Customer>) => {
          this.form.patchValue(response.data);
          this.accounts = response.data.accounts;
        },
        error: (erro) => {
          console.log(erro)
        }
      });
    }
    
  }

  accept(): void {
    const formValue = { ...this.form.value };
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    (this.id) ? this.updateCustomer(formValue) : this.saveCustomer(formValue);
  }

  saveCustomer(customer: Customer): void {
    this.customerService.createCustomer(customer).subscribe({
      next: (response: any) => {
        let id = response.data.customerId;
        this.router.navigate(['/bancus-app/customer/edit', id]);
        this.toast.success('Customer created successfully', 'Success');
      },
      error: (erro) => {
        console.log(erro)
      }
    });
  }

  updateCustomer(customer: Customer): void {
    this.customerService.updateCustomer(+this.id!, customer).subscribe({
      next: (response: any) => {
        this.router.navigate(['/bancus-app/customer']);
        this.toast.success('Customer updated successfully', 'Success');
      },
      error: (erro) => {
        console.log(erro)
      }
    });
  }

  ErrorRequiredMsg(field: string): string {
    const errors = this.form.get(field)?.errors;
    const touch = this.form.get(field)?.touched;

    if ((errors?.['required']) && touch) {
      return `Campo requerido`
    }
    return '';
  }

  public updateData = () => {
    this.loadCustomer();
  }

  cancel(): void {
    window.history.back();
  }
}
