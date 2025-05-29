import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-auth-login',
  standalone: false,
  templateUrl: './auth-login.component.html',
  styleUrl: './auth-login.component.scss'
})
export class AuthLoginComponent {

  constructor(private readonly router: Router) {}

  onRegister(): void {
    this.router.navigate(['/auth/register'])
  }
}
