import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {LoginService} from '../../../services/login.service';
import {Router} from '@angular/router';
import {LoginUser} from '../../../domain/domain';
import {HttpErrorResponse} from '@angular/common/http';
import {RestService} from '../../../services/rest.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {

  isError: boolean;
  errorMessage: string;

  loginForm: FormGroup;

  constructor(fb: FormBuilder, public router: Router, private loginService: LoginService, private restService: RestService) {
    this.loginForm = fb.group({
      'userName': new FormControl('', [Validators.required]),
      'password': new FormControl('', [Validators.required])
    });
  }

  ngOnInit() {
  }

  initFormValue() {
    this.loginForm.reset({userName: '', password: ''}, {onlySelf: false});
  }

  login() {
    this.isError = false;
    if (this.loginForm.valid) {
      this.restService.login(this.loginForm.controls['userName'].value, this.loginForm.controls['password'].value)
        .subscribe((response) => {
          const user: LoginUser = response.data;
          this.loginService.addUser(user);
          this.router.navigate(['/']);
        }, (error: HttpErrorResponse) => {
          this.isError = true;
          this.errorMessage = error.error;
          this.initFormValue();
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
