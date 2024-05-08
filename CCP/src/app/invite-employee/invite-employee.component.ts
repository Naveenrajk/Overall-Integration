import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../Admin-Service/login.service';
import { Login } from '../model/Login';
import { InvitationService } from '../Admin-Service/invitation.service';
import { DepartmentserviceService } from '../Admin-Service/departmentservice.service';
import { RegionService } from '../Admin-Service/region.service';
import { Department } from '../model/Department';
import { Region } from '../model/Region';
import { User } from '../model/User';
import { Invite } from '../model/Invite';
import { UserService } from '../Admin-Service/user.service';

@Component({
  selector: 'app-invite-employee',
  templateUrl: './invite-employee.component.html',
  styleUrls: ['./invite-employee.component.css']
})
export class InviteEmployeeComponent {
//   inviteForm: FormGroup;
//   loginModel: Login = new Login();
//   message: string = '';
//   message1: string = '';
//   message2: string = '';
//   message3: string = '';
//   email :string = '';
//   constructor(private loginService: LoginService, private router: Router,private invitation: InvitationService) {
//     this.inviteForm = new FormGroup({
//       email: new FormControl('', [Validators.required, Validators.pattern("^[0-9.a-z._%+-]+@gmail\.com$")]),
      
//     });
//   }

//   sendInvite(data:any) {
//     this.loginModel.userEmailId = data.email;
//     // alert( this.loginModel.userEmailId);
//     this.invitation.inviteUser(this.loginModel.userEmailId).subscribe(
//       (response: any) => {
//         this.message = response.message;
//         if(this.message ==="User already exist"){
//           this.message2 = '';
//           this.message3= '';
//           this.message1 = this.message;
//         }else if(this.message ==="User is Already Invited"){
//           this.message1 = '';
//           this.message3= '';
//           this.message2 = this.message;
//         }else{
//           this.message2 = '';
//           this.message1= '';
//           this.message3 = this.message;
//         }
//       }
//     )
//   }  
// }


result:string="";
userId:string="";
inviteList:Invite[]=[];

userList:User[]=[];
user : User;

  inviteForm: FormGroup;
  loginModel: Login = new Login();
  userModel: User = new User();
  message: string = '';
  message1: string = '';
  message2: string = '';
  message3: string = '';
  email :string = '';

  constructor(
   private router: Router,private invitation: InvitationService,private userService : UserService) 
  {
    this.user = new User;
    this.inviteForm = new FormGroup({
            email: new FormControl('', [Validators.required, Validators.pattern("^[0-9.a-z._%+-]+@gmail\.com$")]),
            
          });
 
  this.findAllInvitations();
 // this.getAllUser();
  
}
  sendInvite(data:any) {
    this.loginModel.userEmailId = data.email;
    this.userId = sessionStorage.getItem('userId')||"";
    // alert( this.loginModel.userEmailId);
    this.invitation.inviteUser(this.loginModel.userEmailId,this.userId).subscribe(
      (response: any) => {
        this.message = response.message;
        if(this.message ==="User already exist"){
          this.message2 = '';
          this.message3= '';
          this.message1 = this.message;
          setTimeout(function(){ window. location. reload(); }, 1000);
        }else if(this.message ==="User is Already Invited"){
          this.message1 = '';
          this.message3= '';
          this.message2 = this.message;
          setTimeout(function(){ window. location. reload(); }, 1000);
        }else{
          this.message2 = '';
          this.message1= '';
          this.message3 = this.message;
          setTimeout(function(){ window. location. reload(); }, 1000);
        }
      }
    )
  }

  reset(data:any) {
    this.inviteForm.reset();
    this.message1="";
    this.message2="";
    this.message3="";
  }


// insert(data: any) {
//   this.department.departmentName = data.departmentName;
//   this.department.region = data.regionId;
//   this.result=this.service.insertDepartment(this.department);
//   setTimeout(function(){ window. location. reload(); }, 1000);


// }

findAllInvitations() {
  this.invitation.getAllinvites().subscribe(invites => this.inviteList = invites)
  for(let i of this.inviteList){
    console.log(i);
  }
}


// getAllUser() {
//   this.userService.getparticularUser(this.userModel).subscribe(users => this.userList= users);
// }

// getAllUser() {
//   this.userService.getparticularUser(this.user).subscribe(users => this.userList = users)
//   // console.log(this.UserProjects);
// }

// delete(data:any) {
  
//   this.department.departmentId = data.departmentId;

//   this.result = this.service.delete(this.department);
//   this.getAlldepartment();
//   setTimeout(function(){ window. location. reload(); }, 1000);

// }
// updateDepartment(data: any) {

//   this.department.departmentId = data.departmentId;
//   this.department.departmentName = data.departmentName;
//   this.department.region=data.regionId;

//   // alert(this.department.departmentId+this.department.departmentName+this.department.region);
//   this.result=this.service.updateDepartment(this.department);
//   setTimeout(function(){ window. location. reload(); }, 1000);

 
// }
// dataCollect(data: any) {
//   this.departmentDetail.departmentId = data.departmentId;
//   this.departmentDetail.departmentName = data.departmentName;
//   this.departmentDetail.region=data.region;


// }

}
