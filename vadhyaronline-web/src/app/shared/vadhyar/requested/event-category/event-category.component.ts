import {Component, OnInit} from '@angular/core';
import {EventCategory} from '../../../../domain/domain';
import {RestService} from '../../../../services/rest.service';
import {NzNotificationService} from 'ng-zorro-antd';
import {HttpErrorResponse} from '@angular/common/http';

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

  loadRequestedEventCategories() {
    this.loading = true;
    this.restService.getRequestedEventCategories().subscribe(value => {
      this.loading = false;
      this.data = value.data;
    }, (error: HttpErrorResponse) => {
      this.notification.error('Error Loading', error.error);
      this.loading = false;
    });
  }

  ngOnInit() {
    this.loadRequestedEventCategories();
  }

}
