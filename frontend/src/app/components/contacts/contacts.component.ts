import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-contacts',
  standalone: false,
  templateUrl: './contacts.component.html',
  styleUrl: './contacts.component.scss'
})
export class ContactsComponent {

  @Output()
  onChat = new EventEmitter<string>();

  changeChat(value: string): void {
    this.onChat.emit(value);
  }
}
