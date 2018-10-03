import {Component, OnInit} from '@angular/core';
import {Soothram} from '../../../../domain/domain';
import {RestService} from '../../../../services/rest.service';
import {NzNotificationService} from 'ng-zorro-antd';
import {HttpErrorResponse} from '@angular/common/http';
import {AbstractTableView} from '../../../../domain/abstract-table-view';

@Component({
  selector: 'app-soothram',
  templateUrl: './soothram.component.html',
  styleUrls: ['./soothram.component.css']
})
export class SoothramComponent extends AbstractTableView<Soothram> implements OnInit {

  constructor(private restService: RestService, private notification: NzNotificationService) {
    super();
  }

  setFilterColumns() {
    this.filteringValues = {
      soothramName: [{}], approved: [{}], requestedBy: [{}], approvedBy: [{}]
    };
  }

  public loadAllSoothrams() {
    this.restService.getUnapprovedSoothrams().subscribe(value => {
      this.loading = false;
      this.setData(value.data);
    }, (error: HttpErrorResponse) => {
      this.notification.error('Error Loading', error.error);
      this.loading = false;
    });
  }

  ngOnInit() {
    this.setFilterColumns();
    this.loadAllSoothrams();
  }

}
