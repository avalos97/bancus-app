import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MovementRoutingModule } from './movement-routing.module';
import { MovementListComponent } from './list/movement-list.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { AngularMaterialModule } from 'src/app/angular-material/angular-material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [MovementListComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MovementRoutingModule,
    AngularMaterialModule,
    SharedModule
  ],
  exports: [MovementListComponent],

})
export class MovementModule { }
