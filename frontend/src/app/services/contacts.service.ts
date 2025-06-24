import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IContact } from '../interfaces/contact.interface';
import { Observable } from 'rxjs';
import { IRequestContact } from '../interfaces/request-contact.interface';
import { IContactRequest } from '../interfaces/contact-request.interface';

@Injectable({
    providedIn: 'root'
})
export class ContactService {
    
    apiURL: string = '/api/contact';

    constructor(private readonly http: HttpClient) {}

    getContacts(): Observable<IContact[]> {
        return this.http.get<IContact[]>(this.apiURL);
    }

    getRequests(): Observable<IRequestContact[]> {
        return this.http.get<IRequestContact[]>(`${this.apiURL}/requests`);
    }

    sendRequest(username: string): Observable<void> {
        let request: IContactRequest = { name: username };
        
        return this.http.post<void>(`${this.apiURL}/send`, request);
    }

    acceptRequest(username: string): Observable<void> {
        let request: IContactRequest = { name: username };

        return this.http.post<void>(`${this.apiURL}/accept`, request);
    }

    refuseRequest(username: string): Observable<void> {
        let request: IContactRequest = { name: username }
        
        return this.http.post<void>(`${this.apiURL}/delete`, request)
    }
}