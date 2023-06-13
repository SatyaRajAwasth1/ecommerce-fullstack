import { Component, OnInit, OnDestroy } from '@angular/core';
import { AuthService } from '../authentication.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-signup',
  templateUrl:'./signup.component.html' ,
  styles: [
  ]
})
export class SignupComponent implements OnInit, OnDestroy{

  _subscribption: Subscription | undefined;

  user= {
    userName: '',
    password: '',
  }
  confirm_password =''

  constructor(private authService: AuthService, private router: Router){
  }
  ngOnDestroy(): void {
    this._subscribption?.unsubscribe();
  }
  ngOnInit(): void {
    if(this.authService.isUserLoggedIn())
    this.router.navigate(['home'])
  }

  onSubmit(){
    console.log("form submitted.")
    if(
      (this.user.userName != '' && this.user.password != '')
      && ( this.user.userName != null && this.user.password != null)
      && (this.user.password === this.confirm_password)
      ){
      this._subscribption =  this.authService.signup(this.user)
        .subscribe({
          next: (response:any) => {
                console.log('User registered successfully');
                this.router.navigate(['auth/login',  { registrationSuccess: true  }]);
          },
          error: (error) => alert("Couldn't register. try again.")
        })
    }
    else{
      alert("Something wrong in your input")
    }
  }

}
