import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

   BASE_URL = 'http://localhost:8080'

  constructor(private httpClient: HttpClient) { 

  }

  // call api / server to generate token
  doLogin(credentials: any){
    return this.httpClient.post(
      `${this.BASE_URL}/api/auth/login`, credentials )
  }

  //   login user 
  loginUser(token: string){
    localStorage.setItem("ecommerce_token",token);
    return true;
  }

  // check whether user is logged in or not is logged in if token on the local storage
  isUserLoggedIn(){
    let token = localStorage.getItem("ecommerce_token");
      if (token === undefined || token === '' || token == null) {
        return false;
      }
      return true;
    }
  
  getToken(){
    return localStorage.getItem("ecommerce_token");
  }

  //  remove token from local storage to logout user
  logout(){
    localStorage.removeItem('ecommerce_token');
    return true;
  }

  signup(credentials:any){
    return this.httpClient.post(
      `${this.BASE_URL}/api/auth/register`, credentials )
  }

}
