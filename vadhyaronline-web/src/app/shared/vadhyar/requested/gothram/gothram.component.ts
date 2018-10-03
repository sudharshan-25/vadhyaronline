import {Component, OnInit} from '@angular/core';
import {Gothram} from '../../../../domain/domain';
import {RestService} from '../../../../services/rest.service';
import {NzNotificationService} from 'ng-zorro-antd';
import {HttpErrorResponse} from '@angular/common/http';
import {AbstractTableView} from '../../../../domain/abstract-table-view';
import {EditEntity} from '../../../../domain/edit-entity';

@Component({
  selector: 'app-gothram',
  templateUrl: './gothram.component.html',
  styleUrls: ['./gothram.component.css']
})
export class GothramComponent extends AbstractTableView<Gothram> implements OnInit, EditEntity<Gothram> {

  protected selectedEntity: Gothram;
  protected editAllowed: boolean;

  constructor(private restService: RestService, private notification: NzNotificationService) {
    super();
  }

  setFilterColumns() {
    this.filteringValues = {gothramName: [{}], approved: [{}], requestedBy: [{}], approvedBy: [{}]};
  }

  public loadRequestedGothrams() {
    this.restService.getRequestedGothrams().subscribe(value => {
      this.loading = false;
      this.setData(value.data);
    }, (error: HttpErrorResponse) => {
      this.notification.error('Error Loading', error.error);
      this.loading = false;
    });
  }

  ngOnInit() {
    this.setFilterColumns();
    this.loadRequestedGothrams();
  }

  editGothram(gothram: Gothram) {
    this.selectedEntity = gothram;
    this.editAllowed = true;
  }

  updateEntity(updatedGothram: Gothram) {
    this.loadRequestedGothrams();
  }

  cancelChanges() {
    this.selectedEntity = null;
    this.editAllowed = false;
    this.loadRequestedGothrams();
  }

  createEntity() {
    this.selectedEntity = {gothramId: 0, gothramName: '', approvedBy: '', requestedBy: '', approved: false};
    this.editAllowed = true;
  }

  deleteEntity(gothram: Gothram) {
  }
}
