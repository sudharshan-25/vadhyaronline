import { Component, OnInit } from '@angular/core';
import {Soothram} from '../../../../domain/domain';
import {RestService} from '../../../../services/rest.service';
import {NzNotificationService} from 'ng-zorro-antd';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-soothram',
  templateUrl: './soothram.component.html',
  styleUrls: ['./soothram.component.css']
})
export class SoothramComponent implements OnInit {


  data: Array<Soothram> = [];
  loading: boolean;

  constructor(private restService: RestService, private notification: NzNotificationService) {
  }

  public loadRequestedSoothrams() {
    this.restService.getRequestedSoothrams().subscribe(value => {
      this.loading = false;
      this.data = value.data;
    }, (error: HttpErrorResponse) => {
      this.notification.error('Error Loading', error.error);
      this.loading = false;
    });
  }

  ngOnInit() {
    this.loadRequestedSoothrams();
  }

}
