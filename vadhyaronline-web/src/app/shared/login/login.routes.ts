import {Routes} from '@angular/router';
import {UserLoginComponent} from './user-login/user-login.component';

export const LOGIN_ROUTES: Routes = [
  {path: '', redirectTo: '/login/user', pathMatch: 'full'},
  {path: 'user', component: UserLoginComponent},
  {path: 'admin', component: null}
];
