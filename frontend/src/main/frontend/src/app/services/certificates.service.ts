import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {catchError, map} from "rxjs/operators";
import {JwtToken} from "../models/jwt-token";

@Injectable({
  providedIn: 'root'
})
export class CertificatesService {

  constructor(private http: HttpClient) { }

  addCertificate(id: number) {
    console.log(id);
    return this.http.post(`http://localhost:8080/api/cert/` + id, {}, {responseType: 'text'}).subscribe(
      data =>  {
        if(data==='Success') {
          alert('Certificate successfully generated!');
        } else {
          alert('There has been an error while generating certificate');
        }
      });
  }

  deleteCertificate(id: number) {
    console.log(id);
    return this.http.delete(`http://localhost:8080/api/cert/` + id).toPromise();
  }

  getAllData(): Observable<Object> {
    return this.http.get(`http://localhost:8080/api/cert/all-data`);
  }
}
