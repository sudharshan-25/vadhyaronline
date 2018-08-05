import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {VadhyarOnlineRestService} from '../../services/vadhyar-online-rest.service';
import {HttpErrorResponse} from '@angular/common/http';
import {Router} from '@angular/router';
import {AbstractComponent} from '../abstract-component';
import {matchOtherValidator} from '../../domain/validation-custom';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent extends AbstractComponent implements OnInit {

  registerForm: FormGroup;
  userRoles: Array<any>;

  constructor(fb: FormBuilder, private service: VadhyarOnlineRestService, private router: Router) {
    super();
    this.registerForm = fb.group({
      'firstName': new FormControl('', [Validators.required]),
      'lastName': new FormControl('', [Validators.required]),
      'userName': new FormControl('', [Validators.required]),
      'mobile': new FormControl('', [Validators.required]),
      'email': new FormControl('', [Validators.required, Validators.email]),
      'password': new FormControl('', [Validators.required]),
      'passwordRepeat': new FormControl('',
        [Validators.required, matchOtherValidator('password')]),
      'role': new FormControl('', [Validators.required]),
    });

  }

  ngOnInit() {
    this.userRoles = [];
    this.service.getRoles().subscribe(value => {
      value.DATA.forEach((role: any) => {
        if (role['id'] !== 1) {
          this.userRoles.push({id: role['id'], value: role['value']});
        }
      });
    }, (error: HttpErrorResponse) => {
      this.isError = true;
      this.errorMessage = error.error['ERROR'];
    });
  }

  registerUser() {
    this.isError = false;
    if (this.registerForm.valid) {
      const user = this.registerForm.getRawValue();
      user['userId'] = 0;
      delete user['passwordRepeat'];
      this.service.register(user).subscribe(response => {
        if (response.DATA) {
          this.router.navigate(['login']);
        }
      }, (error: HttpErrorResponse) => {
        this.isError = true;
        this.errorMessage = error.message;
      });
    } else {
      Object.keys(this.registerForm.controls).forEach(key => {
        if (this.registerForm.controls[key].invalid) {
          this.registerForm.controls[key].markAsTouched();
        }
      });
    }
  }

  cancelRegister() {
    this.router.navigate(['login']);
  }
}

