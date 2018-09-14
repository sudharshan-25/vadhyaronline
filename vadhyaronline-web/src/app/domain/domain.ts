export interface LoginUser {
  userId: number;
  userName: string;
  role: string;
  loginToken: string;
}

export interface LoginResponse {
  data: LoginUser;
}

export interface DefaultStringResponse {
  data: string;
}
