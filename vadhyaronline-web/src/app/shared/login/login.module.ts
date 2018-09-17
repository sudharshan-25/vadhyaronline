import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UserLoginComponent} from './user-login/user-login.component';
import {RouterModule} from '@angular/router';
import {LOGIN_ROUTES} from './login.routes';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NotFoundPageComponent} from './not-found-page/not-found-page.component';
import {en_US, NgZorroAntdModule, NZ_I18N} from 'ng-zorro-antd';

@NgModule({
  imports: [
    CommonModule, RouterModule.forChild(LOGIN_ROUTES), ReactiveFormsModule, FormsModule, NgZorroAntdModule
  ],
  declarations: [UserLoginComponent, NotFoundPageComponent],
  providers: [
    {provide: NZ_I18N, useValue: en_US}
  ]
})
export class LoginModule {
}
