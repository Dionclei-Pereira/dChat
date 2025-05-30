import { Component, ViewChild } from '@angular/core';
import { NgForm, NgModel } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../../services/auth.service';
import { take } from 'rxjs';

@Component({
  selector: 'app-auth-register',
  standalone: false,
  templateUrl: './auth-register.component.html',
  styleUrl: './auth-register.component.scss'
})
export class AuthRegisterComponent {

  @ViewChild('name')
  name!: NgModel;

  @ViewChild('pass')
  pass!: NgModel;

  @ViewChild('confirm')
  confirm!: NgModel;

  constructor(private readonly auth: AuthService  , private readonly router: Router) { }

  onLogin(): void {
    this.router.navigate(['/auth/login']);
  }

  onSubmit(form: NgForm) {
    this.auth.register({ name: form.value.name, password: form.value.password }).pipe(take(1))
      .subscribe({
        next: () => this.onLogin(),
        error: () => form.reset()
      })
  }

  getErrorMessage(): string {
    if (this.name && this.name.invalid) {
      if (this.name.hasError('required')) return '- Name is required';
      if (this.name.hasError('minlength')) return '- Name must be at least 3 characters';
    }

    if (this.pass && this.pass.invalid) {
      if (this.pass.hasError('required')) return '- Password is required';
      if (this.pass.hasError('minlength')) return '- Password must be at least 6 characters';
      if (this.pass.hasError('maxlength')) return '- Password must be at most 16 characters';
    }

    if (this.confirm && this.confirm.invalid) {
      if (this.confirm.hasError('required')) return '- Confirm Password is required';
    }

    if (this.confirm && (this.confirm.value !== this.pass.value)) {
      return '- Passwords are different';
    }

    return '';
  }
}
