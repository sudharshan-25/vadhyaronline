export class LoginUser {

  constructor(public userId: number, public userName: string, public role: string, public token: string) {
  }

  static parseUser(userString: string): LoginUser {
    try {
      const {userId, userName, role, token} = JSON.parse(userString);
      return new LoginUser(userId, userName, role, token);
    } catch (e) {
      throw new Error('Could not parse the user');
    }
  }
}
