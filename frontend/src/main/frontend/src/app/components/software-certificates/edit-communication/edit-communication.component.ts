import {Component, Inject, OnInit} from '@angular/core';
import {CertificatesService} from "../../../services/certificates.service";
import {MAT_DIALOG_DATA} from "@angular/material";

@Component({
  selector: 'app-edit-communication',
  templateUrl: './edit-communication.component.html',
  styleUrls: ['./edit-communication.component.css']
})
export class EditCommunicationComponent implements OnInit {

  private softwares: [];
  private id: Number;

  constructor(
    private certificatesService: CertificatesService,
    @Inject(MAT_DIALOG_DATA) public data: any) {}

  ngOnInit() {
    this.softwares = this.data.softwares;
    this.id = this.data.id;
    // this.certificate ={};
    // this.certificate = this.certificatesService.getCertificate(this.data)
    //   .subscribe((res : any[]) => {
    //     this.certificate = res;
    //     console.log(res);
    //   });
  }


}
