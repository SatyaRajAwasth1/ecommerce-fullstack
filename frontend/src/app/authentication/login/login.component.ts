import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../authentication.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styles: [
  ]
})
export class LoginComponent implements OnInit {

  disableSubmission = false;
  userLoggedIn = this.authService.isUserLoggedIn();
  registrationSuccess: boolean = false;

  user= {
    userName: '',
    password: '',
  }

  constructor(
    private authService: AuthService, 
    private snackBar: MatSnackBar, 
    private route: ActivatedRoute,
    private router: Router
    ){}
  ngOnInit(): void {

    if(this.userLoggedIn){
      this.router.navigate(['/home'])
    }

    this.route.paramMap.subscribe(params => {
      this.registrationSuccess = params.get('registrationSuccess') === 'true';
    });
    console.log(this.registrationSuccess+" checking status")
    if (this.registrationSuccess) {
      this.showSnackbar('Registered successfully',);
    }

    var l = true;
    this.route.paramMap.subscribe(params => {
      l = params.get('l') !== 'f';
    });
    if(!l){
      this.showSnackbar('Login to proceed!',);
    }

  }
  
  showSnackbar(message: string) {
    this.snackBar.open(message, 'Close', {
      duration: 3000, verticalPosition:'top',
      horizontalPosition:'center'
    });
  }

  updateFormSubmission(){
    if(
      (this.user.userName != '' && this.user.password != '')
      && ( this.user.userName != null && this.user.password != null)
      ){
      this.disableSubmission = false
    }
  }

  onSubmit(){
    console.log("form submitted.")
    if(
      (this.user.userName != '' && this.user.password != '')
      && ( this.user.userName != null && this.user.password != null)
      ){
      this.authService.doLogin(this.user)
        .subscribe({
          next: (response:any) => {
            var token = response.token;
            this.authService.loginUser(token);
            // redirect user to continue
            this.router.navigateByUrl('/cart');
          },
          error: (error) =>{
            console.error(error)
            this.snackBar.open("Couldn't login, try again.","Ok",{duration:2000})
          } 
        })
    }
    else{
      console.log("not verified")
      this.snackBar.open("please! follow email or password pattern.","Ok",{duration:2000})
    }
  }


}
