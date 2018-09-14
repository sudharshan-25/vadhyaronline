import {Inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {DefaultStringResponse, LoginResponse} from '../domain/domain';

@Injectable({
  providedIn: 'root'
})
export class RestService {

  constructor(@Inject('API_URL') private apiURL, private httpClient: HttpClient) {
  }

  public login(userName: string, password: string): Observable<LoginResponse> {
    return this.httpClient.get<LoginResponse>(`${this.apiURL}/user/login?userName=${userName}&password=${password}`);
  }

  public logout(): Observable<DefaultStringResponse> {
    return this.httpClient.post<DefaultStringResponse>(`${this.apiURL}/user/logout`, null, {});
  }

}
