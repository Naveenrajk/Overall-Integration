import { Component } from '@angular/core';
import { SummaryService } from '../survey-service/summary.service';
import { Question } from '../Survey-Model/question';
import { Survey } from '../Survey-Model/survey';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Responses } from '../Survey-Model/responses';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Option } from '../Survey-Model/option';
import { Options } from '../Survey-Model/Options';
import { Questions } from '../Survey-Model/Questions';
import { Page } from '../Survey-Model/page';
 
@Component({
  selector: 'app-summary-page',
  templateUrl: './summary-page.component.html',
  styleUrls: ['./summary-page.component.css']
})
export class SummaryPageComponent {
 
  flagpage: boolean = false;
  flagquest: boolean = true;
  flagresp: boolean = false;
 
  myForm: FormGroup;
 
  survey: Survey;
  surveyDetail:Survey;
  response: Responses;
  question: Question;
  page:Page;
  questions: Questions;
  options:Options;
  result: string = "";
  value!: number;
  ref!: number;
  pageList:Page[]=[];
  questList: Questions[] = [];
  optionList:Options[]=[];
  respList: Responses[] = [];
  surveyId:any=0;
 
 
  constructor(private service: SummaryService,private router:Router,private activatedroute: ActivatedRoute) {
    this.question = new Question;
    this.questions = new Questions;
    this.options=new Options;
    this.survey = new Survey;
    this.surveyDetail = new Survey;
    this.page=new Page;
    this.response = new Responses;
    this.myForm = new FormGroup({
      surveyId: new FormControl('', [Validators.required, Validators.pattern('[ 0-9]+')]),
      questions: new FormControl('', [Validators.required, Validators.pattern(' ')]),
    })
    // this.surveyId=sessionStorage.getItem("surveyId") || "";
    this.activatedroute.paramMap.subscribe((param: Params) => {
      this.surveyId = param['get']("id");
    })
    this.getQuestionCount();
  }
 
  // ngOnInit() {
  //   if (!localStorage.getItem('summary')) {
  //     localStorage.setItem('summary', 'no reload')
  //     location.reload()
  //   } else {
  //     localStorage.removeItem('summary')
  //   }
  // }
 
  getQuestionCount() {
 
    this.survey.surveyId = this.surveyId;
    this.service.getSurveyDetail(this.survey).subscribe(values => {
      this.surveyDetail = values;
      console.log(this.surveyDetail);
    });
    this.service.getQuestionCount(this.survey).subscribe(values => {
      this.value = values;
      console.log(this.value);
    });
    this.service.getAllpages(this.survey).subscribe(page => {
      this.pageList = page;
      console.log(this.pageList)
    });
    this.service.getAllquestions(this.survey).subscribe(question => {
      this.questList = question;
      console.log(this.questList)
    });
    this.service.getAlloptions(this.survey).subscribe(option => {
      this.optionList = option;
      console.log(this.optionList)
    });
 
    this.service.getPagesCount(this.survey).subscribe(values => {
      this.ref = values;
      console.log(this.ref);
    });
 
    // this.service.getResponseDetailCount(this.survey).subscribe(values => {
    //   this.ref= values;
    //   console.log(this.ref);
    // });
    // this.service.getAllResponseDetails(this.survey).subscribe(responses => {
    //   this.respList= responses;
    //   console.log(this.respList);
    // });
  }
 
  // getAllquestions(data: any) {
  //   this.survey.surveyId=data.surveyId
  //   this.service.getAllquestions(this.survey).subscribe(question => this.questList = question);
  // }
 
updatePage(data:any){
  console.log(data);
  this.page.pageId=data.pageId;
  this.page.pageTitle=data.pageTitle;
  console.log(this.page);
  this.service.updatePage(this.page).subscribe({
    next: (res: any) => {
      //
    }, error: () => {
      // alert("error");
    }
  });
}
 
 
updateQuestion(data:any){
  console.log(data);
  this.question.questionId=data.questionId;
  this.question.questions=data.questions;
  console.log(this.question);
  this.service.updateQuestion(this.question).subscribe({
    next: (res: any) => {
      //
    }, error: () => {
      // alert("error");
    }
  });
}
updateOption(data:any){
  console.log(data);
  this.options.optionId=data.optionId;
  this.options.options=data.options;
  console.log(this.options);
  this.service.updateOption(this.options).subscribe({
    next: (res: any) => {
      // alert("Successfully created");
    }, error: () => {
      // alert("error");
    }
  });
}
 
  // page() {
  //   this.flagpage = true;
  // }
 
  questionpage() {
    this.flagquest = true;
  }
 
  responsepage() {
    this.flagresp = true;
  }
 
 
 
}