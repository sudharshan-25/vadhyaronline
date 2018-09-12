import {EventEmitter, Injectable} from '@angular/core';
import {LoginUser} from '../domain/domain';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private router: Router) {
  }

  private loginEvent: EventEmitter<boolean> = new EventEmitter();

  isUserLoggedIn(): boolean {
    const loginUserString = localStorage.getItem('loggedInUser');
    if (loginUserString !== null) {
      try {
        JSON.parse(loginUserString);
        return true;
      } catch (e) {
        return false;
      }
    }
    return false;
  }

  getUser(): LoginUser {
    if (this.isUserLoggedIn()) {
      const loginUserString = localStorage.getItem('loggedInUser');
      return JSON.parse(loginUserString);
    }
    this.router.navigate(['/login'], {queryParams: {'page': 'Unauthorized'}});
    // throw new Error('No Logged in user');
  }

  removeUserSession(): void {
    localStorage.clear();
    this.loginEvent.emit(false);
  }

  addUser(user: LoginUser) {
    localStorage.setItem('loggedInUser', JSON.stringify(user));
    this.loginEvent.emit(true);
  }

  public emitLoginEvent(): EventEmitter<boolean> {
    return this.loginEvent;
  }
}
