import {Routes} from '@angular/router';
import {VadhyarGuard} from './guard/vadhyar.guard';
import {AdminGuard} from './guard/admin.guard';
import {UserGuard} from './guard/user.guard';
import {HomeRedirectComponent} from './shared/menu/home-redirect/home-redirect.component';


export const APP_ROUTES: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'vadhyar', loadChildren: './shared/vadhyar/vadhyar.module#VadhyarModule', canActivate: [VadhyarGuard]},
  {path: 'admin', loadChildren: './shared/admin/admin.module#AdminModule', canActivate: [AdminGuard]},
  {path: 'user', loadChildren: './shared/user/user.module#UserModule', canActivate: [UserGuard]},
  {path: 'login', loadChildren: './shared/login/login.module#LoginModule'},
  {path: 'register', loadChildren: './shared/register/register.module#RegisterModule'},
  {path: 'home', component: HomeRedirectComponent},
  {path: '**', redirectTo: '/login/404'}
];
