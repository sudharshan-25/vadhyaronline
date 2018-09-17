import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {UserRegistrationComponent} from './user-registration/user-registration.component';
import {ReactiveFormsModule} from '@angular/forms';
import {en_US, NgZorroAntdModule, NZ_I18N} from 'ng-zorro-antd';

const REGISTER_ROUTES: Routes = [
  {path: '', redirectTo: 'user', pathMatch: 'full'},
  {path: 'user', component: UserRegistrationComponent}
];

@NgModule({
  imports: [
    CommonModule, RouterModule.forChild(REGISTER_ROUTES), ReactiveFormsModule, NgZorroAntdModule
  ],
  declarations: [UserRegistrationComponent],
  providers: [
    {provide: NZ_I18N, useValue: en_US}
  ]
})
export class RegisterModule {
}
