import {NestedTreeControl} from '@angular/cdk/tree';
import {Component, OnInit} from '@angular/core';
import {MatTreeNestedDataSource} from '@angular/material/tree';
import { CertificatesService } from '../../services/certificates.service';

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
    private certificatesService: CertificatesService
  ) {
    this.dataSource.data = [];
  }

  ngOnInit() {
    this.getAllData()
  }

  hasChild = (_: number, node: ThreeNode) => !!node.children && node.children.length > 0;

  addCertificate(id) {
    console.log(id);
    this.certificatesService.addCertificate(id);
  }

  getAllData(): void {
    let responseData = [];
    this.certificatesService.getAllData().subscribe((res : any[]) => {
      responseData = res;
      responseData.forEach((city) => {
        city.canAddCertificate = true
        city.children = city.offices;
        delete city.offices;
        city.children.forEach((office) => {
          office.canAddCertificate = city.hasCert;
          office.children = office.softwares;
          delete office.softwares;
          office.children.forEach((software) => {
            software.canAddCertificate = office.hasCert;
          })
        });
      })
      console.log(responseData);
      this.dataSource.data = responseData;
    });
  }
}
