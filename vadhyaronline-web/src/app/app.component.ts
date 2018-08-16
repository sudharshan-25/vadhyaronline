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
  userName: string;

  constructor(private loginService: LoginService, private router: Router) {
    this.isLoggedIn = this.loginService.isUserLoggedIn();
    this.setupUserName();
    this.loginService.emitLoginEvent().subscribe(value => {
      this.isLoggedIn = value;
      this.setupUserName();
    });
  }

  private setupUserName(): void {
    if (this.isLoggedIn) {
      this.userName = this.loginService.getUser().userName;
    }
  }

  logout() {
    this.loginService.removeUserSession();
    this.router.navigate(['login']);
  }
}
