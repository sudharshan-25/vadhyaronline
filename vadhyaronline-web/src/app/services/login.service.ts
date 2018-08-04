import {Injectable} from '@angular/core';
import {LoginUser} from '../domain/login-user';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor() {
  }

  isUserLoggedIn(): boolean {
    const loginUserString = localStorage.getItem('loggedInUser');
    if (loginUserString !== null) {
      try {
        LoginUser.parseUser(loginUserString);
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
      return LoginUser.parseUser(loginUserString);
    }
    throw new Error('No Logged in user');
  }

  removeUserSession(): void {
    localStorage.clear();
  }

  addUser(user: LoginUser) {
    localStorage.setItem('loggedInUser', JSON.stringify(user));
  }

}
