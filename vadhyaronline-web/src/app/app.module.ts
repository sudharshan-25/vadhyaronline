import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {RouterModule} from '@angular/router';
import {AmexioDataModule, AmexioNavModule, AmexioWidgetModule} from 'amexio-ng-extensions';
import {APP_ROUTES} from './app.routes';
import {AngularFontAwesomeModule} from 'angular-font-awesome';
import {MenuComponent} from './shared/menu/side-menu/menu.component';
import {TopMenuComponent} from './shared/menu/top-menu/top-menu.component';
import {TokenHttpInterceptorService} from './services/token-http-interceptor.service';
import {HomeRedirectComponent} from './shared/menu/home-redirect/home-redirect.component';

@NgModule({
  declarations: [
    AppComponent, MenuComponent, TopMenuComponent, HomeRedirectComponent
  ],
  imports: [
    BrowserModule, FormsModule, ReactiveFormsModule, HttpClientModule,
    RouterModule.forRoot(APP_ROUTES,
      {
        useHash: false,
        onSameUrlNavigation: 'reload',
        scrollPositionRestoration: 'enabled'
      }),
    AmexioDataModule, AmexioWidgetModule, AmexioNavModule,
    AngularFontAwesomeModule
  ],
  providers: [
    {provide: 'API_URL', useValue: 'http://localhost:8080/vadhyar'},
    {provide: HTTP_INTERCEPTORS, useClass: TokenHttpInterceptorService, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
