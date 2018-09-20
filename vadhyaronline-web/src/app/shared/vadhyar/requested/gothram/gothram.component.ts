import { Component, OnInit } from '@angular/core';
import {Gothram} from '../../../../domain/domain';
import {RestService} from '../../../../services/rest.service';
import {NzNotificationService} from 'ng-zorro-antd';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-gothram',
  templateUrl: './gothram.component.html',
  styleUrls: ['./gothram.component.css']
})
export class GothramComponent implements OnInit {

  data: Array<Gothram> = [];
  loading: boolean;

  constructor(private restService: RestService, private notification: NzNotificationService) {
  }

  public loadRequestedGothrams() {
    this.restService.getRequestedGothrams().subscribe(value => {
      this.loading = false;
      this.data = value.data;
    }, (error: HttpErrorResponse) => {
      this.notification.error('Error Loading', error.error);
      this.loading = false;
    });
  }

  sort(sort: { key: string, value: string }): void {
    this.loading = true;
    const dataList = [...this.data];
    const sortName = sort.key;
    const sortValue = sort.value;
    if (sortName && sortValue) {
      this.data = dataList.sort((a, b) => (sortValue === 'ascend')
        ? (a[sortName] > b[sortName] ? 1 : -1) : (b[sortName] > a[sortName] ? 1 : -1));
    } else {
      this.data = dataList;
    }
    this.loading = false;
  }

  ngOnInit() {
    this.loadRequestedGothrams();
  }

}
