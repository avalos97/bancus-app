import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardTemplateComponent } from './components/card-template/card-template.component';
import { AngularMaterialModule } from '../angular-material/angular-material.module';
import { TableTemplateComponent } from './components/table-template/table-template.component';
import { PublicityComponent } from './components/publicity/publicity.component';



@NgModule({
  declarations: [
    CardTemplateComponent,
    TableTemplateComponent,
    PublicityComponent
  ],
  imports: [
    CommonModule,
    AngularMaterialModule
  ],
  exports: [CardTemplateComponent, TableTemplateComponent,PublicityComponent],
})
export class SharedModule { }
