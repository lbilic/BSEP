import { Component, Inject, OnInit } from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material';
import {CertificatesService} from "../../../services/certificates.service";
import { Certificate } from 'src/app/models/certificate';

@Component({
  selector: 'app-add-certificate',
  templateUrl: './add-certificate.component.html',
  styleUrls: ['./add-certificate.component.css']
})
export class AddCertificateComponent implements OnInit {

  private certificate: Certificate;

  constructor(private certificatesService: CertificatesService,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {
    this.certificate = new Certificate();
    console.log(this.data);
    this.certificate.isSoftware = this.data.type === 'CA' ? false : true;
  }

  addCertificate() {
    if (this.certificate.alias &&
        this.certificate.countryName &&
        this.certificate.dateIssued &&
        this.certificate.endDate &&
        this.certificate.locality) {
      console.log(this.certificate);
      this.certificatesService.addCertificate(this.certificate, this.data.alias);
    }
  }
}
