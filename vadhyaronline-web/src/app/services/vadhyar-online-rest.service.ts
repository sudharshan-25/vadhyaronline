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
    return this.httpClient.get<VadhyarResponse>(`${this.apiURL}/user/login?userName=${userName}&password=${password}`);
  }

  public register(user: any): Observable<VadhyarResponse> {
    return this.httpClient.post<VadhyarResponse>(`${this.apiURL}/user/register`, user, {});
  }

  public getRoles(): Observable<VadhyarResponse> {
    return this.httpClient.get<VadhyarResponse>(`${this.apiURL}/master/userRoles`);
  }

  public getUser(userId: number): Observable<VadhyarResponse> {
    return this.httpClient.get<VadhyarResponse>(`${this.apiURL}/user/${userId}`);
  }

  public updateUser(userId: number, userDetails: any): Observable<VadhyarResponse> {
    return this.httpClient.put<VadhyarResponse>(`${this.apiURL}/user/${userId}`, userDetails);
  }

}
