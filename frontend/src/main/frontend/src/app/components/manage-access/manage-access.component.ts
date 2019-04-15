import { Component, OnInit } from '@angular/core';
import { SistemUserService } from 'src/app/services/sistem-user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-manage-access',
  templateUrl: './manage-access.component.html',
  styleUrls: ['./manage-access.component.css']
})
export class ManageAccessComponent implements OnInit {
  public username;
  public role_name;
  public permission_name;

  public users;
  public roles;
  public permissions;

  public selected_user;
  public selected_role;
  public selected_permission;

  constructor(private systemUserService: SistemUserService,private router: Router) { 
    systemUserService.getUsers().subscribe(
      data => {
        this.users = JSON.parse(data as string);
        console.log(this.users);
          }
    )
    systemUserService.getRoles().subscribe(
      data => {
        this.roles = JSON.parse(data as string);
        console.log(this.roles);
      }
    )
    systemUserService.getPermissions().subscribe(
      data => {
        this.permissions = JSON.parse(data as string);
        console.log(this.permissions);
      }
    )

  }

  ngOnInit() {
  }

  selectUser(user){
    this.selected_user = user
  }

  selectRole(role){
    this.selected_role = role;
  }

  selectPermission(permission){
    this.selected_permission = permission;
  }

  addUser(){
    if(this.username == ""){
      return;
    }
    
    this.systemUserService.addUser(this.username).subscribe(
      data =>  {
        if(data==='Successful') {
          alert('User added!');
        } else {
          alert('There has been an error while adding user');
        }
      }
    );
    
    this.users.push(this.username);
    
  }

  addRole(){
    if(this.role_name == ""){
      return;
    }
    
    this.systemUserService.addRole(this.role_name).subscribe(
      data =>  {
        if(data==='Successful') {
          alert('Role added!');
        } else {
          alert('There has been an error while adding role');
        }
      }
    );
    
    this.roles.push(this.role_name);
    
  }

  addPermission(){
    if(this.permission_name == ""){
      return;
    }
    
    this.systemUserService.addPermission(this.permission_name).subscribe(
      data =>  {
        if(data==='Successful') {
          alert('Permission added!');
        } else {
          console.log(data);
          alert('There has been an error while adding permission');
        }
      }
    );
    
    this.permissions.push(this.permission_name);
    
  }

  removeUser(){
    if(this.selected_user == undefined){
      return;
    }
    
    this.systemUserService.removeUser(this.selected_user).subscribe(
      data =>  {
        if(data==='Successful') {
          alert('User removed!');
        } else {
          alert('There has been an error while removing user');
        }
      }
    );
    
    this.users.splice(this.users.indexOf(this.selected_user),1);
    this.selected_user = undefined;
  }

  removeRole(){
    if(this.selected_role == undefined){
      return;
    }
    
    this.systemUserService.removeRole(this.selected_role).subscribe(
      data =>  {
        if(data==='Successful') {
          alert('Role removed!');
        } else {
          alert('There has been an error while removing role');
        }
      }
    );
    
    this.roles.splice(this.roles.indexOf(this.selected_role),1);
    this.selected_role = undefined;
  }

  removePermission(){
    if(this.selected_permission == undefined){
      return;
    }
    
    this.systemUserService.removePermission(this.selected_permission).subscribe(
      data =>  {
        if(data==='Successful') {
          alert('Permission removed!');
        } else {
          alert('There has been an error while removing permission');
        }
      }
    );
    
    this.permissions.splice(this.permissions.indexOf(this.selected_permission),1);    
    this.selected_permission = undefined;
  }
}
