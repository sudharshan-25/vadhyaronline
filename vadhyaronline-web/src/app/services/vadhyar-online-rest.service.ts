import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {VadhyarResponse} from '../domain/vadhyar-response';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class VadhyarOnlineRestService {

  private apiURL = 'http://localhost:8080/vadhyaronline';

  constructor(private httpClient: HttpClient) {
  }

  public login(userName: string, password: string): Observable<VadhyarResponse> {
    const url = `${this.apiURL}/user/login?userName=${userName}&password=${password}`;
    return this.httpClient.get<VadhyarResponse>(url);
  }

}
