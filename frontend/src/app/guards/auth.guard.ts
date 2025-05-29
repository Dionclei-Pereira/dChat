import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { catchError, map, Observable, of, take } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate {

    constructor(private auth: AuthService, private router: Router) { }

    canActivate(): Observable<boolean> {
        return this.auth.isValid().pipe(
            map(isValid => {
                if (isValid) {
                    return true;
                } else {
                    this.router.navigate(['/auth']);
                    return false;
                }
            }),
            catchError(() => {
                this.router.navigate(['/auth']);
                return of(false);
            })
        );
    }
}
