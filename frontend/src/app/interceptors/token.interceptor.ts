import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { AuthService } from '../services/auth.service';

@Injectable({
    providedIn: 'root'
})
export class NameInterceptor implements HttpInterceptor {

    constructor(private readonly auth: AuthService) {}

    intercept(req: HttpRequest<any>, next: HttpHandler) {
        const token = this.auth.getToken();
        if (token) {
            const clone = req.clone({
                setHeaders: {
                    'Authorization': token
                }
            })
            return next.handle(clone);
        }
        return next.handle(req);
    }
}