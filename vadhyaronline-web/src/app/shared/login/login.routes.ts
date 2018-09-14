import {Routes} from '@angular/router';
import {UserLoginComponent} from './user-login/user-login.component';
import {NotFoundPageComponent} from './not-found-page/not-found-page.component';

export const LOGIN_ROUTES: Routes = [
  {path: '', redirectTo: '/login/user', pathMatch: 'full'},
  {path: 'user', component: UserLoginComponent},
  {path: 'admin', component: null},
  {path: '404', component: NotFoundPageComponent}
];
