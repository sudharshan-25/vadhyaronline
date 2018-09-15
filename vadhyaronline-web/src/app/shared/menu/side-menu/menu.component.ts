import {Component, OnInit} from '@angular/core';
import {LoginService} from '../../../services/login.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  showMenu: boolean;

  admin: boolean;
  vadhyar: boolean;
  user: boolean;

  constructor(private loginService: LoginService) {
    if (this.loginService.isUserLoggedIn()) {
      this.initMenu();
    } else {
      this.showMenu = false;
    }
    this.loginService.emitLoginEvent().subscribe(value => {
      this.showMenu = value;
      if (value) {
        this.initMenu();
      }
    });
  }

  private initMenu() {
    this.admin = false;
    this.vadhyar = false;
    this.user = false;
    const loginUser = this.loginService.getUser();
    if (loginUser) {
      switch (loginUser.role.toLowerCase()) {
        case 'admin':
          this.admin = true;
          break;
        case 'vadhyar':
          this.vadhyar = true;
          break;
        case 'user':
          this.user = true;
          break;
        case 'assistant':
          this.vadhyar = true;
      }
      this.showMenu = true;
    } else {
      this.showMenu = false;
    }
  }

  ngOnInit() {
  }

}
