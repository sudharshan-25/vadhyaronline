import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {UserRegistrationComponent} from './user-registration/user-registration.component';
import {ReactiveFormsModule} from '@angular/forms';

const REGISTER_ROUTES: Routes = [
  {path: '', redirectTo: 'user', pathMatch: 'full'},
  {path: 'user', component: UserRegistrationComponent}
];

@NgModule({
  imports: [
    CommonModule, RouterModule.forChild(REGISTER_ROUTES), ReactiveFormsModule
  ],
  declarations: [UserRegistrationComponent]
})
export class RegisterModule {
}
