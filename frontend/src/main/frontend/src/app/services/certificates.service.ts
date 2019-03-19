import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CertificatesService {

  constructor(private http: HttpClient) { }

  addCertificate(id: number) {
    console.log(id);
    return this.http.post(`http://localhost:8080/api/cert/` + id, {});
  }

  getAllData(): Observable<Object> {
    return this.http.get(`http://localhost:8080/api/cert/all-data`);
  }
}
