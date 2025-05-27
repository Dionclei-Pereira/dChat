import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { AuthLoginComponent } from './components/auth/auth-login/auth-login.component';
import { AuthGuard } from './guards/auth.guard';
import { TokenGuard } from './guards/token.guard';

const routes: Routes = [
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'auth', canActivate: [TokenGuard], children: [
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
