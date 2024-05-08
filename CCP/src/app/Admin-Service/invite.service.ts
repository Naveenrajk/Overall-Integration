import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../model/User';


@Injectable({
  providedIn: 'root'
})
export class InviteService {
  //private url:string="http://192.168.8.90:4992";  
  private url:string="http://localhost:4992"
  
  constructor(private http:HttpClient) { }
  insert(userInvite : User):Observable<any>{
    return this.http.post(this.url+"/PerformInsert", userInvite);
  }
  }
  // {

  // this.http.post(this.url+"/PerformInsert",userInvite ).subscribe();
  //   return "Record Inserted"
  // }
