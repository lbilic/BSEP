import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
// model
import { Token } from "../../models/token";


@Injectable()
export class JwtService {
  private jwtHelper: JwtHelperService;

  constructor() {
    this.jwtHelper = new JwtHelperService();
  }

  tokenExist(): boolean {
    return !!(localStorage.getItem('Authentication-Token'));
  }

  isTokenExpired(): boolean {
    return this.jwtHelper.isTokenExpired(this.getToken());
  }

  hasRole(role: string) {
    return (this.getRolesFromToken().indexOf(role) != -1);
  }

  getRolesFromToken(): Array<string> {
    let tokenDecoded: Token = this.decodeToken();
    return tokenDecoded ? tokenDecoded.roles : Array<string>();
  }

  getUsernameFromToken(): string {
    let tokenDecoded: Token = this.decodeToken();
    return tokenDecoded.sub;
  }

  getIdFromToken(): number {
    let tokenDecoded: Token = this.decodeToken();
    return tokenDecoded.id;
  }

  getToken(): string {
    return localStorage.getItem('Authentication-Token');
  }

  setToken(token: string): void {
    localStorage.setItem('Authentication-Token', token);
  }

  removeToken(): void {
    localStorage.removeItem('Authentication-Token');
  }

  private decodeToken(): Token {
    return this.jwtHelper.decodeToken(this.getToken());
  }
}
