import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { LoginService } from '../Admin-Service/login.service';

import { Login } from '../model/Login';

import { jwtDecode } from 'jwt-decode';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;
  loginModel: Login = new Login();
  message: string = '';
  decodedtoken:any;

  constructor(private loginService: LoginService, private router: Router) {
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.pattern("^[0-9.a-z._%+-]+@gmail\.com$")]),
      password: new FormControl('',Validators.required)
    });
  }

  login(): void {
    if (this.loginForm.valid) {
      this.loginModel.userEmailId = this.loginForm.get('email')!.value;
      this.loginModel.password = this.loginForm.get('password')!.value;
      this.loginService.login(this.loginModel).subscribe(
        (response: any) => {
          if(response.message =="newUser"){
            sessionStorage.setItem('email', response.email);
            sessionStorage.setItem('token', response.token);
            this.router.navigate(['/forcepasswordreset']);
          }else{
            this.decodedtoken = jwtDecode(response.token);
            console.log(this.decodedtoken);
            console.log(this.decodedtoken.type);
            console.log(this.decodedtoken.email);
            console.log(this.decodedtoken);
            console.log(this.decodedtoken);
            sessionStorage.setItem('token', response.token);
            sessionStorage.setItem('userId',this.decodedtoken .sub);
            sessionStorage.setItem('userType', this.decodedtoken.type);
            sessionStorage.setItem('email', this.decodedtoken .email);
            sessionStorage.setItem('mobileNo',this.decodedtoken .mobileNo);
            sessionStorage.setItem('regionId',this.decodedtoken .regId);
            sessionStorage.setItem('regionName',this.decodedtoken .regName);
            sessionStorage.setItem('departmentId',this.decodedtoken .depId);
            sessionStorage.setItem('departmentName',this.decodedtoken .depName);
            sessionStorage.setItem('projectId',this.decodedtoken .proId);
            sessionStorage.setItem('projectName',this.decodedtoken .proName);
            sessionStorage.setItem('forcePass',this.decodedtoken.ForcePass);
          this.router.navigate(['/TwoFactor']);
        }
          }
          ,
        (error: any) => {
          this.message = error.error.message;
        }
      );
    }
  }  
  
}
