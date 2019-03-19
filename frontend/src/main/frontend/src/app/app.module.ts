import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { JwtService } from './services/auth/jwt.service';
import { CertificatesService } from './services/certificates.service';
import { SoftwareCertificatesComponent, ChecklistDatabase } from './components/software-certificates/software-certificates.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {
  MatButtonModule,
  MatCheckboxModule,
  MatTreeModule,
  MatFormFieldModule,
  MatIconModule,
  MatDialogModule
} from '@angular/material';
import { AnonymousGuard } from './shared/guards/anonymous.guard';
import { AuthGuard } from './shared/guards/auth.guard';
import { AdminGuard } from './shared/guards/admin.guard';
import { RevokeCertificateComponent } from './components/revoke-certificate/revoke-certificate.component';
import {JwtInterceptor} from "./shared/interceptors/JwtInterceptor";
import { ShowCertificateComponent } from './components/revoke-certificate/show-certificate/show-certificate.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    NavbarComponent,
    SoftwareCertificatesComponent,
    RevokeCertificateComponent,
    ShowCertificateComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BsDropdownModule.forRoot(),
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCheckboxModule,
    MatTreeModule,
    MatFormFieldModule,
    MatIconModule,
    MatDialogModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    },
    JwtService, ChecklistDatabase, CertificatesService, AnonymousGuard, AuthGuard, AdminGuard
  ],
  bootstrap: [AppComponent],
  entryComponents: [ShowCertificateComponent]
})
export class AppModule { }
