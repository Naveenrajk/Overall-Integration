import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Reset } from '../model/Rest';
import { Observable } from 'rxjs';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class ForcePasswordResetService {

  private url : string ="http://localhost:4992";
  constructor(private http: HttpClient) { }

  insertPass(reset:Reset):Observable<any>{
    return this.http.post<any>(this.url+"/insertPassword", {
      email: reset.email,
      oldPassword: reset.oldPassword,
      newPassword: reset.newPassword,
      confirmPassword: reset.confirmPassword
    })
  }
  generatePassword(email:string){
    this.http.get(this.url+"/ForcePassGenerate/"+email).subscribe();
    Swal.fire({
      title: 'Password Generated Successfully',
      icon: 'success',
      showCancelButton: false,
      confirmButtonColor: '#1B193F',
      confirmButtonText: 'Ok!',
      customClass: {
    popup: 'my-popup-class',
  },
    }).then((result) =>{
      if(result.isConfirmed){
        location.reload();
      }
    })
  }
}
