import { Option } from "./Option";
import { Department } from "./Department";
import { Project } from "./Project";
import { Region } from "./Region";
import { User } from "./User";
import { PollUser } from "./PollUser";

export class Poll {
    pollId!: number;
    endDate!: Date;
    status!: boolean;
    pollQuestion!: string;
    timeStamp!: Date;
    region!: Region;
    options!: Option;
    optionId!: number;
    user!: User;
    userId!:any;
    depart!:any;
    project!: Project;
    department!: Department;
    departmentName!:string;
    visibility!: string;
    voted?: boolean;
    pollUser!:PollUser;
    commentStatus!: boolean;
    userFirstName!: string;
    regionName!: string;
    constructor() {
        this.voted = false;
      }
}