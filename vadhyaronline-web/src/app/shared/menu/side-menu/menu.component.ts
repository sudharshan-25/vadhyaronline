import {Component, OnInit} from '@angular/core';
import {IconLoaderService} from 'amexio-ng-extensions';
import {LoginService} from '../../../services/login.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  dataReader: string;
  showMenu: boolean;

  constructor(private iconService: IconLoaderService, private loginService: LoginService, private router: Router) {
    this.iconService.iconToUse = 'fa';
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
    const loginUser = this.loginService.getUser();
    if (loginUser) {
      switch (loginUser.role.toLowerCase()) {
        case 'admin':
          this.dataReader = 'menus.admin.data';
          break;
        case 'vadhyar':
          this.dataReader = 'menus.vadhyar.data';
          break;
        case 'user':
          this.dataReader = 'menus.user.data';
          break;
        default:
          this.dataReader = 'menus.user.data';
      }
      this.showMenu = true;
    } else {
      this.showMenu = false;
    }
  }

  ngOnInit() {
  }

  nodeClicked(event) {
    if (event.link) {
      console.log(event.link);
      this.router.navigate([event.link]);
    }
  }

}
