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
    this.restService.getAllEventCategories().subscribe(value => {
      this.loading = false;
      this.data = value.data;
    }, (error: HttpErrorResponse) => {
      this.notification.error('Error Loading', error.error);
      this.loading = false;
    });
  }

  ngOnInit() {
    this.loadAllEventCategories();
  }

}
