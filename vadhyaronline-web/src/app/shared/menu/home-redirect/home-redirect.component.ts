import {Component, OnInit} from '@angular/core';
import {LoginService} from '../../../services/login.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-home-redirect',
  templateUrl: './home-redirect.component.html',
  styleUrls: ['./home-redirect.component.css']
})
export class HomeRedirectComponent implements OnInit {

  constructor(private loginService: LoginService, private router: Router) {
    if (this.loginService.isUserLoggedIn()) {
      switch (this.loginService.getUser().role.toLowerCase()) {
        case 'admin':
          this.router.navigate(['/admin']);
          break;
        case 'vadhyar':
          this.router.navigate(['/vadhyar']);
          break;
        case 'user':
          this.router.navigate(['/user']);
          break;
        default:
          this.router.navigate(['/user']);
      }
    } else {
      this.router.navigate(['/login'], {queryParams: {message: 'UnAuthenticated'}});
    }
  }

  ngOnInit() {
  }

}
