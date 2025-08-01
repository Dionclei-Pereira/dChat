import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthLoginComponent } from './auth/auth-login/auth-login.component';
import { CardComponent } from './card/card.component';
import { HomeComponent } from './home/home.component';
import { FormsModule } from '@angular/forms';
import { AuthRegisterComponent } from './auth/auth-register/auth-register.component';
import { ContactsComponent } from './contacts/contacts.component';
import { ChatComponent } from './chat/chat.component';
import { ProfileComponent } from './profile/profile.component';


@NgModule({
  declarations: [
    AuthLoginComponent,
    HomeComponent,
    CardComponent,
    AuthRegisterComponent,
    ContactsComponent,
    ChatComponent,
    ProfileComponent
  ],
  imports: [
    CommonModule,
    FormsModule
  ],
  exports: [
    AuthLoginComponent,
    HomeComponent,
    CardComponent
  ]
})
export class ComponentsModule { }
