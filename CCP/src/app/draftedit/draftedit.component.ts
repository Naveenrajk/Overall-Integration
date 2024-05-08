import { Component, OnInit, ViewChild } from '@angular/core';

 
import { Router } from '@angular/router';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ContentService } from '../Post_Services/content.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Department } from '../Post-Model/Department';
import { Project } from '../Post-Model/Project';
import { Region } from '../Post-Model/Region';
import { User } from '../Post-Model/User';
import { Content } from '../Post-Model/content';
import { ContentValue } from '../Post-Model/contentValue';
import Swal from 'sweetalert2';
import { Mapping } from '../Post-Model/mapping';


 
@Component({
  selector: 'app-draftedit',
  templateUrl: './draftedit.component.html',
  styleUrls: ['./draftedit.component.css']
})
export class DrafteditComponent implements OnInit {
  title = 'Prime';
  ckeConfig: any;
  mycontent: string | undefined;
  log: string | undefined
  @ViewChild('PageContent') PageContent: any;
 
  res: any;
  content: Content;
  contentForm: FormGroup;
  department: Department;
  project: Project;
  region: Region;
  contentvalue:ContentValue;
  // user:User;
  result: string = "";
  contentList:Content[] = [];
  RegionList: Region[] = [];
  mapList:Mapping[]=[];
  DeptList: Department[] = [];
  ProjectList: Project[] = [];
  UserList: User[] = [];
  post:Content[]=[];
  Allreg!: number;
  Alldept!: number;
  Allproj!: number;
  date: Date;

  deptValue:string='';
  projectId: string = '';
  commentStatus!:boolean;
  draftCount:number=0;
  editId:any;
  posts:any;
  id!:any;
 
 constructor(private contentservice: ContentService, private router: Router,private dialog: MatDialog) {
 
    this.date = new Date();
    this.region = new Region;
 
    this.department = new Department;
 
    this.project = new Project;
 
    this.content = new Content;
    this.contentvalue = new ContentValue;
 
    // this.user=new User;
    this.Allreg = 1;
 
    this.Alldept = 1;
 
    this.Allproj = 1;
    this.content = new Content;
    this.contentForm = new FormGroup({
      //  title: new FormControl('', [Validators.required]),
      postContent: new FormControl('', [Validators.required]),
      dateTime: new FormControl('', [Validators.required]),
      // region: new FormControl('', [Validators.required, Validators.pattern('')]),
 
      regionId: new FormControl('', [Validators.required, Validators.pattern('')]),
 
      // department: new FormControl('', [Validators.required]),
 
      departmentId: new FormControl('', [Validators.required]),
 
      // project: new FormControl('', [Validators.required]),
 
      projectId: new FormControl('', [Validators.required]),
      map: new FormControl("",[]),
 
      // userId:new FormControl('', [Validators.required])
 
    });
    this.getRegion();
    this.editId=sessionStorage.getItem("editId") ||"";
    console.log(this.editId);
    this.getDraftContent();
    this.getRegDepPro();
 
  }
 
 
  ngOnInit() {
    this.projectId = sessionStorage.getItem('projectId') || "";
    // this.deptValue = parseInt(this.projectId);
  console.log(this.projectId.toString());
  this.deptValue = this.projectId.substring(2,1);
  console.log(this.deptValue);
    this.Getpost();

    // this.getDraftContent();
    this.ckeConfig = {
      allowedContent: false,
      extraPlugins: 'divarea',
      forcePasteAsPlainText: true,
     
    };
  }
 
 
 
 
  updateDraft(data:any){
     
  }

  
 
 
  // insertPost(data: any) {
  //   var str=data.map
 
  //   var splitter=str.split("+");
 
  //   this.contentvalue.id=splitter[0];
  //   this.contentvalue.value=splitter[1];
  //   this.contentvalue.postContent = data.postContent;
  //   // this.contentvalue.region = sessionStorage.getItem("regionId")||"";
  //   // this.contentvalue.department = sessionStorage.getItem("departmentId")||"";
  //   // this.contentvalue.project = this.deptValue;
  //   this.contentvalue.user=sessionStorage.getItem("userId")||"";
  //   this.contentvalue.status = true;
  //   this.contentvalue.commentStatus = this.commentStatus;
  //   alert("posted successfully")
  //   this.contentservice.AddUpdateContent(this.contentvalue).subscribe((data: any) => {
     
  //     this.router.navigate(['/pageContent']);
  //   },
  //     error => {
  //       console.error("Error Saving Draft: ", error);
  //     }
  //   );
  // }


  deleteContent(postId: number) {
    this.contentservice.deleteDraft(this.editId).subscribe(
      (response: any) => {
        console.log('Post deleted successfully:', response);
 
        // Filter out the deleted post from the posts array
        this.posts = this.posts.filter((draft: { postId: number; }) => draft.postId !== postId);
 
        // Update the UI or perform any necessary actions
      },
      (error: any) => {
        console.error('Error deleting post:', error);
 
        // Handle the error or show a notification to the user
      }
    );
  }

  insertPost(data: any) {
 
    var str=data.map
   
    var splitter=str.split("+");
   
    this.contentvalue.id=splitter[0];
    this.contentvalue.value=splitter[1];
    this.contentvalue.postContent = data.postContent;
   
    this.contentvalue.user=sessionStorage.getItem("userId")||"";
    console.log(this.contentvalue);
    this.contentvalue.status = true;
    this.contentvalue.commentStatus = this.commentStatus;
    this.contentservice.AddUpdateContent(this.contentvalue).subscribe((data))
   
   
    setTimeout(()=>{
  Swal.fire({
    title: 'Post Submitted!',
    text: 'Your post has been successfully added.',
    icon: 'success',
    showCancelButton: false,
    confirmButtonColor: '#3085d6',
   
  }).then(() => {  
       
    // this.router.navigate(['/pageContent']);
    this.deleteContent(data.postId);
    // this.contentservice.deleteDraft(data.postId);
   
  });
   
  },100)
  }
  
  Getpost() {
    this.contentservice.getAllPosts().subscribe((data: any) => {
    this.res = data;
      console.log(this.res);
    })
  }
  getRegion() {
    this.contentservice.getAllRegions().subscribe(regions => this.RegionList = regions);
  }
  getDepartment(data: any) {
 
    this.region.regionId = data.regionId;
 
    this.contentservice.getAllDepartments(this.region).subscribe(departments => this.DeptList = departments);
  }
  getProject(data: any) {
 
    this.department.departmentId = data.departmentId;
 
    this.contentservice.getAllProjects(this.department).subscribe(projects => this.ProjectList = projects);

  }
 

getDraftContent(){
  this.contentservice.updateDraft({
    postId: this.editId,
    dateTime: '',
    postContent: '',
    region: new Region,
    department: 0n,
    project: 0n,
    user: new User,
    nativeElement: undefined,
    status: false,
    userFirstName: '',
    regionName: '',
    departmentId: 0n,
    departmentName: ''
  })
 
    .subscribe(draft => {
      if (draft) {
        this.contentForm.controls['postContent'].setValue(draft.postContent);
        console.log(this.contentForm);
      }
    });
}
Show = true;
Hide = true;
 
 
 
toggleDisplayView()
{
  this.Show = !this.Show;
  this.Hide = !this.Hide;
}
 
toggle = true;
label = false;
 
toggleDisplayShare()
{
  this.toggle = !this.toggle;
  this.label = !this.label;
}

getRegDepPro(){
  this.result=sessionStorage.getItem("userId") || "";
  this.contentvalue.user=this.result;
 
  console.log(this.contentvalue.user);
  this.contentservice.getRegDepPro(this.contentvalue).subscribe(all=>{
    this.mapList=all;
    console.log(this.mapList);
  })
}
 
 
 
}

function swal(arg0: { title: string; icon: string; buttons: { confirm: { text: string; }; }; }) {
  throw new Error('Function not implemented.');
}
