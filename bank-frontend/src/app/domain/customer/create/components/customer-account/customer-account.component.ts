import { Component, Input, Output } from '@angular/core';
import { Account } from '../../../interface/account';
import { ToastrService } from 'ngx-toastr';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { CreateAcountComponent } from '../create-account/create-acount.component';
import { Router } from '@angular/router';
import { CustomerService } from '../../../service/customer.service';
import { CreateMovementComponent } from '../create-movement/create-movement.component';

@Component({
  selector: 'app-customer-account',
  templateUrl: './customer-account.component.html',
  styleUrls: ['./customer-account.component.scss']
})
export class CustomerAccountComponent {

  @Input() customerAccounts: Account[] = [];
  @Input() customerId:number | undefined;
  @Input() updateCustomerAccount!: () => void;

  dialogRef!: MatDialogRef<CreateAcountComponent, any>;
  dialogRefMovement!: MatDialogRef<CreateMovementComponent, any>;

  constructor(
    private dialog: MatDialog,
    private toast: ToastrService,
    private router: Router,
    private customerService: CustomerService,
  ) { }
  

  openDialog(account?: Account): void {
    this.dialogRef = this.dialog.open(CreateAcountComponent, {
      maxWidth: '94vw',
      width: '800px',
      height: '55%',
      data: { account: account, customerId: this.customerId }
    });

    this.dialogRef.afterClosed().subscribe(estado => {
      if (estado) this.updateCustomerAccount();
    });
  }

  openDialogMovements(): void {
    this.dialogRefMovement = this.dialog.open(CreateMovementComponent, {
      maxWidth: '94vw',
      width: '850px',
      height: '85%',
      data: { movement:null, accounts: this.customerAccounts }
    });

    this.dialogRefMovement.afterClosed().subscribe(estado => {
      if (estado) this.updateCustomerAccount();
    });
  }


  deleteAccount(accountId: number): void {
    this.customerService.deleteCustomerAccount(this.customerId!, accountId).subscribe({
      next: (response) => {
        this.updateCustomerAccount();
        this.toast.info('Cuenta eliminada', 'Info');
      },
      error: (erro) => {
        console.log(erro)
      }
    });
  }
}
