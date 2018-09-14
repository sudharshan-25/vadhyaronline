import {Component, OnInit} from '@angular/core';
import {LoginService} from '../../../services/login.service';
import {RestService} from '../../../services/rest.service';
import {HttpErrorResponse} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-top-menu',
  templateUrl: './top-menu.component.html',
  styleUrls: ['./top-menu.component.css']
})
export class TopMenuComponent implements OnInit {

  userName: string;
  showLogout: boolean;

  constructor(private loginService: LoginService, private restService: RestService, private router: Router) {
    this.loginService.emitLoginEvent().subscribe(value => {
      this.showLogout = value;
      if (value) {
        this.userName = this.loginService.getUser().userName;
      }
    });
    this.showLogout = this.loginService.isUserLoggedIn();
  }

  ngOnInit() {
  }

  logout() {
    this.restService.logout().subscribe(value => {
      this.logoutFromBrowser(value);
    }, (error: HttpErrorResponse) => {
      console.log(error.message);
      this.logoutFromBrowser(error.message);
    });
  }

  private logoutFromBrowser(value) {
    this.loginService.removeUserSession();
    this.router.navigate(['/login'], {queryParams: {message: value}});
  }

}
