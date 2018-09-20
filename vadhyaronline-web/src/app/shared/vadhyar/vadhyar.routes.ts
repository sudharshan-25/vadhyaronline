import {Routes} from '@angular/router';

export const VADHYAR_ROUTES: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'requested', loadChildren: './requested/requested.module#RequestedModule'}
];
