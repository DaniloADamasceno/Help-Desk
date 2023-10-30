import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NavgationComponent } from './componets/navigation/navigation.component';

const routes: Routes = [
  {path: '', component: NavgationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
