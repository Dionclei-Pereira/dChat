import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../../services/auth.service';
import { take } from 'rxjs';

@Component({
  selector: 'app-auth-login',
  standalone: false,
  templateUrl: './auth-login.component.html',
  styleUrl: './auth-login.component.scss'
})
export class AuthLoginComponent {

  @ViewChild('form')
  viewForm!: NgForm;

  invalid: boolean = false;

  constructor(private readonly router: Router, private readonly auth: AuthService) {}

  onRegister(): void {
    this.router.navigate(['/auth/register'])
  }

  onSubmit(form: NgForm): void {
    this.auth.login({
      name: form.value.name,
      password: form.value.password
    }).pipe(take(1)).subscribe({
      
      next: () => this.router.navigate(['/home']),

      error: () => this.invalid = true
    })
  }
}
