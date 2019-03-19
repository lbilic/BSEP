import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";
import { Injectable } from "@angular/core";
import { JwtService } from '../../services/auth/jwt.service';

@Injectable()
export class AdminGuard implements CanActivate {

  constructor(private router: Router, private jwtService: JwtService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if(!this.jwtService.tokenExist()){
      this.router.navigate(['login'], { queryParams: { returnUrl: state.url }});
    }
    if(this.jwtService.hasRole('ADMIN')) return true;

    // logged user hasn't role ADMIN
    this.router.navigate(['home']);
    return false;
  }
}
