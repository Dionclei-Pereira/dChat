import { Component, OnDestroy, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { WebSocketService } from '../../services/websocket.service';
import { IMessage } from '../../interfaces/message.interface';
import { take } from 'rxjs';

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
  chat: string = 'Global';
  name: string = '';

  constructor(private auth: AuthService, private ws: WebSocketService) {}

  setPage(value: number): void {
    this.page = value;
  }

  changeChat(value: string): void {
    this.page = 0;
    this.chat = value;
    this.messages = [];
  }

  onLogout(): void {
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
        console.log(response.name)
      })
    this.ws.connect('/topic/messages', (message) => {
      this.messages.push(JSON.parse(message.body));
    });
  }

  ngOnDestroy(): void {
    this.ws.disconnect();
  }
}
