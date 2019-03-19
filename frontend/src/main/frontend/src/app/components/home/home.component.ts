import {NestedTreeControl} from '@angular/cdk/tree';
import {Component, OnInit} from '@angular/core';
import {MatTreeNestedDataSource} from '@angular/material/tree';
import { CertificatesService } from '../../services/certificates.service';
import {Observable} from "rxjs";

interface ThreeNode {
  nameId: string;
  hasCert?: boolean;
  id?: number;
  canAddCertificate?: boolean;
  children?: ThreeNode[];
}

const TREE_DATA: ThreeNode[] = [
  {
    nameId: 'Boston',
    children: [
      {
        nameId: 'Office1',
        hasCert: true,
        children: [
          { nameId: 'Software1'},
          { nameId: 'Software2'},
          { nameId: 'Software3'}
        ]
      },
      {
        nameId: 'Office2',
        hasCert: false,
        canAddCertificate: true,
        id: 1,
        children: [
          { nameId: 'Software1',
            hasCert: true},
          { nameId: 'Software2'},
          { nameId: 'Software3'}
        ]
      },
      {
        nameId: 'Office3',
        children: [
          { nameId: 'Software1'},
          { nameId: 'Software2'},
          { nameId: 'Software3'}
        ]
      }
    ]
  },
  {
    nameId: 'Boston',
    children: [
      {
        nameId: 'Office1',
        hasCert: false,
        children: [
          { nameId: 'Software1'},
          { nameId: 'Software2'},
          { nameId: 'Software3'}
        ]
      },
      {
        nameId: 'Office2',
        children: [
          { nameId: 'Software1'},
          { nameId: 'Software2'},
          { nameId: 'Software3'}
        ]
      },
      {
        nameId: 'Office3',
        children: [
          { nameId: 'Software1'},
          { nameId: 'Software2'},
          { nameId: 'Software3'}
        ]
      }
    ]
  },
];

interface ExampleFlatNode {
  expandable: boolean;
  name: string;
  level: number;
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
        city.canAddCertificate = false
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
