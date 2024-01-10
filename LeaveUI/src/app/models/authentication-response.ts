export interface AuthenticationResponse {
  id: number;
  access_token: string;
  refresh_token: string;
  firstname: string;
  email: string;
  statusCodeValue: number;
}
