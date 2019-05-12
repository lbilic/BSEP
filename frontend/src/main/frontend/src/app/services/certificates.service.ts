import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {catchError, map} from "rxjs/operators";
import {JwtToken} from "../models/jwt-token";
import { Certificate } from '../models/certificate';
import { Connected } from '../models/connected';

@Injectable({
  providedIn: 'root'
})
export class CertificatesService {

  constructor(private http: HttpClient) { }

  addCertificate(certificate: Certificate, alias: String) {
    return this.http.post(`http://localhost:8080/api/cert/` + alias, certificate, {responseType: 'text'}).subscribe(
      data =>  {
        if(data==='Success') {
          alert('Certificate successfully generated!');
          window.location.reload();
        } else {
          alert('There has been an error while generating certificate');
        }
      });
  }

  revokeCertificate(alias) {
    return this.http.delete(`http://localhost:8080/api/cert/` + alias, {responseType: 'text'}).subscribe(
      data =>  {
        if(data==='Success') {
          alert('Certificate successfully revoked!');
          window.location.reload();
        } else {
          alert('There has been an error while revoking certificate');
        }
      });
  }

  getAllData(): Observable<Object> {
    return this.http.get(`http://localhost:8080/api/cert/all-data`);
  }

  getSoftwares(alias): Observable<Object> {
    return this.http.get(`http://localhost:8080/api/software/` + alias);
  }

  getCertificate(alias): Observable<Object> {
    return this.http.get(`http://localhost:8080/api/cert/` + alias);
  }

  updateCommunication(alias, certificates) {
    return this.http.post(`http://localhost:8080/api/software/` + alias, certificates, {responseType: 'text'}).subscribe(
      (data) =>  {
        if(data==='Success') {
          alert('Software communication successfully updated!');
        } else {
          alert('There has been an error while generating certificate');
        }
      });
  }

  downloadCertificate(alias) {
    return this.http.get(`http://localhost:8080/api/cert/download/` + alias, {responseType: 'blob'}).subscribe((response) => {
      let blob = new Blob([<Blob>response], { type: 'application/zip'});
      let link = document.createElement('a');
      link.href = window.URL.createObjectURL(blob);
      link.download = alias + '_storage' + '.zip';
      link.click();
    });
  }
}
