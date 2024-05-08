import { Component } from '@angular/core';
import { User } from '../model/User';
import { UserService } from '../Admin-Service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {

  user:User;
  idUser:number;
 
 
  constructor(private userService:UserService,private router:Router){
    this.user = new User;
    this.idUser =1;
  }
 
  getPaticularUser(data:any){
    // this.userService.findParticularEmployee(this.idUser).subscribe(users => this.user = users)
  }
}
