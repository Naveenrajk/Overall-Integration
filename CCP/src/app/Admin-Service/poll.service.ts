import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Department } from '../model/Department';
import { OptionResponseNew } from '../model/OptionResponseNew';
import { Project } from '../model/Project';
import { Region } from '../model/Region';
import { User } from '../model/User';


@Injectable({
  providedIn: 'root',
})
export class PollService {
  private url: string = 'http://localhost:4992';

  constructor(private http: HttpClient) {}

  get(id: any): Observable<any> {
    console.log(id)
    return this.http.get(this.url+"/poll/"+id);
  }

  getAllPolls():  Observable<any>{
   
    return this.http.get<User[]>(this.url + "/published")
  }
 
  getDraft():  Observable<any>{
    
    return this.http.get<User[]>(this.url+"/drafts")
  }
  
  getOption(): Observable<any> {
    return this.http.get(this.url+"/options");
  }
  getOptionResponse(): Observable<any> {
    return this.http.get(this.url+"/optionResponse");
  }

  getAllPoll(): Observable<any> {
    return this.http.get(this.url+"/poll");
  }

  getOptionResponseInsert(optresponse: OptionResponseNew) {
    console.log(optresponse)
    this.http.post(this.url+ '/optionResponse', optresponse).subscribe();
    return 'Record Response Inserted';
  }

  addPoll(poll: any) {
    console.log(poll);
    this.http.post<any>(this.url + '/poll', poll).subscribe();
  }
 
  getOptionCount(id:any): Observable<any>
  {
    console.log(id);
    return this.http.get(this.url +"/getOptionCount/"+id);
  }

  getAllRegions() {
   
    return this.http.get<Region[]>(this.url + "/region");
 
  }
  getAllDepartments(region: Region) {
    
    return this.http.get<Department[]>(this.url + "/department/" + region.regionId);
  }
  getAllProjects(department: Department) {
    
    return this.http.get<Project[]>(this.url + "/getProject/" + department.departmentId);
  }

  viewAllUserDetails(){

    return this.http.get<User[]>(this.url+"/findAllEmployees");
  }


  getData(user : User)  
  {  
    this.url='http://localhost:4992' + "filterData";  
    return  this.get(this.url +"/userFirstName/" + user);  
  } 
  
  getUsersByFirstName(firstName: string): Observable<User[]> {
    return this.http.get<User[]>(`${this.url}/userFirstName?user=${firstName}`);
  }
}
