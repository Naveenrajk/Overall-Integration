import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Invite } from '../model/Invite';
import { User } from '../model/User';

@Injectable({
  providedIn: 'root'
})
export class InvitationService {

  private url: string = "http://localhost:4992";
 
  constructor(private http: HttpClient) { }

  inviteUser(email: string,userId :string){
   return this.http.get(this.url+"/inviteUserByMail/"+ email+"/"+userId);
  }

  getAllinvites() {
    return this.http.get<Invite[]>(this.url + "/findAllInvitations");
  }

  getparticularUser(userEmailId: string) {
    return this.http.get<User>(this.url + '/findAllInvitations/' + userEmailId);
  }

    
  //   return this.http.get<User[]>(this.url+"/findAllEmployees/"+user.userEmailId);
  // }
}
