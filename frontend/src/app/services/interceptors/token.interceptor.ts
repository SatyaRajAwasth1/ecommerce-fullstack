import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/authentication/authentication.service';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(
    private authService: AuthService
    ) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if(request.headers.get('requiredToken') !== 'false'){
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${this.authService.getToken()}`
        }
      });
    }
  request.headers.delete('requiredToken');
  return next.handle(request);
  }


}
