import { Option } from "./Option";


export class Question
{
    pollQuestion!:string;
    options!:Option[];
    pollId!: number;
    endDate!: Date;
    status!: boolean;
    timeStamp!: Date;
    region!: string;
    optionId!: number;
    user!: string;
    userId!:number;
    regionId!:string;
    departmentId!:string;
    projectId!:string;
    project!: string;
    department!: string;
    voted?: boolean;
    commentStatus!: boolean;
    visibility!: string;
    id!:number;
    values!:string;
}