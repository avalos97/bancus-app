import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { AngularMaterialModule } from 'src/app/angular-material/angular-material.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { MovementModule } from '../domain/movement/movement.module';
import { HomeComponent } from './dash/home.component';
import { HomeRoutingModule } from './home-routing.module';


@NgModule({
  declarations: [HomeComponent],
  imports: [
    CommonModule,
    HomeRoutingModule,
    AngularMaterialModule,
    SharedModule,
    MovementModule
  ],
})
export class HomeModule { }
