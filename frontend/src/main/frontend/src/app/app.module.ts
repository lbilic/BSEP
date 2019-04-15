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
import {
  SoftwareCertificatesComponent
} from './components/software-certificates/software-certificates.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {
  MatButtonModule,
  MatCheckboxModule,
  MatTreeModule,
  MatFormFieldModule,
  MatIconModule,
  MatDialogModule
} from '@angular/material';
import { FormsModule } from '@angular/forms';
import { AnonymousGuard } from './shared/guards/anonymous.guard';
import { AuthGuard } from './shared/guards/auth.guard';
import { AdminGuard } from './shared/guards/admin.guard';
import { RevokeCertificateComponent } from './components/revoke-certificate/revoke-certificate.component';
import {JwtInterceptor} from "./shared/interceptors/JwtInterceptor";
import { ShowCertificateComponent } from './components/revoke-certificate/show-certificate/show-certificate.component';
import { EditCommunicationComponent } from './components/software-certificates/edit-communication/edit-communication.component';
import { ManageAccessComponent } from './components/manage-access/manage-access.component';
import { EditAccessComponent } from './components/edit-access/edit-access.component';
import { DragDropModule } from '@angular/cdk/drag-drop';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    NavbarComponent,
    RevokeCertificateComponent,
    ShowCertificateComponent,
    SoftwareCertificatesComponent,
    EditCommunicationComponent,
    ManageAccessComponent,
    EditAccessComponent
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
    MatDialogModule,
    FormsModule,
    DragDropModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    },
    JwtService, CertificatesService, AnonymousGuard, AuthGuard, AdminGuard
  ],
  bootstrap: [AppComponent],
  entryComponents: [ShowCertificateComponent, EditCommunicationComponent]
})
export class AppModule { }
