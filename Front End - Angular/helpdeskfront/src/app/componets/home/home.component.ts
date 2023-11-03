import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',                       // O nome do seletor do componente  HTML
  templateUrl: './home.component.html',       // O arquivo HTML
  styleUrls: ['./home.component.css']         // O arquivo CSS
})
export class HomeComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}