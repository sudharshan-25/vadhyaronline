import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {VADHYAR_ROUTES} from './vadhyar.routes';


@NgModule({
  imports: [
    CommonModule, RouterModule.forChild(VADHYAR_ROUTES)
  ],
  declarations: []
})
export class VadhyarModule { }
