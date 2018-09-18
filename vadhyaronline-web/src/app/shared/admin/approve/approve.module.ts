import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {EventCategoryComponent} from './event-category/event-category.component';
import {RouterModule, Routes} from '@angular/router';
import {EventTypeComponent} from './event-type/event-type.component';
import {GothramComponent} from './gothram/gothram.component';
import {SoothramComponent} from './soothram/soothram.component';
import {NgZorroAntdModule} from 'ng-zorro-antd';

const APPROVE_MASTER_ROUTES: Routes = [
  {path: '', redirectTo: 'event-category', pathMatch: 'full'},
  {path: 'event-category', component: EventCategoryComponent},
  {path: 'event-type', component: EventTypeComponent},
  {path: 'gothram', component: GothramComponent},
  {path: 'soothram', component: SoothramComponent}
];

@NgModule({
  imports: [
    CommonModule, RouterModule.forChild(APPROVE_MASTER_ROUTES), NgZorroAntdModule
  ],
  declarations: [EventCategoryComponent, EventTypeComponent, GothramComponent, SoothramComponent]
})
export class ApproveModule {
}
