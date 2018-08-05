import {NgModule} from '@angular/core';
import {
  IconClock,
  IconEdit,
  IconEyeOff,
  IconHome,
  IconLogIn,
  IconLogOut,
  IconSettings, IconSlash,
  IconUser,
  IconUserPlus
} from 'angular-feather';


const icons = [
  IconHome, IconEdit, IconSettings, IconUser, IconClock, IconLogOut, IconLogIn, IconUserPlus,
  IconEyeOff, IconSlash
];

@NgModule({
  exports: icons
})
export class IconsModule { }
