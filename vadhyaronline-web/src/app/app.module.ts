import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {RouterModule} from '@angular/router';
import {APP_ROUTES} from './app.routes';
import {MenuComponent} from './shared/menu/side-menu/menu.component';
import {TopMenuComponent} from './shared/menu/top-menu/top-menu.component';
import {TokenHttpInterceptorService} from './services/token-http-interceptor.service';
import {HomeRedirectComponent} from './shared/menu/home-redirect/home-redirect.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {en_US, NgZorroAntdModule, NZ_I18N} from 'ng-zorro-antd';

@NgModule({
  declarations: [
    AppComponent, MenuComponent, TopMenuComponent, HomeRedirectComponent
  ],
  imports: [
    BrowserModule, FormsModule, ReactiveFormsModule, BrowserAnimationsModule, HttpClientModule,
    RouterModule.forRoot(APP_ROUTES,
      {
        useHash: false,
        onSameUrlNavigation: 'reload',
        scrollPositionRestoration: 'enabled'
      }),
    NgZorroAntdModule
  ],
  providers: [
    {provide: 'API_URL', useValue: 'http://localhost:8080/vadhyar'},
    {provide: HTTP_INTERCEPTORS, useClass: TokenHttpInterceptorService, multi: true},
    {provide: NZ_I18N, useValue: en_US}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
