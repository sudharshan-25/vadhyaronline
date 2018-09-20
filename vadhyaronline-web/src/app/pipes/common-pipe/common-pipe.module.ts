import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApproveDisplayPipe } from './approve-display.pipe';
import {NgZorroAntdModule} from 'ng-zorro-antd';

@NgModule({
  imports: [
    CommonModule, NgZorroAntdModule
  ],
  declarations: [ApproveDisplayPipe]
})
export class CommonPipeModule { }
