export interface LoginUser {
  userId: number;
  firstName: string;
  lastName: string;
  userName: string;
  role: string;
}

export interface LoginResponse {
  data: LoginUser;
}
