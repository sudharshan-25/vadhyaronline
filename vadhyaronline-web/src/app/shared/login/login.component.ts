import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  isLoginError: boolean;
  errorMessage = '';

  loginForm: FormGroup;

  constructor(fb: FormBuilder, public router: Router) {
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

    } else {
      this.isLoginError = true;
      this.errorMessage = 'Please fill the mandatory fields';
    }
  }

  registerNow() {
    this.router.navigate(['register']);
  }

}
