import {NgModule} from '@angular/core';
import {
  IconBook,
  IconBookOpen,
  IconCalendar,
  IconCast,
  IconCheckCircle,
  IconClock,
  IconEdit,
  IconEyeOff,
  IconFeather,
  IconFolder,
  IconHome,
  IconInfo,
  IconLogIn,
  IconLogOut, IconPlay,
  IconPlus, IconRefreshCw,
  IconSettings,
  IconSlash,
  IconSliders,
  IconUser,
  IconUserPlus
} from 'angular-feather';


const icons = [
  IconHome, IconEdit, IconSettings, IconUser, IconClock, IconLogOut, IconLogIn, IconUserPlus,
  IconEyeOff, IconSlash, IconCast, IconFeather, IconFolder, IconSliders, IconCheckCircle,
  IconPlus, IconInfo, IconBook, IconBookOpen, IconCalendar, IconRefreshCw, IconPlay
];

@NgModule({
  exports: icons
})
export class IconsModule {
}
