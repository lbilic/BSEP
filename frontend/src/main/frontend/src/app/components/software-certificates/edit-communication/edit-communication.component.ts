import {Component, Inject, OnInit} from '@angular/core';
import {CertificatesService} from "../../../services/certificates.service";
import {MAT_DIALOG_DATA} from "@angular/material";
import { Software } from 'src/app/models/software';
import { Connected } from 'src/app/models/connected';

@Component({
  selector: 'app-edit-communication',
  templateUrl: './edit-communication.component.html',
  styleUrls: ['./edit-communication.component.css']
})
export class EditCommunicationComponent implements OnInit {

  private certificates: Software[];
  private alias: String;

  constructor(
    private certificatesService: CertificatesService,
    @Inject(MAT_DIALOG_DATA) public data: any) {}

  ngOnInit() {
    this.alias = this.data.alias;
    this.certificates = [];
    let result;
    this.certificatesService.getSoftwares(this.data.alias)
      .subscribe((res) => {
        result = <Connected> res;
        result.connectedWith.forEach((item) => {
          let software = new Software(item, true);
          this.certificates.push(software)
        });
        result.others.forEach((item) => {
          let software = new Software(item, false);
          this.certificates.push(software)
        });
      });
  }

  updateCertificate() {
    let connectedCertificates = this.certificates.filter((item)=>{
      return item.isConnected;
    }).map((item) =>{
      return item.alias;
    });
    let notConnectedCertificates = this.certificates.filter((item)=>{
      return !item.isConnected;
    }).map((item) =>{
      return item.alias;
    });
    console.log(connectedCertificates);
    console.log(notConnectedCertificates);
    this.certificatesService.updateCommunication(this.data.alias, 
      {
        connectedWith: connectedCertificates,
        others: notConnectedCertificates
      });
  }


}
