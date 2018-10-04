import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NgZorroAntdModule} from 'ng-zorro-antd';
import {EventCategoryComponent} from './event-category/event-category.component';
import {EventTypeComponent} from './event-type/event-type.component';
import {GothramComponent} from './gothram/gothram.component';
import {SoothramComponent} from './soothram/soothram.component';
import {RouterModule, Routes} from '@angular/router';
import {ReactiveFormsModule} from '@angular/forms';

const REQUESTED_MASTER_ROUTES: Routes = [
  {path: '', redirectTo: 'event-category', pathMatch: 'full'},
  {path: 'event-category', component: EventCategoryComponent},
  {path: 'event-type', component: EventTypeComponent},
  {path: 'gothram', component: GothramComponent},
  {path: 'soothram', component: SoothramComponent}
];


@NgModule({
  imports: [
    CommonModule, NgZorroAntdModule, RouterModule.forChild(REQUESTED_MASTER_ROUTES), ReactiveFormsModule
  ],
  declarations: [EventCategoryComponent, EventTypeComponent, GothramComponent, SoothramComponent]
})
export class RequestedModule {
}
