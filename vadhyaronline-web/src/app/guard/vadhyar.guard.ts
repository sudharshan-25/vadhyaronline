import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {LoginService} from '../services/login.service';

@Injectable({
  providedIn: 'root'
})
export class VadhyarGuard implements CanActivate {

  constructor(private loginService: LoginService, private router: Router) {
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (this.loginService.isUserLoggedIn()) {
      return 'vadhyar' === this.loginService.getUser().role.toLowerCase();
    } else {
      this.router.navigate(['/login'], {queryParams: [{'reason': 'Unauthorized'}]});
    }
    return false;
  }
}
