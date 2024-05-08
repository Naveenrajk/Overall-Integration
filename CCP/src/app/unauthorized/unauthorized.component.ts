import { Component } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-unauthorized',
  templateUrl: './unauthorized.component.html',
  styleUrls: ['./unauthorized.component.css']
})
export class UnauthorizedComponent {

  constructor(private router:Router ){
    this.unauthorizedFunction ("");
  }
  unauthorizedFunction(e: any) {
    Swal.fire({
      title: '401 Unauthorized',
      icon: 'error',
      showCancelButton: false,
      padding: "3em",
      color: "#716add",
      background: "#fff url(https://sweetalert2.github.io/images/trees.png)",
      cancelButtonColor: '#97247E',
      cancelButtonText: 'No, cancel',
      customClass: {
        popup: 'my-popup-class',
      },
    }).then((result) => {
      if (result.isConfirmed) {
        if(sessionStorage.getItem('userType')==="admin"){
          this.router.navigate(['Admin']);
        }else{
          this.router.navigate(['User']);
        }
        
      } else {
        location.reload();
      }
    });
  }
}