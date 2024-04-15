import { Component } from '@angular/core';
import { CustomerService } from '../service/customer.service';
import { Customer } from '../interface/customer';
import { GenericResponse } from 'src/app/shared/interface/generic-response';
import { TableColumn } from 'src/app/shared/interface/table-column';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.scss']
})
export class CustomerListComponent {

  customers: Customer[] = [];

  columns: TableColumn[] = [
    { name: 'name', header: 'Nombre del Cliente' },
    { name: 'address', header: 'Direccion' },
    { name: 'phone', header: 'Telefono' },
  ];

  constructor(
    private router: Router,
    private customerService: CustomerService,
    private toast: ToastrService,
  ) { 
  }


  ngOnInit(): void {
    this.getCustomers();
  }

  getCustomers(): void {
    this.customerService.getAllCustomer().subscribe({
      next: (response: GenericResponse<Customer[]>) => {
        this.customers = response.data;
      },
      error: (erro) => {
        console.log(erro)
      }
    }
    );
  }
  
  public cratetWrapper = () => {
    this.navigateToCreatePage();
  }

  public editWrapper = (customer: Customer) => {
    this.navigateToEditPage(customer);
  }

  public deleteWrapper = (customer: Customer) => {
    this.deleteCustomer(customer.customerId);
  }

  deleteCustomer(id: number): void {
    this.customerService.deleteCustomer(id).subscribe({
      next: (response) => {
        this.getCustomers();
        this.toast.info('Cliente eliminado', 'Info');
      },
      error: (erro) => {
        console.log(erro)
      }
    });

  }

  navigateToEditPage(row: Customer): void {
    this.router.navigate(['/bancus-app/customer/edit', row.customerId]);
  }

  navigateToCreatePage(): void {
    this.router.navigate(['/bancus-app/customer/create']);
  }
}
