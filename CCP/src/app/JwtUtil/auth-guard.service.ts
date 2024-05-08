import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const token = sessionStorage.getItem('token');
    const userType = sessionStorage.getItem('userType') || ""; // Get user type from session storage

    if (token) {
      if (this.checkAccess(route, userType)) {
        return true; // Allow access if token exists and user has the right access
      } else {
        this.router.navigate(['/unauthorized']); // Redirect unauthorized users
        return false;
      }
    } else {
      this.router.navigate(['/unauthorized']); // Redirect unauthenticated users
      return false;
    }
  }

  // Function to check if the user has access based on their user type
  private checkAccess(route: ActivatedRouteSnapshot, userType: string): boolean {
    const allowedRoles = route.data['allowedRoles']; // Get allowed roles from route data
    if (allowedRoles && allowedRoles.includes(userType)) {
      return true; // User has the required role
    } else {
      return false; // User does not have the required role
    }
  }
}