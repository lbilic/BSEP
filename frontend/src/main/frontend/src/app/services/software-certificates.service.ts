import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SoftwareCertificatesService {

  constructor(private http: HttpClient) { }

  public getAllSoftwares() {
    return this.http.get("http://localhost:8080/api/softwares/getall").toPromise();
  }
}
