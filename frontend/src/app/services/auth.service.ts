import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginRequest } from '../interfaces/auth-service/login-request.interface';
import { Observable, of, tap } from 'rxjs';
import { LoginResponse } from '../interfaces/auth-service/login-response.interface';
import { RegisterRequest } from '../interfaces/auth-service/register-request.interface';
import { Router } from '@angular/router';

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    private apiURL: string = '/api/auth'; 

    constructor(private readonly http: HttpClient, private readonly router: Router) {}

    login(credentials: LoginRequest): Observable<LoginResponse> {
        return this.http.post<LoginResponse>(`${this.apiURL}/login`, credentials)
            .pipe(tap(response => {
                localStorage.setItem('token', response.token);
            }));
    }

    register(credentials: RegisterRequest): Observable<void> {
        return this.http.post<void>(`${this.apiURL}/register`, credentials);
    }

    getToken(): string | null {
        return localStorage.getItem('token');
    }

    logout(): void {
        localStorage.removeItem('token');
        this.router.navigate(['/auth'])
    }

    isValid(): Observable<boolean> {
        const token = this.getToken();
        if (!token) return of(false);
        return this.http.get<boolean>(`${this.apiURL}/isvalid`, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        }).pipe(tap(isValid => {
            if (!isValid) this.logout();
        }))
    }
}