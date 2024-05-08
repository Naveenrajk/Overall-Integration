
import { Department } from "./Department";
import { Project } from "./Project";
import { Region } from "./Region";
import { User } from "./User";
// import { User } from "./User";

export class Content {
  
  postId!: number;
  dateTime!: string;
  postContent!: string;
  region!: Region;
  department!: bigint;
  departmentId!:bigint;
  departmentName!:string;
  // department!: Department;
  project!: bigint;
  // project!: Project;
  userFirstName!:string;
  regionName!:string;
  nativeElement: any;
  status!: boolean;
  user!:User;
  commentStatus?:boolean;

  // comments!: CommentInterface ;

}