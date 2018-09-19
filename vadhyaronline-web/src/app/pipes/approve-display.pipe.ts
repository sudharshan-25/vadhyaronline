import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'approveDisplay'
})
export class ApproveDisplayPipe implements PipeTransform {

  transform(value: any, args?: any): any {
    const approvalHTML =
      `<nz-switch [ngModel]="${value}" [nzCheckedChildren]="checkedTemplate" [nzDisabled]="true" 
     [nzUnCheckedChildren]="unCheckedTemplate"></nz-switch>
    <ng-template #checkedTemplate><i class="anticon anticon-check"></i></ng-template>
    <ng-template #unCheckedTemplate><i class="anticon anticon-coss"></i></ng-template>`;
    return approvalHTML;
  }

}
