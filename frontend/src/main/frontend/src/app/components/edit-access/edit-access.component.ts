import { Component, OnInit } from '@angular/core';
import { SistemUserService } from 'src/app/services/sistem-user.service';
import { Router } from '@angular/router';
import {CdkDragDrop, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';

@Component({
  selector: 'app-edit-access',
  templateUrl: './edit-access.component.html',
  styleUrls: ['./edit-access.component.css']
})
export class EditAccessComponent implements OnInit {
  public modify_types = [];
  public selected_type;
  public modify_data = [];
  public selected_data;
  public roles = [];
  public users = [];

  public left_side = [];
  public right_side = [];

  constructor(private systemUserService: SistemUserService,private router: Router) { 
    systemUserService.getUsers().subscribe(
      data => {
        this.users = JSON.parse(data as string);
        }
    )
    systemUserService.getRoles().subscribe(
      data => {
        this.roles = JSON.parse(data as string);
      }
    )

    this.modify_types = ["Users","Roles"];

  }

  typeChange(){
    if(this.selected_type == "Users"){
      this.modify_data = this.users;
    }else{
      this.modify_data = this.roles;
    }
    this.selected_data = undefined
  }

  dataChange(){
    if(this.selected_type == "Users"){
      this.systemUserService.getUserRoles(this.selected_data).subscribe(
        data => {
          this.left_side = JSON.parse(data as string)["owned"];
          this.right_side = JSON.parse(data as string)["others"];
        }
      )
    }else{
      this.systemUserService.getRolePermissions(this.selected_data).subscribe(
        data => {
          this.left_side = JSON.parse(data as string)["owned"];
          this.right_side = JSON.parse(data as string)["others"];
          console.log(this.left_side);
        }
      )

    }
  }

  saveChanges(){
    if(this.selected_type == "Users"){
      this.systemUserService.updateUserRoles(this.selected_data,this.left_side).subscribe(
        data => {
          alert(data);
        }
      )
    }else{
      this.systemUserService.updateRolePermissions(this.selected_data,this.left_side).subscribe(
        data => {
          alert(data);
        }
      )
    }
  }

  drop(event: CdkDragDrop<string[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(event.previousContainer.data,
                        event.container.data,
                        event.previousIndex,
                        event.currentIndex);
    }
  }

  ngOnInit() {
  }

}
