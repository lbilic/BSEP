import { Injectable } from '@angular/core';
import {HttpClient,HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class SistemUserService {

  private base_url = "http://localhost:8080/api/systemUser/";

  constructor(private http: HttpClient) { }

  addUser(username: string) {
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

    var systemUser = {}
    systemUser["username"] = username;
    
    var systemUser_json = JSON.stringify(systemUser);

    return this.http.put(this.base_url + "user", systemUser_json, {headers,responseType: 'text'});
  }

  addRole(name: string) {
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

    var role = {}
    role["name"] = name;
    
    var role_json = JSON.stringify(role);

    return this.http.put(this.base_url + "role", role_json, {headers,responseType: 'text'});
  }

  addPermission(name: string) {
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

    var permission = {}
    permission["name"] = name;
    
    var permission_json = JSON.stringify(permission);

    return this.http.put(this.base_url + "permission", permission_json, {headers,responseType: 'text'});
  }

  getUsers(){
    return this.http.get(this.base_url + "user", { responseType: 'text' as 'json'});
  }

  getRoles(){
    return this.http.get(this.base_url + "role", { responseType: 'text' as 'json'});
  }

  getPermissions(){
    return this.http.get(this.base_url + "permission", { responseType: 'text' as 'json'});
  }

  removeUser(username: string) {
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.delete(this.base_url + "user/" + username , {headers,responseType: 'text'});
  }

  removeRole(name: string) {
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.delete(this.base_url + "role/" + name, {headers,responseType: 'text'});
  }

  removePermission(name: string) {
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.delete(this.base_url + "permission/" + name, {headers,responseType: 'text'});
  }

  getUserRoles(username){
    return this.http.get(this.base_url + "user/" + username, { responseType: 'text' as 'json'});
  }

  getRolePermissions(name){
    return this.http.get(this.base_url + "role/" + name, { responseType: 'text' as 'json'});
  }

  updateUserRoles(username,roles){
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    var send_data = {};
    send_data["username"] = username;
    send_data["roles"] = [];

    for (let role of roles){
      send_data["roles"].push({"name" : role });
    }

    return this.http.post(this.base_url + "user", send_data, {headers,responseType: 'text'});
  }

  updateRolePermissions(name,permissions){
    var headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    var send_data = {};
    send_data["name"] = name;
    send_data["permissions"] = [];

    for (let permission of permissions){
      send_data["permissions"].push({"name" : permission });
    }

    var send_data_json = JSON.stringify(send_data);

    return this.http.post(this.base_url + "role", send_data_json, {headers,responseType: 'text'});
  }
}
