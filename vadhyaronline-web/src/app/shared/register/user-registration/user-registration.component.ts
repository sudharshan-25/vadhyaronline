import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {RestService} from '../../../services/rest.service';
import {HttpErrorResponse} from '@angular/common/http';
import {matchOtherValidator} from '../../../validators/vadhyar.validations';
import {NzNotificationService} from 'ng-zorro-antd';

@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css']
})
export class UserRegistrationComponent implements OnInit {

  isError: boolean;
  errorMessage: string;
  successMessage: string;

  registerForm: FormGroup;
  userRoles: Array<any>;

  constructor(fb: FormBuilder, private service: RestService, private router: Router, private notification: NzNotificationService) {
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

  initFormValue(): any {
    this.registerForm.reset(
      {firstName: '', lastName: '', userName: '', mobile: '', email: '', password: '', passwordRepeat: '', role: ''}
      , {onlySelf: false});
  }


  ngOnInit() {
    this.userRoles = [];
    this.service.getRoles().subscribe(value => {
      value.data.forEach(role => {
        this.userRoles.push({id: role.key, value: role.value});
      });
    }, (error: HttpErrorResponse) => {
      this.notification.error('Error', error.message);
    });
  }

  registerUser() {
    this.isError = false;
    for (const i  of Object.keys(this.registerForm.controls)) {
      this.registerForm.controls[i].markAsDirty();
      this.registerForm.controls[i].updateValueAndValidity();
    }
    if (this.registerForm.valid) {
      const user = this.registerForm.getRawValue();
      user['userId'] = 0;
      delete user['passwordRepeat'];
      this.service.registerUser(user).subscribe(response => {
        if (response.data) {
          this.successMessage = response.data;
          this.initFormValue();
        }
      }, (error: HttpErrorResponse) => {
        this.isError = true;
        this.errorMessage = error.error;
        this.notification.error('Error', this.errorMessage);
      });
    } else {
      Object.keys(this.registerForm.controls).forEach(key => {
        if (this.registerForm.controls[key].invalid) {
          this.registerForm.controls[key].markAsDirty();
          this.registerForm.controls[key].markAsTouched();
        }
      });
    }
  }

  cancelRegister() {
    this.router.navigate(['/login']);
  }

}
