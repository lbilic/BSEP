import { Component, OnInit } from '@angular/core';
import { JwtService } from '../../services/auth/jwt.service';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';
import { BsModalService, BsDropdownDirective } from 'ngx-bootstrap';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
  providers: [JwtService, AuthService, BsModalService, BsDropdownDirective]
})
export class NavbarComponent implements OnInit {

  constructor(protected jwtService: JwtService, private authService: AuthService,
    private router: Router) { }

  ngOnInit() {
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['login']);
  }

  notOnHomeRoute(): boolean {
    return this.router.url !== '/home';
  }

  notOnRegisterBuildingRoute(): boolean{
    return this.router.url !== '/register';
  }

}
