import { Injectable } from '@angular/core';
import { AuthService } from '../authentication/authentication.service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private BASE_URL = 'http://localhost:8080/api'

  constructor(
    private http: HttpClient,
    private authService: AuthService
    ) { 
    
    }

  getPaymentModes():string[]{
    
    this.http.get
    return [];
  }
}
