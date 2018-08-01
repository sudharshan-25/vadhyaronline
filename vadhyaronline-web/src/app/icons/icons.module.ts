import {NgModule} from '@angular/core';
import {
  IconClock,
  IconEdit, IconEyeOff,
  IconHome,
  IconLogIn,
  IconLogOut,
  IconSettings,
  IconUser,
  IconUserPlus
} from 'angular-feather';

const icons = [
  IconHome, IconEdit, IconSettings, IconUser, IconClock, IconLogOut, IconLogIn, IconUserPlus,
  IconEyeOff
];

@NgModule({
  exports: icons
})
export class IconsModule { }
