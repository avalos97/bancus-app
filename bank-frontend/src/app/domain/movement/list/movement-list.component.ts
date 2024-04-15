import { Component } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { TableColumn } from 'src/app/shared/interface/table-column';
import { Movement } from '../interface/movement';
import { MovementService } from '../service/movement.service';
import { ToastrService } from 'ngx-toastr';
import { GenericResponse } from 'src/app/shared/interface/generic-response';
import { CustomerService } from '../../customer/service/customer.service';
import { Customer } from '../../customer/interface/customer';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CreateMovementComponent } from '../../customer/create/components/create-movement/create-movement.component';

@Component({
  selector: 'app-movement-list',
  templateUrl: './movement-list.component.html',
  styleUrls: ['./movement-list.component.scss']
})
export class MovementListComponent {

  form: FormGroup = new FormGroup({});

  columns: TableColumn[] = [
    { name: 'movementId', header: 'ID' },
    { name: 'numberAccount', header: 'Cuenta' },
    { name: 'amount', header: 'Monto' },
    { name: 'movementTypeDescription', header: 'Tipo de movimiento' },
    { name: 'movementDate', header: 'Fecha de movimiento' },
  ];
  movement: Movement[] = [];
  customers: Customer[] = [];
  title: string = '';

  constructor(
    private formBuilder: FormBuilder,
    private service: MovementService,
    private customerService: CustomerService,
    private dialog: MatDialog,
    private toast: ToastrService
  ) { 
    this.form = this.formBuilder.group({
      customer: ['', Validators.required],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.getMovements();
    this.getCustomers();
  }


  getMovements(): void {
    this.service.getAllMovements().subscribe({
      next: (response: GenericResponse<Movement[]>) => {
        //ordenar por idMovement, de mayor a menor
        this.movement = response.data.sort((a, b) => b.movementId - a.movementId);
        this.title = 'Movimientos de todas las cuentas';
      },
      error: (erro) => {
        console.log(erro)
      }
    }
    );
  }

  getMovementsByCriteria(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const customerId = this.form.get('customer')?.value.customerId;
    const startDate = this.form.get('startDate')?.value;
    const endDate = this.form.get('endDate')?.value;
    this.service.getMovementsByCriteria(customerId, startDate, endDate).subscribe({
      next: (response: GenericResponse<Movement[]>) => {
        this.movement = response.data;
        this.title = `Movimientos del cliente ${this.form.get('customer')?.value.name}`;
      },
      error: (erro) => {
        console.log(erro)
      }
    }
    );
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

  public deleteWrapper = (movement: Movement) => {
    this.deleteMovement(movement.movementId);
  }

  deleteMovement(id: number): void {
    this.service.deleteMovement(id).subscribe({
      next: (response) => {
        this.getMovements();
        this.toast.info('Movimiento eliminado', 'Info');
      },
      error: (erro) => {
        console.log(erro)
      }
    });
  }

  clearForm(): void {
    this.form.reset();
    this.getMovements();
  }

}
