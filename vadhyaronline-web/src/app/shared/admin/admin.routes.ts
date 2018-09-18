import {Routes} from '@angular/router';

export const ADMIN_ROUTES: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'approve', loadChildren: './approve/approve.module#ApproveModule'}
];
