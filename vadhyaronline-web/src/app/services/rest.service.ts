import {Inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ChoiceResponse, DefaultStringResponse, DropDownChoices, LoginResponse} from '../domain/domain';

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

  public getRoles(): Observable<ChoiceResponse> {
    return this.httpClient.get<ChoiceResponse>(`${this.apiURL}/choices/role`);
  }

  public registerUser(userDetails: any): Observable<DefaultStringResponse> {
    return this.httpClient.post<DefaultStringResponse>(`${this.apiURL}/user/register`, userDetails, {});
  }

}
