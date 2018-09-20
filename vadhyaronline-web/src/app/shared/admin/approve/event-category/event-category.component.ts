import {Component, OnInit} from '@angular/core';
import {RestService} from '../../../../services/rest.service';
import {EventCategory} from '../../../../domain/domain';
import {HttpErrorResponse} from '@angular/common/http';
import {NzNotificationService} from 'ng-zorro-antd';

@Component({
  selector: 'app-event-category',
  templateUrl: './event-category.component.html',
  styleUrls: ['./event-category.component.css']
})
export class EventCategoryComponent implements OnInit {

  data: Array<EventCategory> = [];
  loading: boolean;

  constructor(private restService: RestService, private notification: NzNotificationService) {
  }

  loadAllEventCategories() {
    this.loading = true;
    this.restService.getUnapprovedEventCategories().subscribe(value => {
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
    this.loadAllEventCategories();
  }

}
