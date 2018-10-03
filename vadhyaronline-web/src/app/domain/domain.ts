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

export interface EventType {
  eventTypeId: number;
  eventTypeName: string;
  eventTypeDescription: string;
  eventCategoryId: number;
  eventCategoryName: string;
  approved: boolean;
  requestedBy: string;
  approvedBy: string;
}

export interface EventTypeResponse {
  data: EventType[];
}

export interface Gothram {
  gothramId: number;
  gothramName: string;
  approved: boolean;
  requestedBy: string;
  approvedBy: string;
}

export interface GothramResponse {
  data: Gothram[];
}

export interface Soothram {
  soothramId: number;
  soothramName: string;
  approved: boolean;
  requestedBy: string;
  approvedBy: string;
}

export interface SoothramResponse {
  data: Soothram[];
}
