import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { IContact } from '../../interfaces/contact.interface';
import { ContactService } from '../../services/contacts.service';

@Component({
  selector: 'app-contacts',
  standalone: false,
  templateUrl: './contacts.component.html',
  styleUrl: './contacts.component.scss'
})
export class ContactsComponent implements OnInit {

  Contacts: IContact[] = [];
  
  @Output()
  onChat = new EventEmitter<string>();

  constructor(private contact: ContactService) {}

  ngOnInit(): void {
      
  }

  changeChat(value: string): void {
    this.onChat.emit(value);
  }
}
