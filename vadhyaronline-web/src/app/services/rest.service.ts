import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {LoginResponse} from '../domain/domain';

@Injectable({
  providedIn: 'root'
})
export class RestService {

  private apiURL = 'http://localhost:8080/vadhyaronline';

  constructor(private httpClient: HttpClient) {
  }

  public login(userName: string, password: string): Observable<LoginResponse> {
    return this.httpClient.get<LoginResponse>(`${this.apiURL}/user/login?userName=${userName}&password=${password}`);
  }


}
