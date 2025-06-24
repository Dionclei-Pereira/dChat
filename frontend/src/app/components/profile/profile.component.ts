import { Component, Input, OnInit } from '@angular/core';
import { IContactRequest } from '../../interfaces/contact-request.interface';
import { ContactService } from '../../services/contacts.service';
import { map, take } from 'rxjs';

@Component({
  selector: 'app-profile',
  standalone: false,
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss'
})
export class ProfileComponent implements OnInit {

  @Input({ required: true })
  profile: string = '';

  requests: string[] | null = null;

  constructor(private contact: ContactService) { }

  acceptRequest(name: string): void {
    this.contact.acceptRequest(name).pipe(take(1)).subscribe();
    this.requests = this.requests!.filter(r => r != name);
  }

  refuseRequest(name: string): void {
    this.contact.refuseRequest(name).pipe(take(1)).subscribe();
    this.requests = this.requests!.filter(r => r != name);
  }

  ngOnInit(): void {
    this.contact.getRequests().pipe(take(1))
      .subscribe({
        next: response => {
          this.requests = response.map(r => this.extractContact(r.from, this.profile));
        },
        error: () => this.requests = []
      });
  }

  private extractContact(id: string, name: string): string {

    let names: string[] = id.split('-');

    return names[0] === name ? names[1] : names[0];
  }
}
