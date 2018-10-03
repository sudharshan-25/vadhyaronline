import {Component, OnInit} from '@angular/core';
import {EventCategory} from '../../../../domain/domain';
import {RestService} from '../../../../services/rest.service';
import {NzNotificationService} from 'ng-zorro-antd';
import {HttpErrorResponse} from '@angular/common/http';
import {AbstractTableView} from '../../../../domain/abstract-table-view';
import {EditEntity} from '../../../../domain/edit-entity';

@Component({
  selector: 'app-event-category',
  templateUrl: './event-category.component.html',
  styleUrls: ['./event-category.component.css']
})
export class EventCategoryComponent extends AbstractTableView<EventCategory>
  implements OnInit, EditEntity<EventCategory> {

  protected selectedEntity: EventCategory;
  protected editAllowed: boolean;

  constructor(private restService: RestService, private notification: NzNotificationService) {
    super();
  }

  setFilterColumns() {
    this.filteringValues = {eventCategoryName: [{}], approved: [{}], requestedBy: [{}], approvedBy: [{}]};
  }

  loadRequestedEventCategories() {
    this.loading = true;
    this.restService.getRequestedEventCategories().subscribe(value => {
      this.loading = false;
      this.setData(value.data);
    }, (error: HttpErrorResponse) => {
      this.notification.error('Error Loading', error.error);
      this.loading = false;
    });
  }

  ngOnInit() {
    this.setFilterColumns();
    this.loadRequestedEventCategories();
  }

  editEventCategory(eventCategory: EventCategory) {
    this.selectedEntity = eventCategory;
    this.editAllowed = true;
  }

  updateEntity(updatedEC: EventCategory) {
    this.loadRequestedEventCategories();
  }

  cancelChanges() {
    this.selectedEntity = null;
    this.editAllowed = false;
    this.loadRequestedEventCategories();
  }

  createEntity() {
    this.selectedEntity = {eventCategoryId: 0, eventCategoryName: '', approvedBy: '', requestedBy: '', approved: false};
    this.editAllowed = true;
  }

  deleteEntity(eventCategory: EventCategory) {
  }

}
