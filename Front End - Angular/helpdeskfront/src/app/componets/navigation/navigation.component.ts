import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navigation',                       // O nome do seletor do componente  HTML       
  templateUrl: './navigation.component.html',       // O arquivo HTML
  styleUrls: ['./navigation.component.css']         // O arquivo CSS
})
export class NavgationComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
