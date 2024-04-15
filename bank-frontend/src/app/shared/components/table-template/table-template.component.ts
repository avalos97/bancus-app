import { Component, Input, OnChanges, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { TableColumn } from '../../interface/table-column';
import { Router } from '@angular/router';

@Component({
  selector: 'app-table-template',
  templateUrl: './table-template.component.html',
  styleUrls: ['./table-template.component.scss']
})
export class TableTemplateComponent implements OnInit, OnChanges{

  @Input() tableColumns!: TableColumn[];
  @Input() tableData!: any[];
  @Input() title!: string;
  @Input() showUpdate: boolean = true;
  @Input() showCreate: boolean = true;
  @Input() edit!: (row?: any) => void;
  @Input() create!: () => void;
  @Input() deleteRegister!: (row?: any) => void;

  displayedColumns!: string[];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private router: Router,
  ) { }
  ngOnChanges(changes: SimpleChanges): void {
    this.displayedColumns = this.tableColumns.map(column => column.name);
    this.displayedColumns.push('actions'); // Agregamos la columna de acciones
    this.dataSource = new MatTableDataSource(this.tableData);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  ngOnInit(): void {

  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

}
