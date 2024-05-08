
import { Region } from "./Region";
import { User } from "./User";
// import { User } from "./User";


export class ContentValue {
  id!:number;
  value!:string;
  postId!: number;
  dateTime!: string;
  postContent!: string;
  region!: string;
  department!: string;
  project!: string;
  nativeElement: any;
  status!: boolean;
  user!:string;
  commentStatus?:boolean;
  // comments!: CommentInterface ;

}
