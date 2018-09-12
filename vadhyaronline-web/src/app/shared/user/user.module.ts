import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UserHomeComponent} from './user-home/user-home.component';
import {RouterModule} from '@angular/router';
import {USER_APP_ROUTES} from './user-routes';
import {ChangePasswordComponent} from './change-password/change-password.component';

@NgModule({
  imports: [
    CommonModule, RouterModule.forChild(USER_APP_ROUTES)
  ],
  declarations: [UserHomeComponent, ChangePasswordComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class UserModule {
}
