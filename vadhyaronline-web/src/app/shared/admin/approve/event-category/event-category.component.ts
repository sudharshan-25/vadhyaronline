import {Component, OnInit} from '@angular/core';
import {RestService} from '../../../../services/rest.service';
import {EventCategory} from '../../../../domain/domain';
import {HttpErrorResponse} from '@angular/common/http';
import {NzNotificationService} from 'ng-zorro-antd';
import {AbstractTableView} from '../../../../domain/abstract-table-view';

@Component({
  selector: 'app-event-category',
  templateUrl: './event-category.component.html',
  styleUrls: ['./event-category.component.css']
})
export class EventCategoryComponent extends AbstractTableView<EventCategory> implements OnInit {

  constructor(private restService: RestService, private notification: NzNotificationService) {
    super();
  }

  loadAllEventCategories() {
    this.loading = true;
    this.restService.getUnapprovedEventCategories().subscribe(value => {
      this.loading = false;
      this.setData(value.data);
    }, (error: HttpErrorResponse) => {
      this.notification.error('Error Loading', error.error);
      this.loading = false;
    });
  }

  ngOnInit() {
    this.loadAllEventCategories();
  }

}
