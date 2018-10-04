import {Component, OnInit} from '@angular/core';
import {RestService} from '../../../../services/rest.service';
import {EventCategory} from '../../../../domain/domain';
import {HttpErrorResponse} from '@angular/common/http';
import {NzNotificationService} from 'ng-zorro-antd';
import {AbstractTableView} from '../../../../domain/abstract-table-view';
import {ApproveEntity, EditEntity} from '../../../../domain/edit-entity';

@Component({
  selector: 'app-event-category',
  templateUrl: './event-category.component.html',
  styleUrls: ['./event-category.component.css']
})
export class EventCategoryComponent extends AbstractTableView<EventCategory>
  implements OnInit, EditEntity<EventCategory>, ApproveEntity<EventCategory> {

  protected selectedEntity: EventCategory;
  protected editAllowed: boolean;

  constructor(private restService: RestService, private notification: NzNotificationService) {
    super();
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
  }

  updateEntity(updatedEC: EventCategory) {
    this.loadAllEventCategories();
  }

  approveEntity(eventCategory: EventCategory) {

  }

  cancelChanges() {
    this.selectedEntity = null;
    this.editAllowed = false;
    this.loadAllEventCategories();
  }

  createEntity() {
    this.selectedEntity = {eventCategoryId: 0, eventCategoryName: '', approvedBy: '', requestedBy: '', approved: false};
    this.editAllowed = true;
  }

  deleteEntity(eventCategory: EventCategory) {
  }
}
