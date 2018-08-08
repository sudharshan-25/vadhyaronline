export class LoginUser {

  constructor(public userId: number, public firstName: string, public lastName: string,
              public userName: string, public token: string) {
  }

  static parseUser(userString: string): LoginUser {
    try {
      const {userId, firstName, lastName, userName, token} = JSON.parse(userString);
      return new LoginUser(userId, firstName, lastName, userName, token);
    } catch (e) {
      throw new Error('Could not parse the user');
    }
  }
}
