import {Component, OnInit} from '@angular/core';
import {EventType, Gothram} from '../../../../domain/domain';
import {RestService} from '../../../../services/rest.service';
import {NzNotificationService} from 'ng-zorro-antd';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-gothram',
  templateUrl: './gothram.component.html',
  styleUrls: ['./gothram.component.css']
})
export class GothramComponent implements OnInit {

  data: Array<Gothram> = [];
  loading: boolean;

  constructor(private restService: RestService, private notification: NzNotificationService) {
  }

  public loadAllGothrams() {
    this.restService.getUnapprovedGothrams().subscribe(value => {
      this.loading = false;
      this.data = value.data;
    }, (error: HttpErrorResponse) => {
      this.notification.error('Error Loading', error.error);
      this.loading = false;
    });
  }

  ngOnInit() {
    this.loadAllGothrams();
  }

}
