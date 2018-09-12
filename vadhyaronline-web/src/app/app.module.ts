import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {RouterModule} from '@angular/router';
import {AmexioDataModule, AmexioNavModule, AmexioWidgetModule} from 'amexio-ng-extensions';
import {APP_ROUTES} from './app.routes';
import {AngularFontAwesomeModule} from 'angular-font-awesome';
import {MenuComponent} from './shared/menu/menu.component';

@NgModule({
  declarations: [
    AppComponent, MenuComponent
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
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
