import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { catchError, map, Observable, of } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate {

    constructor(private auth: AuthService, private router: Router) { }

    canActivate(): Observable<boolean> {
        const token = this.auth.getToken();
        if (!token) {
            this.auth.logout();
            return of(false);
        }
        return this.auth.isValid().pipe(
            map(isValid => {
                if (isValid) {
                    return true;
                } else {
                    this.auth.logout();
                    return false;
                }
            }),
            catchError(() => {
                this.auth.logout();
                return of(false);
            })
        );
    }
}
