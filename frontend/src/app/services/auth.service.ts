import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ILoginRequest } from '../interfaces/auth-service/login-request.interface';
import { Observable, of, tap } from 'rxjs';
import { ILoginResponse } from '../interfaces/auth-service/login-response.interface';
import { IRegisterRequest } from '../interfaces/auth-service/register-request.interface';
import { Router } from '@angular/router';
import { INameResponse } from '../interfaces/auth-service/name-response.interface';

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    private apiURL: string = '/api/auth'; 

    constructor(private readonly http: HttpClient, private readonly router: Router) {}

    login(credentials: ILoginRequest): Observable<ILoginResponse> {
        return this.http.post<ILoginResponse>(`${this.apiURL}/login`, credentials)
            .pipe(tap(response => {
                localStorage.setItem('token', response.token);
            }));
    }

    register(credentials: IRegisterRequest): Observable<void> {
        return this.http.post<void>(`${this.apiURL}/register`, credentials);
    }

    getName(): Observable<INameResponse> {
        return this.http.get<INameResponse>(`${this.apiURL}/username`);
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