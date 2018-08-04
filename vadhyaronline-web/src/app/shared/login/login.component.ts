import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {VadhyarOnlineRestService} from '../../services/vadhyar-online-rest.service';
import {LoginUser} from '../../domain/login-user';
import {LoginService} from '../../services/login.service';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  isLoginError: boolean;
  errorMessage = '';

  loginForm: FormGroup;

  constructor(fb: FormBuilder, public router: Router, private loginService: LoginService,
              private restService: VadhyarOnlineRestService) {
    this.loginForm = fb.group({
      'userName': new FormControl('', [Validators.required]),
      'password': new FormControl('', [Validators.required])
    });
  }

  ngOnInit() {
  }

  login() {
    this.isLoginError = false;
    if (this.loginForm.valid) {
      this.restService.login(this.loginForm.controls['userName'].value, this.loginForm.controls['password'].value)
        .subscribe((response) => {
          const user = LoginUser.parseUser(JSON.stringify(response.DATA));
          this.loginService.addUser(user);
        }, (error1: HttpErrorResponse) => {
          this.isLoginError = true;
          this.errorMessage = error1.message;
        });
    } else {
      Object.keys(this.loginForm.controls).forEach(control => {
        if (this.loginForm.controls[control].invalid) {
          this.loginForm.controls[control].markAsTouched();
        }
      });
    }
  }

  registerNow() {
    this.router.navigate(['register']);
  }

}
