import {Inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {
  ChoiceResponse,
  DefaultStringResponse,
  EventCategory,
  EventCategoryResponse,
  EventType,
  EventTypeResponse,
  GothramResponse,
  LoginResponse,
  SoothramResponse
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

  public registerUser(userDetails: any): Observable<DefaultStringResponse> {
    return this.httpClient.post<DefaultStringResponse>(`${this.apiURL}/user/register`, userDetails, {});
  }

  /**
   * Drop down choices
   */
  public getRoles(): Observable<ChoiceResponse> {
    return this.httpClient.get<ChoiceResponse>(`${this.apiURL}/choices/role`);
  }

  /**
   * Event Category
   */
  public getUnapprovedEventCategories(): Observable<EventCategoryResponse> {
    return this.httpClient.get<EventCategoryResponse>(`${this.apiURL}/eventCategory/unapproved`);
  }

  public getRequestedEventCategories(): Observable<EventCategoryResponse> {
    return this.httpClient.get<EventCategoryResponse>(`${this.apiURL}/eventCategory/requested`);
  }

  public updateEventCategory(eventCategory: EventCategory): Observable<DefaultStringResponse> {
    return this.httpClient.put<DefaultStringResponse>(`${this.apiURL}/eventCategory/${eventCategory.eventCategoryId}`, eventCategory);
  }

  public createEventCategory(eventCategory: EventCategory): Observable<DefaultStringResponse> {
    return this.httpClient.post<DefaultStringResponse>(`${this.apiURL}/eventCategory/`, eventCategory);
  }

  public deleteEventCategory(eventCategory: EventCategory): Observable<DefaultStringResponse> {
    return this.httpClient.delete<DefaultStringResponse>(`${this.apiURL}/eventCategory/${eventCategory.eventCategoryId}`);
  }

  public approveEventCategory(eventCategory: EventCategory): Observable<DefaultStringResponse> {
    return this.httpClient.put<DefaultStringResponse>(`${this.apiURL}/eventCategory/${eventCategory.eventCategoryId}/approve`, null);
  }

  /**
   * Event Type
   */
  public getUnapprovedEventTypes(): Observable<EventTypeResponse> {
    return this.httpClient.get<EventTypeResponse>(`${this.apiURL}/eventType/unapproved`);
  }

  public getRequestedEventTypes(): Observable<EventTypeResponse> {
    return this.httpClient.get<EventTypeResponse>(`${this.apiURL}/eventType/requested`);
  }

  public updateEventType(eventType: EventType): Observable<DefaultStringResponse> {
    return this.httpClient.put<DefaultStringResponse>(`${this.apiURL}/eventType/${eventType.eventTypeId}`, eventType);
  }

  public createEventType(eventType: EventType): Observable<DefaultStringResponse> {
    return this.httpClient.post<DefaultStringResponse>(`${this.apiURL}/eventType/`, eventType);
  }

  public deleteEventType(eventType: EventType): Observable<DefaultStringResponse> {
    return this.httpClient.delete<DefaultStringResponse>(`${this.apiURL}/eventType/${eventType.eventTypeId}`);
  }

  public approveEventType(eventType: EventType): Observable<DefaultStringResponse> {
    return this.httpClient.put<DefaultStringResponse>(`${this.apiURL}/eventType/${eventType.eventTypeId}/approve`, null);
  }

  /**
   * Gothram
   */
  public getUnapprovedGothrams(): Observable<GothramResponse> {
    return this.httpClient.get<GothramResponse>(`${this.apiURL}/gothram/unapproved`);
  }

  public getRequestedGothrams(): Observable<GothramResponse> {
    return this.httpClient.get<GothramResponse>(`${this.apiURL}/gothram/requested`);
  }

  /**
   * Soothram
   */
  public getUnapprovedSoothrams(): Observable<SoothramResponse> {
    return this.httpClient.get<SoothramResponse>(`${this.apiURL}/soothram/unapproved`);
  }

  public getRequestedSoothrams(): Observable<SoothramResponse> {
    return this.httpClient.get<SoothramResponse>(`${this.apiURL}/soothram/requested`);
  }

}
