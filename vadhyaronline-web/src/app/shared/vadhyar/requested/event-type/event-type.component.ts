import { Component, OnInit } from '@angular/core';
import {EventCategory, EventType} from '../../../../domain/domain';
import {RestService} from '../../../../services/rest.service';
import {NzNotificationService} from 'ng-zorro-antd';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-event-type',
  templateUrl: './event-type.component.html',
  styleUrls: ['./event-type.component.css']
})
export class EventTypeComponent implements OnInit {

  data: Array<EventType> = [];
  loading: boolean;

  constructor(private restService: RestService, private notification: NzNotificationService) {
  }

  loadRequestedEventTypes() {
    this.loading = true;
    this.restService.getRequestedEventTypes().subscribe(value => {
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
    this.loadRequestedEventTypes();
  }
}
