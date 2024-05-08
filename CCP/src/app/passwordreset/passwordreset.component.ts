import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Reset } from '../model/Rest';
import { ResetService } from '../Admin-Service/reset.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-passwordreset',
  templateUrl: './passwordreset.component.html',
  styleUrls: ['./passwordreset.component.css']
})
export class PasswordresetComponent {

  passwordresetForm: FormGroup;
  reset: Reset;
  result: string = "";
  email!: string;
  message: string = '';

  constructor(private service: ResetService,private router:Router) {
    this.passwordresetForm = new FormGroup({
      oldPass: new FormControl('', [Validators.required]),
      newPass: new FormControl('', [Validators.required]),
      confirmPass: new FormControl('', [Validators.required]),
    });
    this.reset = new Reset();
  }

  ngOnInit(): void {
    this.email = sessionStorage.getItem("email") || '';
    this.reset.email = this.email;
  }

  dataSave(data: any): void {
    const resetData: Reset = {
      otp: "",
      oldPassword: data.oldPass,
      newPassword: data.newPass,
      confirmPassword: data.confirmPass,
      email: this.email
     
    };

    

    // this.service.updatePass(resetData).subscribe(
    //   (respoonse:any) => {
    //     this.message = "Password Updated";
    //     this.result=respoonse.message;
    //     if (this.result=="Password updated successfully."){
    //       this.message="";
    //       alert("Password updated successfully, login again.")
    //       window.location.href=' ';
    //     }

    //   },
    //   (error) => {
    //     this.message = error.error.message;
    //   }
    // );

    this.service.updatePass(resetData).subscribe(
      (respoonse:any) => {
        
        this.message = "Password Updated";
        this.result=respoonse.message;
        if (this.result=="Password updated successfully."){
          this.message="";
          Swal.fire({
          title: 'Password updated successfully, login again.',
          icon: 'success',
          showCancelButton: false,
          confirmButtonColor: '#1B193F',
          confirmButtonText: 'Ok!',
          customClass: {
        popup: 'my-popup-class',
      },
        }).then((result) =>{
          if(result.isConfirmed){
            sessionStorage.clear();
            this.router.navigate([''])
          }
        })
        }

      },
      (error) => {
        this.message = error.error.message;
      }
    );

    
  }
}