import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UserLoginComponent} from './user-login/user-login.component';
import {RouterModule} from '@angular/router';
import {LOGIN_ROUTES} from './login.routes';
import {ReactiveFormsModule} from '@angular/forms';

@NgModule({
  imports: [
    CommonModule, RouterModule.forChild(LOGIN_ROUTES), ReactiveFormsModule
  ],
  declarations: [UserLoginComponent]
})
export class LoginModule {
}
