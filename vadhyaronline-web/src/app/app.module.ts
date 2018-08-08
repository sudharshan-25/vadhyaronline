import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';

import {AppComponent} from './app.component';
import {HomeComponent} from './shared/home/home.component';
import {LoginUserGuard} from './services/login-user.guard';
import {LoginComponent} from './shared/login/login.component';
import {RegisterUserComponent} from './shared/register-user/register-user.component';
import {IconsModule} from './shared/icons.module';
import {TokenHttpInterceptorService} from './services/token-http-interceptor.service';


const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: '/home'},
  {path: 'home', component: HomeComponent, canActivate: [LoginUserGuard]},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterUserComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
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
