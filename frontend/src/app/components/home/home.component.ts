import { Component, OnDestroy, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { WebSocketService } from '../../services/websocket.service';
import { IMessage } from '../../interfaces/message.interface';

@Component({
  selector: 'app-home',
  standalone: false,
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit, OnDestroy {

  messages: IMessage[] = [];
  text: string = '';

  constructor(private auth: AuthService, private ws: WebSocketService) {}

  onLogout(): void {
    this.auth.logout();
  }

  send(): void {
    this.ws.send('/app/global', { content: this.text });
    this.text = '';
  }

  ngOnInit(): void {
    this.ws.connect('/topic/messages', (message) => {
      this.messages.push(JSON.parse(message.body));
    });
  }

  ngOnDestroy(): void {
    this.ws.disconnect();
  }
}
