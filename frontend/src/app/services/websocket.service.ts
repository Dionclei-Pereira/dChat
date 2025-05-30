import { Injectable } from '@angular/core';
import { Client, IMessage, StompSubscription } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class WebSocketService {
  private client: Client;
  private subs: StompSubscription | undefined;
  private readonly apiURL: string = '/api';

  constructor(private auth: AuthService) {
    this.client = new Client({
      webSocketFactory: () => new SockJS(`${this.apiURL}/ws`),
      reconnectDelay: 5000,
      connectHeaders: {
        Authorization: 'Bearer ' + this.auth.getToken(),
      }
    });
  }

  connect(topic: string, callback: (message: IMessage) => void): void {
    this.client.onConnect = () => {
      this.subs = this.client.subscribe(topic, callback);
    };

    this.client.activate();
  }

  disconnect(): void {
    if (this.subs) {
      this.subs.unsubscribe();
    }
    this.client.deactivate();
  }

  send(destination: string, body: any) {
    this.client.publish({
      destination,
      body: JSON.stringify(body),
    });
  }
}