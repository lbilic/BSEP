import {NestedTreeControl} from '@angular/cdk/tree';
import {Component, OnInit} from '@angular/core';
import {MatTreeNestedDataSource} from '@angular/material/tree';
import { CertificatesService } from '../../services/certificates.service';
import {MatDialog, MatDialogConfig} from "@angular/material";
import { AddCertificateComponent } from './add-certificate/add-certificate.component';

interface ThreeNode {
  nameId: string;
  hasCert?: boolean;
  id?: number;
  canAddCertificate?: boolean;
  children?: ThreeNode[];
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [CertificatesService]
})
export class HomeComponent implements OnInit{
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

  addCertificateModal(type, alias) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.data = {type: type, alias: alias};
    dialogConfig.width ='450px';
    this.dialog.open(AddCertificateComponent, dialogConfig);
  }

  getAllData(): void {
    let responseData = [];
    this.certificatesService.getAllData().subscribe((res : any[]) => {
      responseData = [res];
      this.dataSource.data = responseData;
    });
  }
}
