// import { Region } from '../../src/app/model/Region';
import { Region } from './Region';
import { User } from './User';
import { Project } from "./Project";

export class Department {
    departmentId!: number;
    departmentName!: string;
    region!: bigint;
    regionList!: [];
   
    regionName!:string;
    users!: User[];
    project!: Project[];

}