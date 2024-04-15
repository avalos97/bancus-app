import { Component, OnInit } from '@angular/core';
import { JokeService } from './service/joke.service';
import { Joke } from './interface/joke';

@Component({
  selector: 'app-publicity',
  templateUrl: './publicity.component.html',
  styleUrls: ['./publicity.component.scss']
})
export class PublicityComponent implements OnInit{

  joke:Joke = {} as Joke;
  private intervalId: any;

  constructor(
    private service: JokeService
  ) { }

  ngOnInit() {
    this.getPublicity();
    this.intervalId = setInterval(() => this.getPublicity(), 10000); // 20000 milliseconds -> 10 seconds
  }

  
  getPublicity(): void {
    this.service.getPublicity().subscribe({
      next: (response) => {
        this.joke = response.data;
      },
      error: (erro) => {
        console.log(erro)
      }
    });
  }
  
  ngOnDestroy() {
    if (this.intervalId) {
      clearInterval(this.intervalId); // Clear the interval to avoid memory leaks
    }
  }
}
