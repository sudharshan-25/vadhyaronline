import {Component, OnInit} from '@angular/core';
import {EventCategory, EventType} from '../../../../domain/domain';
import {RestService} from '../../../../services/rest.service';
import {NzNotificationService} from 'ng-zorro-antd';
import {HttpErrorResponse} from '@angular/common/http';
import {AbstractTableView} from '../../../../domain/abstract-table-view';
import {EditEntity} from '../../../../domain/edit-entity';

@Component({
  selector: 'app-event-type',
  templateUrl: './event-type.component.html',
  styleUrls: ['./event-type.component.css']
})
export class EventTypeComponent extends AbstractTableView<EventType> implements OnInit, EditEntity<EventType> {

  protected selectedEntity: EventCategory;
  protected editAllowed: boolean;

  constructor(private restService: RestService, private notification: NzNotificationService) {
    super();
  }

  setFilterColumns() {
    this.filteringValues = {'eventTypeName': [{}], eventCategoryName: [{}], approved: [{}], requestedBy: [{}], approvedBy: [{}]};
  }

  loadRequestedEventTypes() {
    this.loading = true;
    this.restService.getRequestedEventTypes().subscribe(value => {
      this.loading = false;
      this.setData(value.data);
    }, (error: HttpErrorResponse) => {
      this.notification.error('Error Loading', error.error);
      this.loading = false;
    });
  }

  ngOnInit() {
    this.setFilterColumns();
    this.loadRequestedEventTypes();
  }

  editEventType(eventType: EventType) {
    this.selectedEntity = eventType;
    this.editAllowed = true;
  }

  cancelChanges() {
    this.selectedEntity = null;
    this.editAllowed = false;
    this.loadRequestedEventTypes();
  }

  createEntity() {
  }

  deleteEntity(entity: EventType) {
  }

  updateEntity(updatedEntity: EventType) {
    this.loadRequestedEventTypes();
  }

}
