import {Component, OnInit} from '@angular/core';
import {LoginService} from '../../services/login.service';
import {LoginUser} from '../../domain/login-user';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  isAdmin: boolean;
  isVadhyar: boolean;
  isUser: boolean;

  constructor(private loginService: LoginService) {
    const loggedInUser = this.loginService.getUser();
    this.isAdmin = loggedInUser.role === 'Admin';
    this.isVadhyar = loggedInUser.role === 'Vadhyar';
    this.isUser = loggedInUser.role === 'User';
  }

  ngOnInit() {
  }

}
