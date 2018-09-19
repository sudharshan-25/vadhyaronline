import {Inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {
  ChoiceResponse,
  DefaultStringResponse,
  EventCategoryResponse,
  EventTypeResponse,
  GothramResponse,
  LoginResponse, SoothramResponse
} from '../domain/domain';

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

  public getAllEventCategories(): Observable<EventCategoryResponse> {
    return this.httpClient.get<EventCategoryResponse>(`${this.apiURL}/eventCategory/all`);
  }

  public getAllEventTypes(): Observable<EventTypeResponse> {
    return this.httpClient.get<EventTypeResponse>(`${this.apiURL}/eventType/all`);
  }

  public getAllGothrams(): Observable<GothramResponse> {
    return this.httpClient.get<GothramResponse>(`${this.apiURL}/gothram/all`);
  }

  public getAllSoothrams(): Observable<SoothramResponse> {
    return this.httpClient.get<SoothramResponse>(`${this.apiURL}/soothram/all`);
  }


}
