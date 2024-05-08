import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Question } from '../Survey-Model/question';
import { Page } from '../Survey-Model/page';
import { Survey } from '../Survey-Model/survey';
import { Responses } from '../Survey-Model/responses';
import { SurveyresponseService } from '../survey-service/surveyresponse.service';
import { Option } from '../Survey-Model/option';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Questions } from '../Survey-Model/Questions';
import { Options } from '../Survey-Model/Options';
import { Ref } from '../Survey-Model/Ref';
import { RefOption } from '../Survey-Model/RefOption';
import { RefQuestion } from '../Survey-Model/RefQuestion';
import { ResponseDetail } from '../Survey-Model/ResponseDetail';
import { DatePipe } from '@angular/common';
import Swal from 'sweetalert2';
 
@Component({
  selector: 'app-survey-response',
  templateUrl: './survey-response.component.html',
  styleUrls: ['./survey-response.component.css']
})
export class SurveyResponseComponent {
 
  flag: boolean = true;
  myForm: FormGroup;
  question: Questions[] = [];
  options: Options[] = [];
  // questionList: Question[] = [];
  // pagesList:Page[]=[];
  SingleChoiceOptions: Option[] = [];
  MultiChoiceOptions: Option[] = [];
  StringList: String[] = [];
 
  ReferenceList: Ref[] = []
  reference: Ref;
  referenceOption: RefOption;
  referenceQuestion: RefQuestion;
  referenceOptionList: RefOption[] = [];
 
  responseDetailId: any = 0;
 
  num1: number = 0;
  num: number = 0;
  pages: Page[] = [];
  page1: Page[] = [];
  surveyId!: any;
  surveyValue!:number;
  // option!: Option;
  survey: Survey;
  page: Page;
  i!: number;
  responses: Responses;
  dropdownOptions = [];
  // selectedOptions = [];
  selectedOptions0: number[] = [];
  selectedOptions1: number[] = [];
  selectedOptions2: number[] = [];
  selectedOptions3: number[] = [];
  selectedOptions4: number[] = [];
  selectedOptions5: number[] = [];
  selectedOptions6: number[] = [];
  selectedOptions7: number[] = [];
  selectedOptions8: number[] = [];
  selectedOptions9: number[] = [];
  selectedOptions10: number[] = [];
  selectedOptions11: number[] = [];
  selectedOptions12: number[] = [];
  selectedOptions13: number[] = [];
  selectedOptions14: number[] = [];
 
  div1: boolean = false;
  quest: Question;
 
  startDateAndTime: any;
  responseDetail: ResponseDetail;
  endDateAndTime: any;
  responsesList: Responses[] = [];
  userId: any = 0;
 
  popUp: boolean = false;
  hideform: boolean = true;
 
  constructor(private response: SurveyresponseService, private activatedroute: ActivatedRoute, private fb: FormBuilder, private datePipe: DatePipe, private router: Router) {
    this.survey = new Survey;
    // this.question = new Question;
    // this.option = new Option;
    this.page = new Page;
    this.responseDetail = new ResponseDetail;
    this.reference = new Ref;
    this.referenceOption = new RefOption;
    this.referenceQuestion = new RefQuestion;
    this.responses = new Responses;
    this.quest = new Question;
    this.userId = sessionStorage.getItem("userId") || "";
    this.surveyId = sessionStorage.getItem("surveyId") || "";
    console.log(this.surveyId);
   
    // this.activatedroute.paramMap.subscribe((param: Params) => {
    //   this.surveyId = param['get']("id");
    //   console.log(this.surveyId);
    //   this.getquestions();
    // })
    this.getpages();
    console.log(this.question);

    // for(let i=0;i<this.question.length;i++){
    //   var obj= {optionId[i]};
    // }
    
   

    this.myForm = new FormGroup({
      optionId0: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z + 0-9]+')]),
      optionId1: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z + 0-9]+')]),
      optionId2: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z + 0-9]+')]),
      optionId3: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z + 0-9]+')]),
      optionId4: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z + 0-9]+')]),
      optionId5: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z + 0-9]+')]),
      optionId6: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z + 0-9]+')]),
      optionId7: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z + 0-9]+')]),
      optionId8: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z + 0-9]+')]),
      optionId9: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z + 0-9]+')]),
      optionId10: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z + 0-9]+')]),
      optionId11: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z + 0-9]+')]),
      optionId12: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z + 0-9]+')]),
      optionId13: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z + 0-9]+')]),
      optionId14: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z + 0-9]+')]),
      // optionb: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z + 0-9]+')]),
      // optionc: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z + 0-9]+')]),
      // radioArray: this.fb.array([this.addRadioGroup()], [Validators.required]),
      // checkBoxArray: this.fb.array([this.addCheckBoxGroup()], [Validators.required]),
      // dropDownArray: this.fb.array([this.addDropDownGroup()], [Validators.required]),
    });
  //   for(let i=0;i<this.question.length;i++){
  //     this.myForm.controls.create(optionIdi);
  //  }
   
    
    // this.getquestions();
    // questions: this.response.getAllquestions();
  }
 
  get f() { return this.myForm.controls; };
 
 
  private addRadioGroup(): FormGroup {
    return this.fb.group({
      optiona: [, Validators.required]
    });
  }
 
 
 
  ngOnInit(): void {
    this.startDateAndTime = this.datePipe.transform(new Date(), 'yyyy-MM-dd HH:mm:ss');
      console.log(this.startDateAndTime);
  }
 
 
 
 
  getpages() {
    console.log(this.surveyId);
    this.surveyValue=this.surveyId;
    console.log(this.surveyValue);
    this.survey.surveyId =  this.surveyValue;
    console.log(this.survey.surveyId);
    this.response.getSurvey(this.survey).subscribe(survey => {
      this.survey = survey;
      console.log(this.survey);
    });
    this.response.getAllpages(this.survey).subscribe(page => {
      this.pages = page;
      console.log(this.pages);
      this.page.pageId=this.pages[0].pageId;
      this.response.getAllquestions(this.page).subscribe(questions => {
        this.question = questions;
        console.log(this.question);
      });
      this.response.getAlloptions(this.page).subscribe(opt => {
        this.options = opt;
        console.log(this.options)
      });
    });
    
    
  }

  getquestions(data:any){
   console.log(data.pageId);
   this.question=[];
   this.options=[];
   this.page.pageId=data.pageId+1;
    this.response.getAllquestions(this.page).subscribe(questions => {
      this.question = questions;
      console.log(this.question);
    });
    this.response.getAlloptions(this.page).subscribe(opt => {
      this.options = opt;
      console.log(this.options)
    });
  }

 
  submit() {
    this.endDateAndTime = this.datePipe.transform(new Date(), 'yyyy-MM-dd HH:mm:ss');
    console.log(this.endDateAndTime);
    this.responseDetail.userId = this.userId;
    this.responseDetail.surveyId = this.surveyId ;
    this.responseDetail.startTime = this.startDateAndTime;
    this.responseDetail.endTime = this.endDateAndTime;
    console.log(this.responseDetail);
    console.log(this.responsesList);
    this.response.insertResponseDetails(this.responseDetail).subscribe(
      (response: any) => {
        // alert("Successfully created");
        sessionStorage.setItem("responseDetailId", response.message);
        console.log("Inserted");
        this.responseDetailId = sessionStorage.getItem("responseDetailId") || "";
        console.log(this.responseDetailId);
        for (let i = 0; i < this.responsesList.length; i++) {
          if(this.responsesList[i].optionId==null){
            //  console.log(null);
          }
          else {
          this.responsesList[i].responseDetailId = this.responseDetailId;
          this.response.insertResponses(this.responsesList[i]).subscribe({
            next: (res: any) => {
              // alert("Successfully created");
              console.log("Inserted");
              if(res == true){
                console.log("Inserted");
                Swal.fire({
                  title: 'Response Submitted',
                  text: 'Your response has been submitted successfully!',
                  icon: 'success'
                }).then(() => {
                  this.router.navigate(['/survey']);
                })
              }
            }, error: () => {
              // alert("Error in creating the survey");
              console.log("Not");
              Swal.fire({
                title: 'Response Not Submitted',
                text: 'Your response not submitted!',
                icon: 'error'
              })
            }
          });
          console.log(this.responsesList[i]);
        }
        }
 
      }, (error: any) => {
        // alert("Error in creating the survey");
        console.log("Not");
      }
    );
 
    this.hideform = false;
    this.popUp = true;
 
    // setTimeout(() => {
    //   this.router.navigate(['/survey']);
    // }, 5000);
 
    // alert("Response Submitted");
 
  }
  
  getValue(data:any){
    console.log(data);
  }
 
  insertAnswer(data: any) {
    console.log(data);

    // this.option.options=data.options;
    // this.responses.responseQuestion=data.questions;
    // this.responses.optionId = data.optionId;
    console.log(data.optionId);
    if (data.optionId0 == "") {
    //  console.log(data.optionId0);
    } else if (data.optionId0 == true) {
      this.responses=new Responses;
      //  for(let i=0;i<this.selectedOptions.length;i++){
      this.responses.optionType = "Checkbox";
      this.responses.optionId = 10;
      this.responses.option = this.selectedOptions0;
      console.log(this.responses);
      this.responsesList.push(this.responses);
      //  }
    } else {
      this.responses=new Responses;
      this.responses.optionType = "Radio";
      this.responses.optionId = data.optionId0;
      console.log(this.responses);
      this.responsesList.push(this.responses);
    } if (data.optionId1 == "") {
      // console.log(data.optionId1);
     } else if (data.optionId1 == true) {
      this.responses=new Responses;
       //  for(let i=0;i<this.selectedOptions.length;i++){
       this.responses.optionType = "Checkbox";
       this.responses.optionId = 10;
       this.responses.option = this.selectedOptions1;
       console.log(this.responses);
       this.responsesList.push(this.responses);
       //  }
     } else {
      this.responses=new Responses;
       this.responses.optionType = "Radio";
       this.responses.optionId = data.optionId1;
       console.log(this.responses);
       this.responsesList.push(this.responses);
     } if (data.optionId2 == "") {
      // console.log(data.optionId2);
     } else if (data.optionId2 == true) {
      this.responses=new Responses;
       //  for(let i=0;i<this.selectedOptions.length;i++){
       this.responses.optionType = "Checkbox";
       this.responses.optionId = 10;
       this.responses.option = this.selectedOptions2;
       console.log(this.responses);
       this.responsesList.push(this.responses);
       //  }
     } else {
       this.responses=new Responses;
       this.responses.optionType = "Radio";
       this.responses.optionId = data.optionId2;
       console.log(this.responses);
       this.responsesList.push(this.responses);
     } if (data.optionId3 == "") {
      // console.log(data.optionId3);
     } else if (data.optionId3 == true) {
      this.responses=new Responses;
       //  for(let i=0;i<this.selectedOptions.length;i++){
       this.responses.optionType = "Checkbox";
       this.responses.optionId = 10;
       this.responses.option = this.selectedOptions3;
       console.log(this.responses);
       this.responsesList.push(this.responses);
       //  }
     } else {
      this.responses=new Responses;
       this.responses.optionType = "Radio";
       this.responses.optionId = data.optionId3;
       console.log(this.responses);
       this.responsesList.push(this.responses);
     } if (data.optionId4 == "") {
      // console.log(data.optionId4);
     } else if (data.optionId4 == true) {
      this.responses=new Responses;
       //  for(let i=0;i<this.selectedOptions.length;i++){
       this.responses.optionType = "Checkbox";
       this.responses.optionId = 10;
       this.responses.option = this.selectedOptions4;
       console.log(this.responses);
       this.responsesList.push(this.responses);
       //  }
     } else {
      this.responses=new Responses;
       this.responses.optionType = "Radio";
       this.responses.optionId = data.optionId4;
       console.log(this.responses);
       this.responsesList.push(this.responses);
     } if (data.optionId5 == "") {
      // console.log(data.optionId5);
     } else if (data.optionId15 == true) {
      this.responses=new Responses;
       //  for(let i=0;i<this.selectedOptions.length;i++){
       this.responses.optionType = "Checkbox";
       this.responses.optionId = 10;
       this.responses.option = this.selectedOptions5;
       console.log(this.responses);
       this.responsesList.push(this.responses);
       //  }
     } else {
      this.responses=new Responses;
       this.responses.optionType = "Radio";
       this.responses.optionId = data.optionId5;
       console.log(this.responses);
       this.responsesList.push(this.responses);
     } if (data.optionId6 == "") {
      // console.log(data.optionId6);
     } else if (data.optionId6 == true) {
      this.responses=new Responses;
       //  for(let i=0;i<this.selectedOptions.length;i++){
       this.responses.optionType = "Checkbox";
       this.responses.optionId = 10;
       this.responses.option = this.selectedOptions6;
       console.log(this.responses);
       this.responsesList.push(this.responses);
       //  }
     } else {
      this.responses=new Responses;
       this.responses.optionType = "Radio";
       this.responses.optionId = data.optionId6;
       console.log(this.responses);
       this.responsesList.push(this.responses);
     } if (data.optionId7 == "") {
      // console.log(data.optionId7);
     } else if (data.optionId7 == true) {
      this.responses=new Responses;
       //  for(let i=0;i<this.selectedOptions.length;i++){
       this.responses.optionType = "Checkbox";
       this.responses.optionId = 10;
       this.responses.option = this.selectedOptions7;
       console.log(this.responses);
       this.responsesList.push(this.responses);
       //  }
     } else {
      this.responses=new Responses;
       this.responses.optionType = "Radio";
       this.responses.optionId = data.optionId7;
       console.log(this.responses);
       this.responsesList.push(this.responses);
     } if (data.optionId8 == "") {
      // console.log(data.optionId8);
     } else if (data.optionId8 == true) {
      this.responses=new Responses;
       //  for(let i=0;i<this.selectedOptions.length;i++){
       this.responses.optionType = "Checkbox";
       this.responses.optionId = 10;
       this.responses.option = this.selectedOptions8;
       console.log(this.responses);
       this.responsesList.push(this.responses);
       //  }
     } else {
      this.responses=new Responses;
       this.responses.optionType = "Radio";
       this.responses.optionId = data.optionId8;
       console.log(this.responses);
       this.responsesList.push(this.responses);
     } if (data.optionId9 == "") {
      // console.log(data.optionId9);
     } else if (data.optionId9 == true) {
      this.responses=new Responses;
       //  for(let i=0;i<this.selectedOptions.length;i++){
       this.responses.optionType = "Checkbox";
       this.responses.optionId = 10;
       this.responses.option = this.selectedOptions9;
       console.log(this.responses);
       this.responsesList.push(this.responses);
       //  }
     } else {
      this.responses=new Responses;
       this.responses.optionType = "Radio";
       this.responses.optionId = data.optionId9;
       console.log(this.responses);
       this.responsesList.push(this.responses);
     } if (data.optionId10 == "") {
      // console.log(data.optionId10);
     } else if (data.optionId10 == true) {
      this.responses=new Responses;
       //  for(let i=0;i<this.selectedOptions.length;i++){
       this.responses.optionType = "Checkbox";
       this.responses.optionId = 10;
       this.responses.option = this.selectedOptions10;
       console.log(this.responses);
       this.responsesList.push(this.responses);
       //  }
     } else {
      this.responses=new Responses;
       this.responses.optionType = "Radio";
       this.responses.optionId = data.optionId10;
       console.log(this.responses);
       this.responsesList.push(this.responses);
     } if (data.optionId11 == "") {
      // console.log(data.optionId11);
     } else if (data.optionId11 == true) {
      this.responses=new Responses;
       //  for(let i=0;i<this.selectedOptions.length;i++){
       this.responses.optionType = "Checkbox";
       this.responses.optionId = 10;
       this.responses.option = this.selectedOptions11;
       console.log(this.responses);
       this.responsesList.push(this.responses);
       //  }
     } else {
      this.responses=new Responses;
       this.responses.optionType = "Radio";
       this.responses.optionId = data.optionId11;
       console.log(this.responses);
       this.responsesList.push(this.responses);
     } if (data.optionId12 == "") {
      // console.log(data.optionId12);
     } else if (data.optionId12 == true) {
      this.responses=new Responses;
       //  for(let i=0;i<this.selectedOptions.length;i++){
       this.responses.optionType = "Checkbox";
       this.responses.optionId = 10;
       this.responses.option = this.selectedOptions12;
       console.log(this.responses);
       this.responsesList.push(this.responses);
       //  }
     } else {
      this.responses=new Responses;
       this.responses.optionType = "Radio";
       this.responses.optionId = data.optionId12;
       console.log(this.responses);
       this.responsesList.push(this.responses);
     } if (data.optionId13 == "") {
      // console.log(data.optionId13);
     } else if (data.optionId13 == true) {
      this.responses=new Responses;
       //  for(let i=0;i<this.selectedOptions.length;i++){
       this.responses.optionType = "Checkbox";
       this.responses.optionId = 10;
       this.responses.option = this.selectedOptions13;
       console.log(this.responses);
       this.responsesList.push(this.responses);
       //  }
     } else {
      this.responses=new Responses;
       this.responses.optionType = "Radio";
       this.responses.optionId = data.optionId13;
       console.log(this.responses);
       this.responsesList.push(this.responses);
     } if (data.optionId14 == "") {
      // console.log(data.optionId14);
     } else if (data.optionId14 == true) {
      this.responses=new Responses;
       //  for(let i=0;i<this.selectedOptions.length;i++){
       this.responses.optionType = "Checkbox";
       this.responses.optionId = 10;
       this.responses.option = this.selectedOptions14;
       console.log(this.responses);
       this.responsesList.push(this.responses);
       //  }
     } else {
      this.responses=new Responses;
       this.responses.optionType = "Radio";
       this.responses.optionId = data.optionId14;
       console.log(this.responses);
       this.responsesList.push(this.responses);
     }
    console.log(this.responsesList);
    this.responses = new Responses;
    // this.response.insertResponses(this.responses).subscribe({
    //   next: (res: any) => {
    //     // alert("Successfully created");
    //   }, error: () => {
    //     // alert("Error in creating the survey");
    //   }
    // });
 
    console.log(data.optionId);
    // alert(this.responses.responseId);
    console.log(this.responses);
    // this.selectedOptions0 = [];
    this.myForm.reset();
 
  }
 
 
  nextPage() {
    this.num = this.num + 1;  //Page
 
  }
 
  nextQuestion() {
    // if(this.num == 0 && this.num1==null){
    //   this.num1=this.num1+1;
    // }
 
    this.num1 = this.num1 + 1;  //Question
 
  }
 
 
  decnum(i: number) {
    this.i = this.i - 1;
    this.num = this.num - 1;
  }
 
 
 
  // previousPage(pages: number){
  //   this.activePage = pages + 1;
  // }
 
 
  showPageIndex(pageIndex: Page) {
    this.page = pageIndex;
    console.log(this.page);
  }
 
  div1Function() {
    this.div1 = !this.div1;
    // this.div1=true;
  }

  
 

// Multiple choice
 j!:number;
  onCheckboxChange( j:number,optionId: number, event: any) {

    // console.log(j);
    // for(let j==){

    // for(let i=0;i<this.question.length;i++){
    //     selectedOptions[i]=number[]=[];
    // }
      if(j==0){
    if (event.target.checked) {
      console.log(this.selectedOptions0);
      this.selectedOptions0.push(optionId);
    } else {
      const index = this.selectedOptions0.indexOf(optionId);
      if (index >= 0) {
        this.selectedOptions0.splice(index, 1);
      }
    }
  }
  else if(j==1){
if (event.target.checked) {
  console.log(this.selectedOptions1);
  this.selectedOptions1.push(optionId);
} else {
  const index = this.selectedOptions1.indexOf(optionId);
  if (index >= 0) {
    this.selectedOptions1.splice(index, 1);
  }
}
}
else if(j==2){
if (event.target.checked) {
console.log(this.selectedOptions2);
this.selectedOptions2.push(optionId);
} else {
const index = this.selectedOptions2.indexOf(optionId);
if (index >= 0) {
  this.selectedOptions2.splice(index, 1);
}
}
}
 else if(j==3){
if (event.target.checked) {
console.log(this.selectedOptions3);
this.selectedOptions3.push(optionId);
} else {
const index = this.selectedOptions3.indexOf(optionId);
if (index >= 0) {
  this.selectedOptions3.splice(index, 1);
}
}
}

 else if(j==4){
if (event.target.checked) {
console.log(this.selectedOptions4);
this.selectedOptions4.push(optionId);
} else {
const index = this.selectedOptions4.indexOf(optionId);
if (index >= 0) {
  this.selectedOptions4.splice(index, 1);
}
}
}
 else if(j==5){
if (event.target.checked) {
console.log(this.selectedOptions5);
this.selectedOptions5.push(optionId);
} else {
const index = this.selectedOptions5.indexOf(optionId);
if (index >= 0) {
  this.selectedOptions5.splice(index, 1);
}
}
}
 else if(j==6){
if (event.target.checked) {
console.log(this.selectedOptions6);
this.selectedOptions6.push(optionId);
} else {
const index = this.selectedOptions6.indexOf(optionId);
if (index >= 0) {
  this.selectedOptions6.splice(index, 1);
}
}
}
else if(j==7){
if (event.target.checked) {
console.log(this.selectedOptions7);
this.selectedOptions7.push(optionId);
} else {
const index = this.selectedOptions7.indexOf(optionId);
if (index >= 0) {
  this.selectedOptions7.splice(index, 1);
}
}
}
 else if(j==8){
if (event.target.checked) {
console.log(this.selectedOptions8);
this.selectedOptions8.push(optionId);
} else {
const index = this.selectedOptions8.indexOf(optionId);
if (index >= 0) {
  this.selectedOptions8.splice(index, 1);
}
}
} 
 else if(j==9){
if (event.target.checked) {
console.log(this.selectedOptions9);
this.selectedOptions9.push(optionId);
} else {
const index = this.selectedOptions9.indexOf(optionId);
if (index >= 0) {
  this.selectedOptions9.splice(index, 1);
}
}
}
 else if(j==10){
if (event.target.checked) {
console.log(this.selectedOptions10);
this.selectedOptions10.push(optionId);
} else {
const index = this.selectedOptions10.indexOf(optionId);
if (index >= 0) {
  this.selectedOptions10.splice(index, 1);
}
}
} 
 else  if(j==11){
if (event.target.checked) {
console.log(this.selectedOptions11);
this.selectedOptions11.push(optionId);
} else {
const index = this.selectedOptions11.indexOf(optionId);
if (index >= 0) {
  this.selectedOptions11.splice(index, 1);
}
}
}
 else  if(j==12){
if (event.target.checked) {
console.log(this.selectedOptions12);
this.selectedOptions12.push(optionId);
} else {
const index = this.selectedOptions12.indexOf(optionId);
if (index >= 0) {
  this.selectedOptions12.splice(index, 1);
}
}
}
 else  if(j==13){
if (event.target.checked) {
console.log(this.selectedOptions13);
this.selectedOptions13.push(optionId);
} else {
const index = this.selectedOptions13.indexOf(optionId);
if (index >= 0) {
  this.selectedOptions13.splice(index, 1);
}
}
}
 else if(j==14){
if (event.target.checked) {
console.log(this.selectedOptions14);
this.selectedOptions14.push(optionId);
} else {
const index = this.selectedOptions14.indexOf(optionId);
if (index >= 0) {
  this.selectedOptions14.splice(index, 1);
}
}
}
  else{
    console.log(j,optionId);
  }
  // }
  }

  
}