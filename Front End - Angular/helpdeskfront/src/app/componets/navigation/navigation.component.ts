import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';


@Component({
  selector: 'app-navigation',                       // O nome do seletor do componente  HTML       
  templateUrl: './navigation.component.html',       // O arquivo HTML
  styleUrls: ['./navigation.component.css']         // O arquivo CSS
})
export class NavgationComponent implements OnInit {


  constructor(private router: Router) { }

  ngOnInit(): void {

    this.router.navigate(['/home']);                // Navegação para a página home

  }

}
