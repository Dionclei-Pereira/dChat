import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { IContact } from '../../interfaces/contact.interface';
import { ContactService } from '../../services/contacts.service';
import { AuthService } from '../../services/auth.service';
import { take } from 'rxjs';

@Component({
  selector: 'app-contacts',
  standalone: false,
  templateUrl: './contacts.component.html',
  styleUrl: './contacts.component.scss'
})
export class ContactsComponent implements OnInit {

  contacts: IContact[] = [];
  
  @Input({ required: true })
  name: string = '';

  @Output()
  onChat = new EventEmitter<string>();

  constructor(private contactService: ContactService) {}

  ngOnInit(): void {
    this.contactService.getContacts().pipe(take(1))
      .subscribe({
        next: response => {
          response.forEach(c => {
            c.id = this.extractContact(c.id, this.name);
            this.contacts.push(c);
          });
        }
      })
  }

  changeChat(value: string): void {
    this.onChat.emit(value);
  }

  private extractContact(id: string, name: string): string {

    let names: string[] = id.split('-');

    return names[0] === name ? names[1] : names[0];
  }
}
