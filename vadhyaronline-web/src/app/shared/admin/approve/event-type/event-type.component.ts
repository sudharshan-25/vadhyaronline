import {Component, OnInit} from '@angular/core';
import {RestService} from '../../../../services/rest.service';
import {NzNotificationService} from 'ng-zorro-antd';
import {EventType} from '../../../../domain/domain';
import {HttpErrorResponse} from '@angular/common/http';
import {AbstractTableView} from '../../../../domain/abstract-table-view';

@Component({
  selector: 'app-event-type',
  templateUrl: './event-type.component.html',
  styleUrls: ['./event-type.component.css']
})
export class EventTypeComponent extends AbstractTableView<EventType> implements OnInit {

  constructor(private restService: RestService, private notification: NzNotificationService) {
    super();
  }

  setFilterColumns() {
    this.filteringValues = {'eventTypeName' : [{}], eventCategoryName: [{}], approved: [{}], requestedBy : [{}],  approvedBy: [{}]};
  }

  public loadEventTypes(): void {
    this.restService.getUnapprovedEventTypes().subscribe(value => {
      this.loading = false;
      this.setData(value.data);
    }, (error: HttpErrorResponse) => {
      this.notification.error('Error Loading', error.error);
      this.loading = false;
    });
  }

  ngOnInit() {
    this.setFilterColumns();
    this.loadEventTypes();
  }

}
