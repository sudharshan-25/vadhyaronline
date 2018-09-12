import {Routes} from '@angular/router';
import {UserHomeComponent} from './user-home/user-home.component';
import {ChangePasswordComponent} from './change-password/change-password.component';


export const USER_APP_ROUTES: Routes = [
  {path: 'user', redirectTo: 'user/home', pathMatch: 'prefix'},
  {path: 'home', component: UserHomeComponent},
  {path: 'change-password', component: ChangePasswordComponent}
];
