import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { InviteService } from '../Admin-Service/invite.service';
import { User } from '../model/User';

@Component({
  selector: 'app-userregister',
  templateUrl: './userregister.component.html',
  styleUrls: ['./userregister.component.css']
})
export class UserregisterComponent {
  
    inviteForm: FormGroup;  
    message: string = '';
    inviteclass: User;
    result:string="";
    message1: string="";
    message2: string="";
    
    constructor(private serviceObj: InviteService, private router: Router) {
      this.inviteclass=new User();
      
      this.inviteForm = new FormGroup({
        userEmailId: new FormControl('', [Validators.required, Validators.pattern("[a-z0-9._%+-]+@gmail.com")]),
        userFirstName: new FormControl('',[Validators.required,Validators.pattern("[a-zA-Z][a-zA-Z ]+")]),
        userLastName: new FormControl('',[Validators.required,Validators.pattern("[a-zA-Z]+")]),
        userMobileNumber: new FormControl('',[Validators.required,Validators.pattern("^((\\+91-?)|0)?[0-9]{10}$")])
  
      });
     }
    // insert(data:any)
    // {
    //   this.inviteclass.userFirstName=data.userFirstName;
    //   this.inviteclass.userLastName=data.userLastName;
    //   this.inviteclass.userEmailId=data.userEmailId;
    //   this.inviteclass.userMobileNumber=data.userMobileNumber;    
    //   this.result=this.serviceObj.insert(this.inviteclass);
    //   this.inviteForm.reset();    
    //   console.log(this.result);
    //   alert(this.result);
    // }
    insert(data:any){
      this.inviteclass.userFirstName=data.userFirstName;
      this.inviteclass.userLastName=data.userLastName;
      this.inviteclass.userEmailId=data.userEmailId;
      this.inviteclass.userMobileNumber=data.userMobileNumber;    
      this.serviceObj.insert(this.inviteclass).subscribe(
      (resultdata:any)=>{
          this.result = resultdata.message;
          if(this.result==="success"){
            this.inviteForm.reset();
            this.message = "User Register Successfully";
            setTimeout(function(){ window. location. reload(); }, 1000);          
          }else if(this.result==="invalid"){
            // this.inviteForm.reset();
            this.message2 = "Email not found";
            setTimeout(function(){ window. location. reload(); }, 2000);          
          }
          else{
            // this.inviteForm.reset();   
            this.message1 = "User is already registered.";
            setTimeout(function(){ window. location. reload(); }, 2000); 
          }
        }
        
      )
    }
    
  }

