import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { catchError, map, Observable, of, take } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable({
    providedIn: 'root'
})
export class TokenGuard implements CanActivate {

    constructor(private auth: AuthService, private router: Router) { }

    canActivate(): boolean {
        const token = this.auth.getToken();
        if (token) {
            this.router.navigate(['/home'])
            return false;
        }
        return true;
    }
}
