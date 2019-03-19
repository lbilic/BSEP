import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";
import { Injectable } from "@angular/core";
import { JwtService } from '../../services/auth/jwt.service';

@Injectable()
export class AnonymousGuard implements CanActivate {JwtServiceJwtSerJwtService

  constructor(private router: Router, private jwtService: JwtService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if(!this.jwtService.tokenExist() || this.jwtService.isTokenExpired()) return true;

    // user is already logged in so redirect him to home page
    this.router.navigate(['home']);
    return false;
  }
} 