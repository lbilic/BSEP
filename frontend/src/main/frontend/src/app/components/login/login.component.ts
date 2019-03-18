import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Login } from '../../models/login';
import { AppError } from '../../shared/errors/app-error';
import { BadRequestError } from '../../shared/errors/bad-request-error';
import { ForbiddenError } from '../../shared/errors/forbidden-error';
import { NotFoundError } from '../../shared/errors/not-found-error';
import { ToasterService } from 'angular2-toaster';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [AuthService, ToasterService]
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  returnUrl: string;

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router,
              private toasterService: ToasterService,
              private authService: AuthService) {
                this.loginForm = this.formBuilder.group({
                  username: ['', Validators.required],
                  password: ['', Validators.required]
                });
               }

  ngOnInit() {
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  get username() {
    return this.loginForm.get('username');
  }

  get password() {
    return this.loginForm.get('password');
  }

  login() {
    let login = new Login(this.username.value, this.password.value);

    // stop here if form is invalid
    if (this.loginForm.invalid) {
        return;
    }

    this.authService.login(login)
      .subscribe((successfullyLoggedIn) => {
        if(successfullyLoggedIn) {
          this.router.navigateByUrl(this.returnUrl);
        }else
          this.toasterService.pop('error', 'Error', 'Invalid login');
      }, (error: AppError) => {
        if (error instanceof BadRequestError)
          this.toasterService.pop('error', 'Error','Invalid format of given data!');
        else if (error instanceof ForbiddenError)
          this.toasterService.pop('error', 'Error', 'Account not confirmed!');
        else if (error instanceof NotFoundError)
          this.toasterService.pop('error', 'Error', 'Bad credentials!');
        else {
          this.toasterService.pop('error', 'Error', 'Something unexpected happened! \nSee information about error in console.');
          throw error;
        }
      });
  }

}
