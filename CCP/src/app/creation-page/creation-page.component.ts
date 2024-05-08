import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
// import { SurveycreationService } from '../service/surveycreation.service';
import { Survey } from '../Survey-Model/survey';
import { Region } from '../Survey-Model/Region';
import { Department } from '../Survey-Model/Department';
import { Project } from '../Survey-Model/Project';
import { SurveycreationService } from '../survey-service/surveycreation.service';
import { Route, Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { Mapping } from '../Survey-Model/Mapping';

@Component({
  selector: 'app-creation-page',
  templateUrl: './creation-page.component.html',
  styleUrls: ['./creation-page.component.css']
})
export class CreationPageComponent {

  flagregion: boolean = false;
  flagdept: boolean = false;
  flagbtn: boolean = true;
  survey: Survey;
  msg!:string;
  region: Region;
  value: String;
  department: Department;
  project: Project;
  myform: FormGroup;
  result: string = "";
  RegionList: Region[] = [];
  mapList:Mapping[]=[];
  DeptList: Department[] = [];
  ProjectList: Project[] = [];
  Allreg!: number;
  Alldept!: number;
  Allproj!: number;
  route: boolean = true;

  flag: boolean = true;
  userId!:any;
  sample!:any;

  startDate: any;
  compareDate: any;
  addnewpage!:any;

  constructor(private service: SurveycreationService, private router: Router, private datePipe: DatePipe) {
    this.survey = new Survey;
    this.region = new Region;
    this.department = new Department;
    this.project = new Project;
    this.value = new String;
    this.Allreg = 1;
    this.Alldept = 1;
    this.Allproj = 1;

    this.myform = new FormGroup(
      {
        surveyName: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z + 0-9]+')]),
        endDate: new FormControl('', [Validators.required]),
        // region: new FormControl('', [Validators.required, Validators.pattern('')]),
        // regionId: new FormControl('', [Validators.required, Validators.pattern('')]),
        // department: new FormControl('', [Validators.required]),
        // departmentId: new FormControl('', [Validators.required]),
        // project: new FormControl('', [Validators.required]),
        // projectId: new FormControl('', [Validators.required]),
        map: new FormControl('', [Validators.required, Validators.pattern('')]),
      }
    );

    this.getRegion();
    this.userId=sessionStorage.getItem("userId")||'';
    console.log("User:"+this.userId);
    this.getRegDepPro();
  }
  
  createSurvey(data: any) {
    console.log(data);
    var str=data.map
    // var splitter:string[];
    var splitter=str.split("+");
    // console.log(splitter[0]);
    // console.log(splitter[1]);
    // for(let i=0;i<splitter.length;i++){
    //   console.log(splitter[0]);
    // }

    this.survey.surveyName = data.surveyName;
    this.survey.endDate = data.endDate;
    this.survey.id=splitter[0];
    this.survey.value=splitter[1];
    // this.survey.region = data.regionId;
    // this.survey.department = data.departmentId;
    // this.survey.project = data.projectId;
    this.survey.user=this.userId;
    // this.survey.region = (this.region.regionId = data.regionId);
    // this.survey.department = (this.department.departmentId = data.departmentId);
    // this.survey.project = (this.project.projectId = data.projectId);
    // this.survey.department.departmentId = data.department.departmentId;
    // this.survey.project.projectId = data.project.projectId;
    // alert("Region" + this.survey.region);
    this.service.create(this.survey).subscribe(
      (response: any) => {
        sessionStorage.setItem("surveyId",response.message);
        this.router.navigate(['/survey/builder']);
        // alert("Successfully created");
      }, (error:any)  => {
        alert("Survey Details Required");
      }
    );
    console.log(this.survey);
    // location.reload();
    // this.addnewpage=true;
    // sessionStorage.setItem("flag",this.addnewpage);
    
    // alert("Region "+this.survey.region);
    //   this.result=this.service.create(this.survey);
  }

  // insertRegion(data: any) {
  //   this.region.regionName = data.regionName;
  // }

  // insertDepartment(data: any) {
  //   this.department.departmentName = data.departmentName;
  // }

  // insertProject(data: any) {
  //   this.project.projectName = data.projectName;
  // }

  getRegion() {
    this.service.getAllRegions().subscribe(regions => this.RegionList = regions);
  }

  getDepartment(data: any) {
    console.log(data);
    this.region.regionId = data.regionId;
    // this.service.getAllDepartments(this.region).subscribe(departments => this.DeptList = departments);
    // alert(this.region.regionId)
    this.flagregion = true;
    // if (this.region.regionId != this.Allreg) {

    // }
  }

  getProject(data: any) {
    this.department.departmentId = data.departmentId;
    this.service.getAllProjects(this.department).subscribe(projects => this.ProjectList = projects);
    // alert(this.department.departmentId);
    this.flagdept = true;
    // if (this.department.departmentId != this.Alldept) {

    // }
  }

  getRegDepPro(){
    this.survey.user=this.userId;
    this.service.getRegDepPro(this.survey).subscribe(all=>{
      this.mapList=all;
      console.log(this.mapList);
    })
  }

  changeregion() {
    this.flagregion = true;
  }

  changedept() {
    this.flagdept = true;
  }

  validDate(data: any) {
    console.log(data.endDate)
    this.compareDate = data.endDate;
    this.startDate = this.datePipe.transform(new Date(), 'yyyy-MM-dd ');
    console.log(this.startDate);
    if (this.compareDate < this.startDate) {
      // alert("It is invalid")
      this.msg="Survey End date is invalid";
    } else {
      // alert("The date is Valid")
      // this.msg="The date is Valid";
    }
  }
}