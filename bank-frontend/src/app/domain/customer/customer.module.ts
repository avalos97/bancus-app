import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { AngularMaterialModule } from 'src/app/angular-material/angular-material.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { CreateCustomerComponent } from './create/create-customer.component';
import { CustomerRoutingModule } from './customer-routing.module';
import { CustomerListComponent } from './list/customer-list.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CustomerAccountComponent } from './create/components/customer-account/customer-account.component';
import { CreateAcountComponent } from './create/components/create-account/create-acount.component';
import { CreateMovementComponent } from './create/components/create-movement/create-movement.component';


@NgModule({
  declarations: [
    CustomerListComponent,
    CreateCustomerComponent,
    CustomerAccountComponent,
    CreateAcountComponent,
    CreateMovementComponent
  ],
  imports: [
    CommonModule,
    CustomerRoutingModule,
    SharedModule,
    AngularMaterialModule,
    FormsModule,
    ReactiveFormsModule,
  ]
})
export class CustomerModule { }
