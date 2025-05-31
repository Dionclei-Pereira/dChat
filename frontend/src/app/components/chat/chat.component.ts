import { AfterViewInit, Component, EventEmitter, Input, Output } from '@angular/core';
import { IMessage } from '../../interfaces/message.interface';

@Component({
  selector: 'app-chat',
  standalone: false,
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.scss'
})
export class ChatComponent implements AfterViewInit {

  @Input({ required: true })
  messages: IMessage[] = []

  @Input({ required: true })
  user: string = '';

  @Input({ required: true })
  chat: string | undefined = '';

  @Output()
  messageEmitter = new EventEmitter<string>();

  text: string = '';

  onSend(): void {
    this.messageEmitter.emit(this.text);
    this.text = '';
    setTimeout(() => {
      this.scrollToBottom();
    }, 100);
  }

  ngAfterViewInit(): void {
    this.scrollToBottom();
  }

  scrollToBottom(): void {
    const div = document.querySelector('.chating');
    if (div) {
      div.scrollTop = div.scrollHeight;
    }
  }
}
