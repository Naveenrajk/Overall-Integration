import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Department } from "src/app/model/Department";
import { Mapping } from "src/app/model/Mapping";
import { OptionResponseNew } from "src/app/model/OptionResponseNew";
import { PollResponse } from "src/app/model/PollResponse";
import { PollValue } from "src/app/model/PollValue";
import { Project } from "src/app/model/Project";
import { Region } from "src/app/model/Region";
import { User } from "src/app/model/User";
import { Poll } from "src/app/model/poll";

@Injectable({
  providedIn: "root",
})
export class PollService {
  private url: string = "http://localhost:4992";

  constructor(private http: HttpClient) {}

  insertPoll(question: string, enddate : Date, status : boolean,
    visibility : string, id : number, values : string, userId : number,
    files: File[], optionValues: string[]):Observable<any> {
    const formData: FormData = new FormData();
 
    console.log(JSON.stringify(id));
    formData.append('pollQuestion',question);
    formData.append('endDate',enddate.toString());
    formData.append('status',JSON.stringify(status));
    formData.append('visibility',visibility);
    formData.append('id',id.toString());

    formData.append('values',values);
    formData.append('userId',JSON.stringify(userId));
 
 
    files.forEach(file => {
     
      formData.append('file', file);
    });
    optionValues.forEach(value => {
     
      formData.append('value', value);
    });
 
    return this.http.post<any>(this.url+'/poll', formData);
  }


  get(id: any): Observable<any> {
    return this.http.get(this.url + "/poll/" + id);
  }

  getAllPolls(): Observable<any> {
    console.log("working")
    return this.http.get<User[]>(this.url + "/published");
  }

  deletePoll(id: any): Observable<any> {
    return this.http.delete(this.url + "/poll/" + id);
  }

  getDraftById(pollId: number): Observable<any> {
    return this.http.get(this.url + "/draft/" + pollId);
  }

  getDraft(): Observable<any> {
    return this.http.get<User[]>(this.url + "/drafts");
  }

  getOption(): Observable<any> {
    return this.http.get(this.url + "/options");
  }

  getOptionValue(poll:PollValue):Observable<any>{
    console.log(poll);
    return this.http.get(this.url + "/optionValue/"+poll);

  }
  getOptionResponse(): Observable<any> {
    return this.http.get(this.url + "/optionResponse");
  }

  getOptionById(id:any):Observable<any>{
    return this.http.get(this.url + "/optionValue/"+id);
  }

  getAllPoll(): Observable<any> {
    return this.http.get(this.url + "/poll");
  }

  getOptionResponseInsert(optresponse: OptionResponseNew) {
    this.http.post(this.url + "/optionResponse", optresponse).subscribe();
    return "Record Response Inserted";
  }

  addPoll(poll: any): Observable<any> {
    return this.http.post(this.url + "/poll", poll);
  }

  updatePoll(poll:PollValue):Observable<any>{
    console.log(poll)
    return this.http.put(this.url+"/updatePoll",poll)
  }

  getOptionCount(id: any): Observable<any> {
    return this.http.get(this.url + "/getOptionCount/" + id);
  }

  getAllRegions() {
    return this.http.get<Region[]>(this.url + "/findAllRegions");
  }
  getAllDepartments(region: Region) {
    return this.http.get<Department[]>(
      this.url + "/getDepartment/" + region.regionId
    );
  }
  getAllProjects(department: Department) {
    return this.http.get<Project[]>(
      this.url + "/getProject/" + department.departmentId
    );
  }

  viewAllUserDetails() {
    return this.http.get<User[]>(this.url + "/findAllEmployees");
  }

  getData(user: User) {
    this.url = "http://localhost:4992" + "filterData";
    return this.get(this.url + "/userFirstName/" + user);
  }

  getUsersByFirstName(firstName: string): Observable<User[]> {
    return this.http.get<User[]>(`${this.url}/userFirstName?user=${firstName}`);
  }

  // vote(optionIndex: any) {
  //   console.log(optionIndex)
  //   return this.http.post<any>(this.url+'/vote/'+optionIndex, {});
  // }

  getVoteCounts(id: any) {
    return this.http.get<any>(this.url + "/counts/" + id);
  }

  getAllVoteCount() :  Observable<any> {
    return this.http.get<any>(this.url+'/counts/all');
  }

  getAllReactionCount(id: any) {
    return this.http.get<any>(this.url + "/getReactionCount/" + id);
  }


  // vote(votes: any) {
  //   console.log(votes)
  //   return this.http.post<any>(this.url+'/votes', {});
  // }

  // getVoteCounts(optionId:any) {
  //   return this.http.get<any>(this.url+'/counts/'+optionId);
  // }

  vote(pollId: number, optionId: number, userId: any): Observable<any> {
    console.log(optionId)
    return this.http.post<any>(
      `${this.url}/${pollId}/${optionId}/${userId}`,
      {}
    );
  }

  updateVote( optionId: any, userId: any,pollId: any): Observable<any> {
    return this.http.put<any>(`${this.url}/${optionId}/${userId}/${pollId}`, {});
  }

  deleteDraft(pollId: number): Observable<any> {
    // alert("Draft Deleted successfully");
    return this.http.delete(this.url + "/poll/" + pollId);
  }

  updateDraft(data: any) {
    return this.http.put(this.url + "/updateDraft", data);
  }
  getDraftOptionsById(pollId: number): Observable<any> {
    return this.http.get(this.url + "/draftOption/" + pollId);
  }

  changeStatus(): Observable<any> {
    return this.http.get(this.url + "/changeStatus");
  }

  getResponse(pollResponse: PollResponse): Observable<any> {
    console.log(pollResponse)
    return this.http.post(this.url + "/pollResponse", pollResponse);
  }

  getAllResponse(userId : any): Observable<any> {
    console.log(userId)
    return this.http.get(this.url + "/pollAllResponse/" +userId);
  }

  getPollByPollName(pollName: string): Observable<Poll[]> {

    return this.http.get<Poll[]>(
      `${this.url}/pollQuestion?pollQuestion=${pollName}`
    );
  }
  getRegDepPro(poll: PollValue){
    console.log(poll);
    return this.http.get<Mapping[]>(this.url+"/getpolldepregpro/"+poll.user)
  }


getVoteCount(pollId:any):Observable<any>{
    return this.http.get(this.url + "/voteCount/" + pollId);
  }

 getAllOptionResponse():Observable<any>{
    return this.http.get<any>(this.url + "/optionAllResponse");
  }
}
