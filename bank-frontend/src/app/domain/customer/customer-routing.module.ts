import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerListComponent } from './list/customer-list.component';
import { CreateCustomerComponent } from './create/create-customer.component';

const routes: Routes = [
  { path: '', component: CustomerListComponent },
  { path: 'create', component: CreateCustomerComponent },
  { path: 'edit/:id', component: CreateCustomerComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
