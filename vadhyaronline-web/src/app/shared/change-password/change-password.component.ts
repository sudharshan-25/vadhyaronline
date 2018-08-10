import {Component, OnInit} from '@angular/core';
import {AbstractComponent} from '../abstract-component';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {LoginService} from '../../services/login.service';
import {matchOtherValidator} from '../../domain/validation-custom';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent extends AbstractComponent implements OnInit {

  changePwdForm: FormGroup;

  isAdmin: boolean;
  userList: Array<any>;

  constructor(fb: FormBuilder, private loginService: LoginService) {
    super();
    this.changePwdForm = fb.group({
      'userId': new FormControl('', [Validators.required]),
      'oldPassword': new FormControl('', [Validators.required]),
      'newPassword': new FormControl('', [Validators.required]),
      'newPasswordRepeat': new FormControl('', [Validators.required,
        matchOtherValidator('newPassword')]),
    });
    this.isAdmin = loginService.getUser().role === 'Admin';
    if (this.isAdmin) {
      // TODO list users
      this.userList = [];
    } else {
      this.changePwdForm.controls['userId'].setValue(this.loginService.getUser().userId);
    }
  }

  initFormValue(): any {
    return {
      userId: this.isAdmin ? '' : this.loginService.getUser().userId,
      oldPassword: '', newPassword: '', newPasswordRepeat: ''
    };
  }

  ngOnInit() {
  }

  changePassword(values: any) {
    this.isError = false;
    if (this.changePwdForm.valid) {
      // TODO change password rest
      this.changePwdForm.reset(this.initFormValue());
    } else {
      Object.keys(this.changePwdForm.controls).forEach(key => {
        if (this.changePwdForm.controls[key].invalid) {
          this.changePwdForm.controls[key].markAsTouched();
        }
      });
    }
  }

}
