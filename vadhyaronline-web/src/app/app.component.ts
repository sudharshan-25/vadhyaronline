import {Component} from '@angular/core';
import {LoginService} from './services/login.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  isLoggedIn: boolean;

  constructor(private loginService: LoginService, private router: Router) {
    this.isLoggedIn = this.loginService.isUserLoggedIn();
    this.loginService.emitLoginEvent().subscribe(value => {
      this.isLoggedIn = value;
    });
  }

  logout() {
    this.loginService.removeUserSession();
    this.router.navigate(['login']);
  }
}
