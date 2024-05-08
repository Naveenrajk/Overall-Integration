import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


import { Observable } from 'rxjs';

import { Region } from '../Post-Model/Region';
import { Department } from '../Post-Model/Department';
import { Project } from '../Post-Model/Project';

import { Content } from '../Post-Model/content';
import { User } from '../Post-Model/User';
import { ContentValue } from '../Post-Model/contentValue';
import { Mapping } from '../Post-Model/mapping';





@Injectable({
  providedIn: 'root'
})
export class ContentService {
  private isFeedListVisible=false;
  url: any;
  constructor(private http: HttpClient) {

   }



  AddUpdateContent(contentvalue: ContentValue) {
    this.url = 'http://localhost:1090';
    
    return this.http.post(this.url+"/postInsert", contentvalue);
  }
 AddDraft(contentvalue: ContentValue) {
    this.url = 'http://localhost:1090';
    // alert(content.dateTime+content.postContent);
    return this.http.post(this.url+"/draftInsert", contentvalue);
  }
  
  // Getcontent() {
  //   this.url = 'http://localhost:1090/viewAllPost';
  //   return this.http.get(this.url);
  // }

  // new code by post 
  getpost(user: User): Observable<any> {
    this.url = 'http://localhost:1090';
    return this.http.get(this.url + "/getpostregionuser/" + user.userId);
  }



  getRegDepPro(post: ContentValue){
    console.log(post);
    this.url = 'http://localhost:1090';
    return this.http.get<Mapping[]>(this.url+"/getpostdepregpro/"+post.user)
  }
  

  GetcontentById(Id: any) {

    this.url = 'http://localhost:1090/postInsert' + Id;
    return this.http.get(this.url);
  }

  // Getcontent() {
  //   this.url = 'http://localhost:1090/viewAllPost';
  //   return this.http.get(this.url);
  // }

  getAllRegions() {
    this.url = 'http://localhost:4992';
    return this.http.get<Region[]>(this.url + "/findAllRegions");

  }

  getAllDepartments(region: Region) {
    this.url = 'http://localhost:4992';
    return this.http.get<Department[]>(this.url + "/getDepartment/" + region.regionId);
  }
  
  getAllProjects(department: Department) {
    this.url = 'http://localhost:4992';
    return this.http.get<Project[]>(this.url + "/getProject/" + department.departmentId);

  }

  getAllUsers() {
    this.url = 'http://localhost:4992';
    return this.http.get<User[]>(this.url + "/user");

  }

  getAllPosts():Observable<any>{
    this.url = 'http://localhost:1090';
    return this.http.get<User[]>(this.url + "/published")
  }
 
  getAllDrafts(){
    this.url = 'http://localhost:1090';
    return this.http.get<User[]>(this.url+"/drafts")
  }


deleteDraft(postId: number) {
  this.url = 'http://localhost:1090';
  return this.http.delete<any>(this.url + '/post/' + postId);
}


updateDraft(content: Content){
  this.url = 'http://localhost:1090';
  return this.http.get<any>(this.url+ '/postview/' + content.postId)
}

deletePost(postId:any):Observable<any>{
  this.url = 'http://localhost:1090';
  // console.log(postId+"delete post");
  return this.http.delete(this.url+'/posts/'+postId);

}
toggleFeedListVisibility():void{
  this.isFeedListVisible=!this.isFeedListVisible;
}
isFeedListCurrentlyVisible():boolean{
  return this.isFeedListVisible;
}



}
