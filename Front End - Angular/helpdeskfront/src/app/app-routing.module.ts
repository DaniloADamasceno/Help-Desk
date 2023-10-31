import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { NavgationComponent } from './componets/navigation/navigation.component';
import { HomeComponent } from './componets/home/home.component';

const routes: Routes = [
  {path: '', component: NavgationComponent, children: [
    {path: 'home', component: HomeComponent}  // Navegação para a página home -> Página Filha 
  ]
}
];


const routes: Routes = [];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
