import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IMessage } from '../interfaces/message.interface';

@Injectable({
    providedIn: 'root'
})
export class HistoryService {

    private apiURL = '/api/history';

    constructor(private http: HttpClient) {}

    getGlobalHistory(): Observable<IMessage[]> {
        return this.http.get<IMessage[]>(`${this.apiURL}/global`);
    }
}