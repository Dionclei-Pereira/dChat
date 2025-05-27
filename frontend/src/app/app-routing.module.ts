import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { AuthLoginComponent } from './components/auth/auth-login/auth-login.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'auth', children: [
    { path: 'login', component: AuthLoginComponent },
    { path: '', redirectTo: 'login', pathMatch: 'full' }
  ]},
  { path: '', redirectTo: 'auth/login', pathMatch: 'full' },
  { path: '**', redirectTo: 'auth/login' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
