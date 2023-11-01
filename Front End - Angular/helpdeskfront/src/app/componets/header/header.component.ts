import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',                           // O nome do seletor do componente  HTML
  templateUrl: './header.component.html',           // O arquivo HTML
  styleUrls: ['./header.component.css']             // O arquivo CSS
})
export class HeaderComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
