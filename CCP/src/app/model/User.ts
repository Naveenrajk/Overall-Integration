
import { Department } from "./Department";
import { Project } from "./Project";
import { Region } from "./Region";
export class User {
    userId!: number;
    userFirstName!: string;
    userLastName!: string;
    userEmailId!: string;
    userPassword!: string;
    userType!: string;
    userMobileNumber!: number;

    department!:Department[];
    region!:Region[];
    
    departmentName!:string;
  
    regionName!:string;
   
    project!: Project[];
}