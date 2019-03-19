import { Injectable } from "@angular/core";
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Observable } from "rxjs";
import { JwtService } from '../../services/auth/jwt.service';
// service

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
  constructor(public jwtService: JwtService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if(this.jwtService.tokenExist() && !this.jwtService.isTokenExpired()) {
      req = req.clone({
        setHeaders: {
          'Authentication-Token': this.jwtService.getToken()
        }
      });
    }

    return next.handle(req);
  }
}
