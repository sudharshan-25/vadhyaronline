import {Component, OnInit} from '@angular/core';
import {Gothram} from '../../../../domain/domain';
import {RestService} from '../../../../services/rest.service';
import {NzNotificationService} from 'ng-zorro-antd';
import {HttpErrorResponse} from '@angular/common/http';
import {AbstractTableView} from '../../../../domain/abstract-table-view';

@Component({
  selector: 'app-gothram',
  templateUrl: './gothram.component.html',
  styleUrls: ['./gothram.component.css']
})
export class GothramComponent extends AbstractTableView<Gothram> implements OnInit {

  constructor(private restService: RestService, private notification: NzNotificationService) {
    super
    ();
  }

  public loadAllGothrams() {
    this.restService.getUnapprovedGothrams().subscribe(value => {
      this.loading = false;
      this.setData(value.data);
    }, (error: HttpErrorResponse) => {
      this.notification.error('Error Loading', error.error);
      this.loading = false;
    });
  }

  ngOnInit() {
    this.loadAllGothrams();
  }

}
