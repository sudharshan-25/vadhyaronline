export class LoginUser {

  constructor(public userId: number, public firstName: string, public lastName: string,
              public userName: string) {
  }

  static parseUser(userString: string): LoginUser {
    try {
      const {userId, firstName, lastName, userName} = JSON.parse(userString);
      return new LoginUser(userId, firstName, lastName, userName);
    } catch (e) {
      throw new Error('Could not parse the user');
    }
  }
}
