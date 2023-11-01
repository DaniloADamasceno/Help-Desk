import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { NavigationComponent } from './componets/navigation/navigation.component'; // Navegação para a página home -> Página Pai
import { HomeComponent } from './componets/home/home.component';                   // Navegação para a página home -> Página Filha
import { TechnicianListComponent } from './componets/technician/technician-list/technician-list.component';


const routes: Routes = [
  {
    path: '', component: NavigationComponent, children: [
    {path: 'home', component: HomeComponent},  // Navegação para a página HOME -> Página Filha 
    {path: 'technician', component: TechnicianListComponent},  // Navegação para a página TÉCNICO LIST -> Página Filha
  ]
}
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
