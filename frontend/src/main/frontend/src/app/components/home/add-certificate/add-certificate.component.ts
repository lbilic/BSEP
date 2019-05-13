import { Component, Inject, OnInit } from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material';
import {CertificatesService} from "../../../services/certificates.service";
import { Certificate } from 'src/app/models/certificate';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-add-certificate',
  templateUrl: './add-certificate.component.html',
  styleUrls: ['./add-certificate.component.css']
})
export class AddCertificateComponent implements OnInit {

  private certificate: Certificate;
  form: FormGroup;
  countryNameCtrl: FormControl;
  aliasCtrl: FormControl;
  dateIssuedCtrl: FormControl;
  endDateCtrl: FormControl;
  localityCtrl: FormControl;
  stateNameCtrl: FormControl;
  length: Boolean = false;

  constructor(
    private fb: FormBuilder,
    private certificatesService: CertificatesService,
    @Inject(MAT_DIALOG_DATA) public data: any) {
    this.certificate = new Certificate();
    this.certificate.isSoftware = this.data.type === 'CA' ? false : true;
    this.aliasCtrl = this.fb.control([this.certificate.alias, Validators.required]);
    this.countryNameCtrl = this.fb.control([this.certificate.countryName, [Validators.required, Validators.minLength(2),  Validators.maxLength(2)]]);
    this.dateIssuedCtrl = this.fb.control([this.certificate.dateIssued, Validators.required]);
    this.endDateCtrl = this.fb.control([this.certificate.endDate, Validators.required]);
    this.localityCtrl = this.fb.control([this.certificate.locality, Validators.required]);
    this.stateNameCtrl = this.fb.control([this.certificate.stateName, Validators.required]);

    this.form = this.fb.group({
      alias: this.aliasCtrl,
      countryName: this.countryNameCtrl,
      dateIssued: this.dateIssuedCtrl,
      endDate: this.endDateCtrl,
      locality: this.localityCtrl,
      stateName: this.stateNameCtrl
    });
    }

  ngOnInit() {
    
  }

  addCertificate() {
    if (this.certificate.countryName.length !=2 ){
      this.length = true;
      return;
    }
    this.certificatesService.addCertificate(this.certificate, this.data.alias);
  }
}
