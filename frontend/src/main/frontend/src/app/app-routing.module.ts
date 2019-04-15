import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { SoftwareCertificatesComponent } from './components/software-certificates/software-certificates.component';
import { AuthGuard } from './shared/guards/auth.guard';
import { AnonymousGuard } from './shared/guards/anonymous.guard';
import { AdminGuard } from './shared/guards/admin.guard';
import {RevokeCertificateComponent} from "./components/revoke-certificate/revoke-certificate.component";
import { ManageAccessComponent } from './components/manage-access/manage-access.component';
import { EditAccessComponent } from './components/edit-access/edit-access.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
  { path: 'login', component: LoginComponent, canActivate: [AnonymousGuard]},
  { path: 'software-certificates', component: SoftwareCertificatesComponent, canActivate: [AdminGuard]},
  { path: 'revoke-certificate', component: RevokeCertificateComponent, canActivate: [AuthGuard]},
  { path: 'manage-access', component: ManageAccessComponent, canActivate: [AdminGuard]},
  { path: 'edit-access', component: EditAccessComponent, canActivate: [AdminGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
