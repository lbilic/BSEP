
import {throwError as observableThrowError,  Observable } from 'rxjs';

import {map, catchError} from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from "@angular/common/http";



// models
import { Login } from "../../models/login";
import { JwtToken } from "../../models/jwt-token";
// errors
import { AppError } from "../../shared/errors/app-error";
import { BadRequestError } from "../../shared/errors/bad-request-error";
import { NotFoundError } from "../../shared/errors/not-found-error";
// service
import { JwtService } from "./jwt.service";
import { ForbiddenError } from "../../shared/errors/forbidden-error";

@Injectable()
export class AuthService {
  private readonly urlBase = '/api';

  constructor(private http: HttpClient, private jwtService: JwtService) { }

  login(credentials: Login): Observable<boolean> {
    return this.http.post(`http://localhost:8080/api/login`, credentials).pipe(
      map((token: JwtToken) => {
        if(token.value) {
          this.jwtService.setToken(token.value);
          return true;
        }
        else return false;
      }),catchError(this.handleErrors),);
  }

  logout() {
    this.jwtService.removeToken();
  }

  checkUsername(username: string): Observable<boolean> {
    let params: HttpParams = new HttpParams()
      .append('username', username);
    console.log(username);
    return this.http.get<boolean>(`http://localhost:8080/api/check_username`, {params}).pipe(
      catchError(this.handleErrors));
  }

  currentUser(): Observable<any> {
    return this.http.get<any>(`http://localhost:8080/api/current_user`).pipe(
      catchError(this.handleErrors));
  }

  private handleErrors(response: Response) {
    if(response.status === 400)
      return observableThrowError(new BadRequestError());
    else if(response.status === 404)
      return observableThrowError(new NotFoundError());
    else if(response.status === 403)
      return observableThrowError(new ForbiddenError());
    return observableThrowError(new AppError(response));
  }
}
