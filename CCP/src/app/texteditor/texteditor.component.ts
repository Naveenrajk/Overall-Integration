import { Component, Sanitizer } from '@angular/core';
import { ViewChild } from '@angular/core';

import { FormGroup } from '@angular/forms';
import { FormControl } from '@angular/forms';
import { Validators } from '@angular/forms';

import { Router } from '@angular/router';
import { Region } from '../Post-Model/Region';
import { Department } from '../Post-Model/Department';
import { Project } from '../Post-Model/Project';
import { User } from '../Post-Model/User';
// import { Content } from '../model/content';
import { ContentService } from '../Post_Services/content.service';
import { Content } from '../Post-Model/content';
import { ContentValue } from '../Post-Model/contentValue';
// import { ToastrService,ToastrConfig } from 'ngx-toastr';
import Swal from 'sweetalert2';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { Mapping } from '../Post-Model/mapping';




@Component({
  selector: 'app-texteditor',
  templateUrl: './texteditor.component.html',
  styleUrls: ['./texteditor.component.css']
})
export class TexteditorComponent {

    


  title = 'Prime';
  ckeConfig: any;
  mycontent: string | undefined;
  log: string | undefined
  @ViewChild('PageContent') PageContent: any;
  res: any;
  content: Content;
  contentvalue:ContentValue;
  contentForm: FormGroup;
  department: Department;
  project: Project;
  region: Region;
  // user:User;
  result: string = "";
  contentList:Content[] = [];
  RegionList: Region[] = [];
  DeptList: Department[] = [];
  mapList:Mapping[]=[];
  ProjectList: Project[] = [];
  UserList: User[] = [];
  post: Content[]=[];
  Allreg!: number;
  Alldept!: number;
  Allproj!: number;
  date: Date;
  deptValue:string='';
  projectId: string = '';
  commentStatus!:boolean;
  draftCount:number=0;
  posts: any;


  constructor(private contentservice: ContentService, private router: Router) {


    this.date = new Date();
    this.region = new Region;

    this.department = new Department;

    this.project = new Project;

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
      commentStatus: new FormControl(false), // Initialize with false
      map: new FormControl("",[]),

      // userId:new FormControl('', [Validators.required])

    }); this.getRegion();
    // this.getUser();
    this.getRegDepPro();

  }

  ngOnInit() {

    this.projectId = sessionStorage.getItem('projectId') || "";
    // this.deptValue = parseInt(this.projectId);
  console.log(this.projectId.toString());
  this.deptValue = this.projectId.substring(2,1);
  console.log(this.deptValue);
    this.Getpost();
    // this.contentservice.getAllRegions().subscribe(regions => this.RegionList = regions); 
    this.ckeConfig = {
      allowedContent: false,
      extraPlugins: 'divarea',
      forcePasteAsPlainText: true,
      
    };
    this.contentservice.getAllDrafts() // Include drafts
      .subscribe(posts => {
        this.draftCount = posts.filter(post => post).length;
      });
  
  }


  AddPost(data: any) {
 
    var str=data.map
   
    var splitter=str.split("+");
   
    this.contentvalue.id=splitter[0];
    this.contentvalue.value=splitter[1];
    this.contentvalue.postContent = data.postContent;
    // this.contentvalue.region = sessionStorage.getItem("regionId")||"";
    // this.contentvalue.department = sessionStorage.getItem("departmentId")||"";
    // this.contentvalue.project = this.deptValue;
    this.contentvalue.user=sessionStorage.getItem("userId")||"";
    console.log(this.contentvalue);
    this.contentvalue.status = true;
    this.contentvalue.commentStatus = this.commentStatus;
    this.contentservice.AddUpdateContent(this.contentvalue).subscribe((data))
   
    setTimeout(()=>{
  Swal.fire({
    title: 'Successfully Posted!!',
    text: 'Your post has been successfully added.',
    icon: 'success',
    showCancelButton: false,
    confirmButtonColor: '#3085d6',
   
  }).then(() => {      
    window.location.reload();
   
  });
   
  },100)
  }


   
 insertDraft(data: any) {
 
  var str=data.map
 
  var splitter=str.split("+");
 
  this.contentvalue.id=splitter[0];
  this.contentvalue.value=splitter[1];
  this.contentvalue.postContent = data.postContent;
  this.contentvalue.user=sessionStorage.getItem("userId")||"";
  console.log(this.contentvalue);
  this.contentvalue.status = false;
  this.contentvalue.commentStatus = this.commentStatus;
  this.contentservice.AddDraft(this.contentvalue).subscribe((data))
 
  setTimeout(()=>{
Swal.fire({
  title: 'Draft Submitted!',
  text: 'Your Draft has been successfully added.',
  icon: 'success',
  showCancelButton: false,
  confirmButtonColor: '#3085d6',
 
}).then(() => {      
  this.router.navigate(['/draft']);
 
});
 
},200)
}
 



  // insertDraft(data: any) {
  //   this.contentvalue.postContent = data.postContent;
  //   this.contentvalue.region = sessionStorage.getItem("regionId")||"";
  //   this.contentvalue.department = sessionStorage.getItem("departmentId")||"";
  //   this.contentvalue.project = this.deptValue;
  //   this.contentvalue.user=sessionStorage.getItem("userId")||"";
  //   this.contentvalue.status = false;
  //   this.contentvalue.commentStatus = this.commentStatus;

  // Swal.fire({
  //   title: 'Draft Submitted!',
  //   text: 'Your Draft has been successfully added.',
  //   icon: 'success',
  //   showCancelButton: false, 
  //   confirmButtonColor: '#3085d6', 
  // }).then((result) => {
  //   if (result.isConfirmed) {
  //     this.contentservice.AddDraft(this.contentvalue).subscribe((data: any) => {
        
  //       this.router.navigateByUrl('/draft/${data.id}');
  //     },

  //     );
      
  //   }
  //   else{
  //     ( error: any) => {
  //       console.error("Error Saving Draft: ", error);
  //       Swal.fire({
  //         title: 'Error!',
  //         text: 'An error occurred while saving your Draft. Please try again.',
  //         icon: 'error'
  //       });
  //     }
  //   }
  // }
  // );
//}

  

  Getpost() {
    this.contentservice.getAllPosts(

    ).subscribe((data: any) => {
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

    // alert(this.region.regionId) 

    // if (this.region.regionId != this.Allreg) { 



    // } 
  }
  getProject(data: any) {

    this.department.departmentId = data.departmentId;

    this.contentservice.getAllProjects(this.department).subscribe(projects => this.ProjectList = projects);
    // alert(this.department.departmentId); 
    // if (this.department.departmentId != this.Alldept) { 
    // } 
  }

  // getUser() { 
  //   this.contentservice.getAllUsers().subscribe(User => this.UserList = User); 
  // } 

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

  disableComments() {
    this.commentStatus = !this.commentStatus; // Toggle the comment status
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




