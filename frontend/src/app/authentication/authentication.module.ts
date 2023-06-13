import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthenticationRoutingModule } from './authentication-routing.module';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    LoginComponent,
    SignupComponent,

    // Material UI Components
    

  ],
  imports: [
    CommonModule,
    AuthenticationRoutingModule,
    FormsModule,

    // material ui modules
    MatCardModule,
    MatIconModule,
    MatFormFieldModule,
    MatCheckboxModule

  ],
  exports: [
    SignupComponent,
    LoginComponent  
  ]
})
export class AuthenticationModule { }
