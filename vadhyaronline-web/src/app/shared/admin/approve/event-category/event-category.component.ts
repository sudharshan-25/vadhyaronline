import {Component, OnInit} from '@angular/core';
import {RestService} from '../../../../services/rest.service';
import {EventCategory} from '../../../../domain/domain';
import {HttpErrorResponse} from '@angular/common/http';
import {NzNotificationService} from 'ng-zorro-antd';
import {AbstractTableView} from '../../../../domain/abstract-table-view';
import {ApproveEntity, EditEntity} from '../../../../domain/edit-entity';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-event-category',
  templateUrl: './event-category.component.html',
  styleUrls: ['./event-category.component.css']
})
export class EventCategoryComponent extends AbstractTableView<EventCategory>
  implements OnInit, EditEntity<EventCategory>, ApproveEntity<EventCategory> {

  protected selectedEntity: EventCategory;
  protected editAllowed: boolean;
  protected eventCategoryForm: FormGroup;

  constructor(fb: FormBuilder, private restService: RestService,
              private notification: NzNotificationService) {
    super();
    this.eventCategoryForm = fb.group({
      eventCategoryId: new FormControl('', [Validators.required]),
      eventCategoryName: new FormControl('', [Validators.required]),
    });
  }

  setFilterColumns() {
    this.filteringValues = {eventCategoryName: [{}], approved: [{}], requestedBy: [{}], approvedBy: [{}]};
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
    this.setFilterColumns();
    this.loadAllEventCategories();
  }

  editEventCategory(eventCategory: EventCategory) {
    this.selectedEntity = eventCategory;
    this.editAllowed = true;
    this.eventCategoryForm.setValue(
      {
        eventCategoryId: this.selectedEntity.eventCategoryId,
        eventCategoryName: this.selectedEntity.eventCategoryName
      }
    );
  }

  updateEntity() {
    if (this.eventCategoryForm.valid) {
      const updatedEC = this.eventCategoryForm.value;
      if (updatedEC['eventCategoryId'] === 0) {
        this.restService.createEventCategory(updatedEC).subscribe(value => {
          this.notification.success('Success', value.data);
        }, (error: HttpErrorResponse) => {
          this.notification.error('Error', error.error);
        }, () => {
          this.selectedEntity = null;
          this.editAllowed = false;
          this.loadAllEventCategories();
        });
      } else {
        this.restService.updateEventCategory(updatedEC).subscribe(value => {
          this.notification.success('Success', value.data);
        }, (error: HttpErrorResponse) => {
          this.notification.error('Error', error.error);
        }, () => {
          this.selectedEntity = null;
          this.editAllowed = false;
          this.loadAllEventCategories();
        });
      }
    }
  }

  approveEntity(eventCategory: EventCategory) {
    this.restService.approveEventCategory(eventCategory).subscribe(value => {
      this.notification.success('Approved', value.data);
    }, (error: HttpErrorResponse) => {
      this.notification.error('Error', error.error);
    }, () => {
      this.loadAllEventCategories();
    });
  }

  cancelChanges() {
    this.selectedEntity = null;
    this.editAllowed = false;
    this.loadAllEventCategories();
  }

  createEntity() {
    this.selectedEntity = {eventCategoryId: 0, eventCategoryName: ''};
    this.selectedEntity = {eventCategoryId: 0, eventCategoryName: ''};
    this.eventCategoryForm.setValue(
      {
        eventCategoryId: this.selectedEntity.eventCategoryId,
        eventCategoryName: this.selectedEntity.eventCategoryName
      }
    );
    this.editAllowed = true;
  }

  deleteEntity(eventCategory: EventCategory) {
    this.restService.deleteEventCategory(eventCategory).subscribe(value => {
      this.notification.success('Deleted', value.data);
    }, (error: HttpErrorResponse) => {
      this.notification.error('Error', error.error);
    }, () => {
      this.loadAllEventCategories();
    });
  }
}
