import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';

import {AppComponent} from './app.component';
import {HomeComponent} from './shared/userPages/home/home.component';
import {LoginUserGuard} from './services/login-user.guard';
import {LoginComponent} from './shared/login/login.component';
import {RegisterUserComponent} from './shared/register-user/register-user.component';
import {IconsModule} from './shared/icons.module';
import {TokenHttpInterceptorService} from './services/token-http-interceptor.service';
import {MenuComponent} from './shared/menu/menu.component';
import {ChangePasswordComponent} from './shared/change-password/change-password.component';
import {AdminHomeComponent} from './shared/adminPages/admin-home/admin-home.component';
import {VadhyarHomeComponent} from './shared/vadhyarPages/vadhyar-home/vadhyar-home.component';
import {PrimaryDetailsComponent} from './shared/profile-details/primary-details/primary-details.component';


const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: '/home'},
  {path: 'home', component: HomeComponent, canActivate: [LoginUserGuard]},
  {path: 'adminHome', component: AdminHomeComponent, canActivate: [LoginUserGuard]},
  {path: 'vadhyarHome', component: VadhyarHomeComponent, canActivate: [LoginUserGuard]},
  {path: 'changePassword', component: ChangePasswordComponent, canActivate: [LoginUserGuard]},
  {path: 'primaryDetails', component: PrimaryDetailsComponent, canActivate: [LoginUserGuard]},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterUserComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    AdminHomeComponent, VadhyarHomeComponent, HomeComponent,
    MenuComponent, ChangePasswordComponent, PrimaryDetailsComponent,
    LoginComponent, RegisterUserComponent
  ],
  imports: [
    BrowserModule, ReactiveFormsModule, IconsModule, HttpClientModule,
    RouterModule.forRoot(routes),
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: TokenHttpInterceptorService,
    multi: true
  }],
  bootstrap: [AppComponent],
})
export class AppModule {
}
