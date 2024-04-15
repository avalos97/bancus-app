import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss']
})
export class SidenavComponent implements OnInit {

  isOpen: boolean = true;
  username: string = 'Usuario Test';
  constructor(
    private breakpointObserver: BreakpointObserver) { }
  ngOnInit(): void {
  }

  opened() {
    this.isOpen = !this.isOpen;
  }

  cards = this.breakpointObserver.observe(Breakpoints.Handset).pipe(
    map(({ matches }) => {
      if (matches) {
        return [

          { title: 'Card 1', cols: 2, rows: 1, generalCols: 2 },
          { title: 'Card 2', cols: 2, rows: 1, generalCols: 2 },
        ];
      }

      return [
        { title: 'Card 1', cols: 3, rows: 2, generalCols: 3 },
        { title: 'Card 2', cols: 3, rows: 6, generalCols: 3 },
      ];
    })
  );

}
