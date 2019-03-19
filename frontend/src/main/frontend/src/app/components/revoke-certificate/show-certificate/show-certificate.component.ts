import {Component, Inject, OnInit} from '@angular/core';
import {MatDialog, MAT_DIALOG_DATA} from '@angular/material';
import {CertificatesService} from "../../../services/certificates.service";

@Component({
  selector: 'app-show-certificate',
  templateUrl: './show-certificate.component.html',
  styleUrls: ['./show-certificate.component.css']
})
export class ShowCertificateComponent implements OnInit {

  private certificate;

  constructor(
    private certificatesService: CertificatesService,
    @Inject(MAT_DIALOG_DATA) public data: any) {}

  ngOnInit() {
    console.log(this.data);
    this.certificate ={};
    this.certificate = this.certificatesService.getCertificate(this.data)
      .subscribe((res : any[]) => {
        this.certificate = res;
        console.log(res);
      });
  }

}
