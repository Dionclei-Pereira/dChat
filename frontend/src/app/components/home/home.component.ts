import { Component, OnDestroy, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { WebSocketService } from '../../services/websocket.service';
import { IMessage } from '../../interfaces/message.interface';
import { take } from 'rxjs';
import { HistoryService } from '../../services/history.service';

@Component({
  selector: 'app-home',
  standalone: false,
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit, OnDestroy {

  messages: IMessage[] = [];
  text: string = '';
  page: number = 0;
  chat: string = '';
  name: string = '';
  load: boolean = false;

  constructor(private auth: AuthService, private ws: WebSocketService, private history: HistoryService) {
    this.changeChat('Global');
  }

  setPage(value: number): void {
    this.page = value;
  }

  changeChat(value: string): void {
    this.page = 0;
    this.chat = value;
    this.messages = [];
    this.load = false;

    if (value === 'Global') {
      this.history.getGlobalHistory().pipe(take(1))
        .subscribe({
          next: (response) => response.forEach(message => this.messages.push(message)),
          error: () => {},
          complete: () => { this.load = true; }
        });
    }
  }

  onLogout(): void {
    this.ws.disconnect();
    this.auth.logout();
  }

  onSend(text: string) {
    this.text = text;
    this.send();
  }

  send(): void {
    this.ws.send('/app/global', { content: this.text });
    this.text = '';
  }

  ngOnInit(): void {
    this.auth.getName().pipe(take(1))
      .subscribe(response => {
        this.name = response.name;
      })
    this.ws.connect('/topic/messages', (message) => {
      this.messages.push(JSON.parse(message.body));
    });
  }

  ngOnDestroy(): void {
    this.ws.disconnect();
  }
}
