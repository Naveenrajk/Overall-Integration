
import { Project } from "./Project";
export class PollUser {
    userId!: any;
    userFirstName!: string;
    userLastName!: string;
    userEmailId!: string;
    userPassword!: string;
    userType!: string;
    userMobileNumber!: number;
    department!: bigint;
    departmentName!:string;
    region!: bigint;
    regionName!:string;
    project!: Project[];
}