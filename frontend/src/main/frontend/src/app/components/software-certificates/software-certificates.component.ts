import { Component, OnInit, Injectable } from '@angular/core';
import { NestedTreeControl} from '@angular/cdk/tree';
import {
  MatTreeNestedDataSource,
  MatDialog,
  MatDialogConfig
} from '@angular/material';
import {CertificatesService} from "../../services/certificates.service";
import {EditCommunicationComponent} from "./edit-communication/edit-communication.component";

interface ThreeNode {
  nameId: string;
  hasCert?: boolean;
  id?: number;
  canAddCertificate?: boolean;
  children?: ThreeNode[];
}

@Component({
  selector: 'app-software-certificates',
  templateUrl: './software-certificates.component.html',
  styleUrls: ['./software-certificates.component.css']
})
export class SoftwareCertificatesComponent implements OnInit{

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

  editCommunication(alias) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.width = '300px';
    dialogConfig.data = { alias: alias };
    this.dialog.open(EditCommunicationComponent, dialogConfig);
  }

  getAllData(): void {
    let responseData = [];
    this.certificatesService.getAllData().subscribe((res : any[]) => {
      responseData = [res];
      this.dataSource.data = responseData;
    });
  }
}
