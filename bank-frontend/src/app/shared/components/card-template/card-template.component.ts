import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-card-template',
  templateUrl: './card-template.component.html',
  styleUrls: ['./card-template.component.scss']
})
export class CardTemplateComponent implements OnChanges {
  ngOnChanges(changes: SimpleChanges): void {

  }

  @Input() title: string = '';
  @Input() heigthFoot: string = '';
  @Input() heigthHeader: string = '';
  @Input() mostrarHeader: string = '';
  @Input() isOverflow: string = '';
}
