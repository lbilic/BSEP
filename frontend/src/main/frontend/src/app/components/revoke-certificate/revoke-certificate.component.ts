import { Component, OnInit } from '@angular/core';
import {CertificatesService} from "../../services/certificates.service";
import {NestedTreeControl} from "@angular/cdk/tree";
import {MatDialog, MatDialogConfig, MatTreeNestedDataSource} from "@angular/material";
import {MAT_DIALOG_DATA} from '@angular/material';
import {ShowCertificateComponent} from "./show-certificate/show-certificate.component";

interface ThreeNode {
  nameId: string;
  hasCert?: boolean;
  id?: number;
  canAddCertificate?: boolean;
  children?: ThreeNode[];
}

@Component({
  selector: 'app-revoke-certificate',
  templateUrl: './revoke-certificate.component.html',
  styleUrls: ['./revoke-certificate.component.css']
})
export class RevokeCertificateComponent implements OnInit {

  treeControl = new NestedTreeControl<ThreeNode>(node => node.children);
  dataSource = new MatTreeNestedDataSource<ThreeNode>();

  constructor(
    private certificatesService: CertificatesService,
    private dialog: MatDialog
  ) {
    this.dataSource.data = [];
  }

  ngOnInit() {
    this.getAllData()
  }

  hasChild = (_: number, node: ThreeNode) => !!node.children && node.children.length > 0;

  deleteCertificate(id) {
    this.certificatesService.deleteCertificate(id);
  }

  showCertificate(id) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.data = id;
    this.dialog.open(ShowCertificateComponent, dialogConfig);
  }

  getAllData(): void {
    let responseData = [];
    this.certificatesService.getAllData().subscribe((res : any[]) => {
      responseData = [res];
      this.dataSource.data = responseData;
    });
  }

  download(alias) {
    this.certificatesService.downloadCertificate(alias);
  }
}
