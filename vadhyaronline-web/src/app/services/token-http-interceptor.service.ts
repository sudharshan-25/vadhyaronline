import {Injectable} from '@angular/core';
import {HttpInterceptor} from '@angular/common/http';
import {LoginService} from './login.service';
import {HttpRequest} from '@angular/common/http/src/request';
import {HttpHandler} from '@angular/common/http/src/backend';
import {Observable} from 'rxjs';
import {HttpEvent} from '@angular/common/http/src/response';

@Injectable({
  providedIn: 'root'
})
export class TokenHttpInterceptorService implements HttpInterceptor {

  constructor(private loginService: LoginService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const url = req.url;
    if ((url.indexOf('/user/login') !== -1 && url.indexOf('/user/register') !== -1)) {
      req = req.clone({
        setHeaders: {
          'X-Auth-Token': this.loginService.getUser().token
        }
      });
    }
    return next.handle(req);
  }
}
