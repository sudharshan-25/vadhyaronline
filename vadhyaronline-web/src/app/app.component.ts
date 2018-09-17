import {Component} from '@angular/core';
import {LoginService} from './services/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  showMenu: boolean;

  constructor(private loginService: LoginService) {
    this.showMenu = this.loginService.isUserLoggedIn();
    this.loginService.emitLoginEvent().subscribe(value => {
      this.showMenu = value;
    });
  }
}
