import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SidenavRoutingModule } from './sidenav-routing.module';
import { SidenavComponent } from './sidenav/sidenav.component';
import { AngularMaterialModule } from '../angular-material/angular-material.module';
import { SharedModule } from '../shared/shared.module';


@NgModule({
  declarations: [
    SidenavComponent
  ],
  imports: [
    CommonModule,
    SidenavRoutingModule,
    AngularMaterialModule,
    SharedModule,
  ]
})
export class SidenavModule { }
