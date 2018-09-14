import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {LoginService} from './login.service';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TokenHttpInterceptorService implements HttpInterceptor {

  constructor(private loginService: LoginService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const url = req.url;
    if ((url.indexOf('/user/login') === -1 && url.indexOf('/user/register') === -1)) {
      req = req.clone({
        setHeaders: {
          'X-Auth-Token': this.loginService.getUser().loginToken
        }
      });
    }
    return next.handle(req);
  }
}
