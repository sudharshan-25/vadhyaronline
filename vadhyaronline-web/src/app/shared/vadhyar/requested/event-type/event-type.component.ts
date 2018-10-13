import {Component, OnInit} from '@angular/core';
import {EventType} from '../../../../domain/domain';
import {RestService} from '../../../../services/rest.service';
import {NzNotificationService} from 'ng-zorro-antd';
import {HttpErrorResponse} from '@angular/common/http';
import {AbstractTableView} from '../../../../domain/abstract-table-view';
import {EditEntity} from '../../../../domain/edit-entity';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-event-type',
  templateUrl: './event-type.component.html',
  styleUrls: ['./event-type.component.css']
})
export class EventTypeComponent extends AbstractTableView<EventType> implements OnInit, EditEntity<EventType> {

  protected selectedEntity: EventType;
  protected editAllowed: boolean;
  protected eventTypeForm: FormGroup;

  constructor(fb: FormBuilder, private restService: RestService, private notification: NzNotificationService) {
    super();
    this.eventTypeForm = fb.group({
      eventTypeId: new FormControl('', [Validators.required]),
      eventTypeName: new FormControl('', [Validators.required]),
      eventTypeDescription: new FormControl(''),
      eventCategoryId: new FormControl('', [Validators.required])
    });
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
    this.eventTypeForm.setValue(this.selectedEntity);
  }

  cancelChanges() {
    this.selectedEntity = null;
    this.editAllowed = false;
    this.loadRequestedEventTypes();
  }

  createEntity() {
    this.selectedEntity = {eventTypeId: 0, eventTypeName: '', eventTypeDescription: '', eventCategoryId: 0};
    this.eventTypeForm.setValue(this.selectedEntity);
    this.editAllowed = true;
  }

  deleteEntity(entity: EventType) {
    this.restService.deleteEventType(entity).subscribe(value => {
      this.notification.success('Deleted', value.data);
    }, (error: HttpErrorResponse) => {
      this.notification.error('Error', error.error);
    }, () => {
      this.loadRequestedEventTypes();
    });
  }

  updateEntity(updatedEntity: EventType) {
    if (this.eventTypeForm.valid) {
      const updatedET: EventType = this.eventTypeForm.value;
      if (updatedET.eventTypeId === 0) {
        this.restService.createEventType(updatedET).subscribe(value => {
          this.notification.success('Success', value.data);
        }, (error: HttpErrorResponse) => {
          this.notification.error('Error', error.error);
        }, () => {
          this.selectedEntity = null;
          this.editAllowed = false;
          this.loadRequestedEventTypes();
        });
      } else {
        this.restService.updateEventType(updatedET).subscribe(value => {
          this.notification.success('Success', value.data);
        }, (error: HttpErrorResponse) => {
          this.notification.error('Error', error.error);
        }, () => {
          this.selectedEntity = null;
          this.editAllowed = false;
          this.loadRequestedEventTypes();
        });
      }
    }
  }

}
