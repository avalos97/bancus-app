import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { Movement } from 'src/app/domain/movement/interface/movement';
import { MovementType } from 'src/app/domain/movement/interface/movement-type';
import { MovementService } from 'src/app/domain/movement/service/movement.service';
import { GenericResponse } from 'src/app/shared/interface/generic-response';
import { Account } from '../../../interface/account';

const DEPOSITO = "DEPOSITO";
const CREDITO = "CREDITO";

@Component({
  selector: 'app-create-movement',
  templateUrl: './create-movement.component.html',
  styleUrls: ['./create-movement.component.scss']
})
export class CreateMovementComponent {

  form: FormGroup = this.fb.group({
    movementId: [null],
    amount: ['', Validators.required],
    accountId: ['', Validators.required],
    movementTypeId: ['', Validators.required],
  });

  movement!: Movement;
  accounts: Account[] = [];

  movementTypes:MovementType[] = [
    {movementTypeId:1, description:DEPOSITO},
    {movementTypeId:2, description:CREDITO},
  ];

  constructor(
    private service: MovementService,
    private dialogRef: MatDialogRef<CreateMovementComponent>,
    @Inject(MAT_DIALOG_DATA) private data: { movement: Movement, accounts: Account[]},
    private toast: ToastrService,
    private fb: FormBuilder,
  ) {
  }

  ngOnInit(): void {
    this.setMovement();
    this.accounts = this.data.accounts;
  }

  setMovement() {
    this.movement = this.data.movement;
    if (this.movement?.movementId) {
      this.form.reset({
        ...this.movement,
      });
    }
  }

  accept(): void {
    const formValue = { ...this.form.value };
    if (this.form.invalid) {//validamos que no haya error en los campos
      this.form.markAllAsTouched();//simulamos haber tocado los campos para mostrar los errores
      return;
    }
    this.movement = formValue;
    (this.movement.movementId) ? this.updateMovement() : this.saveMovement();
  }

  saveMovement(): void {
    this.service.createMovement(this.movement).subscribe({
      next: (response: GenericResponse<Movement>) => {
        this.toast.success('Movement created successfully', 'Success');
        this.closeModal(true);
      },
      error: (error) => {
        this.toast.error(error.error.message, 'Error');
      }
    }
    );
  }

  updateMovement(): void {
    this.service.updateMovement(this.data.movement.movementId,this.movement).subscribe({
      next: (response: GenericResponse<Movement>) => {
        this.toast.success('Movement updated successfully', 'Success');
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
