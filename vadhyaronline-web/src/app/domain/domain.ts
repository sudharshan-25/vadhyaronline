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

export interface DropDownChoices {
  key: number;
  value: string;
  criteriaId: number;
  criteriaValue: string;
}

export interface ChoiceResponse {
  data: DropDownChoices[];
}

export interface EventCategory {
  eventCategoryId: number;
  eventCategoryName: string;
  approved: boolean;
  requestedBy: string;
  approvedBy: string;
}

export interface EventCategoryResponse {
  data: EventCategory[];
}
