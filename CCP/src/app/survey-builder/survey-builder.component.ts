import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Page } from '../Survey-Model/page';
import { Question } from '../Survey-Model/question';
import { Option } from '../Survey-Model/option';
import { SurveybuilderService } from '../survey-service/surveybuilder.service';
import { SurveyQuestion } from '../Survey-Model/surveyquestion';
import { PageValue } from '../Survey-Model/PageValue';
import { QuestionValue } from '../Survey-Model/QuestionValue';
import { OptionValue } from '../Survey-Model/OptionValue';
import { Router } from '@angular/router';
import { Survey } from '../Survey-Model/survey';
import { Responses } from '../Survey-Model/responses';
import { Reference } from '../Survey-Model/Reference';
import { NewReference } from '../Survey-Model/NewReference';
import Swal from 'sweetalert2';
 
@Component({
  selector: 'app-survey-builder',
  templateUrl: './survey-builder.component.html',
  styleUrls: ['./survey-builder.component.css']
})
export class SurveyBuilderComponent implements OnInit {
  myGroup: FormGroup;
  survey: Survey;
  response: Responses;
  pageValue: String[] = [];
  reference: Reference;
  referenceList: Reference[] = [];
  newreferenceList: NewReference;
  pages: Page;
  questionList: Question[] = [];
  question: Question;
  quest = [];
  option: Option;
  pgs: Page[] = [];
  opts: Option[] = [];
  surveyPages: any[] = [{ index: 1, questions: [] }];
  currentPageIndex: number = 0;
  editorVisible: boolean = false;
  selectedFieldType: string = '';
  questionText: string = '';
  answerText: string = '';
  options: string[] = []; // Added options array
  selectedDropdownOption: string = '';
  successMessage: string = '';
  showPlaceholder: { [key: number]: boolean } = {};
  viewPlaceholder: { [key: number]: boolean } = {};
  dragOverPage: number | null = null;
  formBuilder: any;
 
 
  surveyId!: any;
  addnewpage: any = 'false';
 
  pageNo: number = 1;
  pageTitle1!: string;
  pageTitle2!: string;
  pageTitle3!: string;
  no!: number;
  questionno: number=0;
  questionnoList1: number[] = [];
  questionnoList2: number[] = [];
  questionnoList3: number[] = [];
  pagevalue1: PageValue;
  pagevalue2: PageValue;
  pagevalue3: PageValue;
  pagevalueList: PageValue[] = [];
  optionValue: OptionValue;
  optionValueList: OptionValue[] = [];
  optionValueList1: OptionValue[] = [];
  newReferenceList: NewReference[] = [];
  newReferenceList1: NewReference[] = [];
  newreference: NewReference;
  optionType!: string;
  removeValue!: number;
  removeValueList: number[] = [];
  // optionArray!:FormArray;
 
  // displaybox: boolean = true;
 
  mandatory: boolean = false;
 
 
  constructor(private fb: FormBuilder, private service: SurveybuilderService, private router: Router) {
 
    this.surveyId = sessionStorage.getItem("surveyId") || "";
    this.initPlaceholderVisibility();
    this.survey = new Survey;
    this.response = new Responses;
    this.newreferenceList = new NewReference;
    this.reference = new Reference;
    this.pages = new Page;
    this.question = new Question;
    this.option = new Option;
    this.pagevalue1 = new PageValue;
    this.pagevalue2 = new PageValue;
    this.pagevalue3 = new PageValue;
    this.optionValue = new OptionValue;
    this.newreference = new NewReference;
 
    this.myGroup = this.fb.group({
      pageTitle: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z + 0-9]+')]),
      pageTitle2: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z + 0-9]+')]),
      pageTitle3: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z + 0-9]+')]),
      questions: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z + 0-9]+')]),
      // optionArray:this.fb.array([])
      optionArray: this.fb.array([this.addOptionGroup()], [Validators.required])
      // questionArray:new FormArray([])
    });
 
    // this.myForm = this.fb.group({
    //   pageTitle: ['', [Validators.required, Validators.pattern('[a-zA-Z + 0-9]+')]],
    //   questionText: ['', [Validators.required, Validators.pattern('[a-zA-Z + 0-9]+')]],
    //   options: ['', [Validators.required, Validators.pattern('[a-zA-Z + 0-9]+')]]
    // });
 
 
    // this.myForm = this._fb.group({
    //   pageTitle: new FormControl('', [Validators.required, Validators.maxLength(50)]),
    //   pageArray: this._fb.array([this.addPageGroup()], [Validators.required]),
    //   // questionArray: this._fb.array([this.addQuestionGroup()], [Validators.required]),
    //   // optionArray: this._fb.array([this.addPageGroup()], [Validators.required]),
    // });
    this.getPage();
  }
  ngOnInit() {
    if (!localStorage.getItem('builder')) {
      localStorage.setItem('builder', 'no reload')
      location.reload()
    } else {
      localStorage.removeItem('builder')
    }
  }
 


  private addOptionGroup(): FormGroup {
    return this.fb.group({
      options: [, Validators.required]
    });
  }
 
  // get optionsList() {
  //   return (<FormArray>this.myGroup.get('optionArray'))
  // }
 
  //   get questions(){
  //     return (<FormArray>this.myGroup.get('questionArray')).controls
  // }
 
 
  initPlaceholderVisibility() {
    this.surveyPages.forEach((_, index) => {
      this.showPlaceholder[index] = true;
    });
  }
 
  onDragStart(event: any, type: string) {
    event.dataTransfer.setData('type', type);
  }
 
  onDragOver(event: any, pageIndex: number) {
    event.preventDefault();
    this.dragOverPage = pageIndex;
  }
 
  onDragEnter(pageIndex: number) {
    this.dragOverPage = pageIndex;
  }
 
  onDragLeave(pageIndex: number) {
    if (this.dragOverPage === pageIndex) {
      this.dragOverPage = null;
    }
  }
 
  onDrop(event: any, pageIndex: number, fieldType: string, data: any) {
    this.pageNo = pageIndex + 1;
  if(this.pageNo==1){
    if(this.questionnoList1.length==0 || this.questionnoList1.length<15){
    event.preventDefault();
    const type = event.dataTransfer.getData('type');
    this.selectedFieldType = this.getAnswerType(type, fieldType);
    const question = new SurveyQuestion(type, '', '');
 
    if (type === 'checkbox' || type === 'radio' || type === 'dropdown') {
      question.options = ['Option 1', 'Option 2', 'Option 3'];
    }
    this.surveyPages[pageIndex].questions.push(question);
    this.showPlaceholder[pageIndex] = false;
    this.dragOverPage = null;
    this.optionType = type;
    console.log(this.optionType);
    this.no = 1;
    this.questionnoList1.push(this.no);
    console.log(this.questionnoList1.length);
    if (this.questionnoList1.length == 1) {
      this.pageTitle1 = data.pageTitle;
      console.log(this.pageTitle1);
    }
    this.questionno = this.questionnoList1.length;
  } else{
    this.viewPlaceholder[pageIndex]=true;
  }
} else if(this.pageNo==2){
  if(this.questionnoList2.length==0 || this.questionnoList2.length<15){
  event.preventDefault();
  const type = event.dataTransfer.getData('type');
  this.selectedFieldType = this.getAnswerType(type, fieldType);
  const question = new SurveyQuestion(type, '', '');

  if (type === 'checkbox' || type === 'radio' || type === 'dropdown') {
    question.options = ['Option 1', 'Option 2', 'Option 3'];
  }
  this.surveyPages[pageIndex].questions.push(question);
  this.showPlaceholder[pageIndex] = false;
  this.dragOverPage = null;
  this.optionType = type;
  console.log(this.optionType);
  this.no = 1;
      this.questionnoList2.push(this.no);
      this.questionno = this.questionnoList2.length;
      if (this.questionnoList2.length == 1) {
        this.pageTitle2 = data.pageTitle2;
        console.log(this.pageTitle2);
      } 
} else{
  this.viewPlaceholder[pageIndex]=true;
}
} else if(this.pageNo==3){
  if(this.questionnoList3.length==0 || this.questionnoList3.length<15){
  event.preventDefault();
  const type = event.dataTransfer.getData('type');
  this.selectedFieldType = this.getAnswerType(type, fieldType);
  const question = new SurveyQuestion(type, '', '');

  if (type === 'checkbox' || type === 'radio' || type === 'dropdown') {
    question.options = ['Option 1', 'Option 2', 'Option 3'];
  }
  this.surveyPages[pageIndex].questions.push(question);
  this.showPlaceholder[pageIndex] = false;
  this.dragOverPage = null;
  this.optionType = type;
  console.log(this.optionType);
  this.no = 1;
      this.questionnoList3.push(this.no);
      this.questionno = this.questionnoList3.length;
      if (this.questionnoList3.length == 1) {
        this.pageTitle3 = data.pageTitle3;
        console.log(this.pageTitle3);
 
      }
} else{
  this.viewPlaceholder[pageIndex]=true;
}
} 
// if (this.pageNo == 1) {
//       this.no = 1;
//       this.questionnoList1.push(this.no);
//       console.log(this.questionnoList1.length);
//       if (this.questionnoList1.length == 1) {
//         this.pageTitle1 = data.pageTitle;
//         console.log(this.pageTitle1);
//       }
//       this.questionno = this.questionnoList1.length;
//     } else if (this.pageNo == 2) {
//       this.no = 1;
//       this.questionnoList2.push(this.no);
//       this.questionno = this.questionnoList2.length;
//       if (this.questionnoList2.length == 1) {
//         this.pageTitle2 = data.pageTitle;
//         console.log(this.pageTitle2);
//       }
//     } else if (this.pageNo == 3) {
//       this.no = 1;
//       this.questionnoList3.push(this.no);
//       this.questionno = this.questionnoList3.length;
//       if (this.questionnoList3.length == 1) {
//         this.pageTitle3 = data.pageTitle;
//         console.log(this.pageTitle3);
 
//       }
//     }
 
  }
 
 
  getPageTitle(data: any) {
    // console.log(data);
  }
 
 
  toggleOffcanvas() {
    this.hidden(this.pageNo - 1, 0);
    if(this.questionnoList1.length<16){
    this.showPlaceholder[this.pageNo - 1] = true;
    if(this.pageNo==1){
      this.questionnoList1.pop();
    } else if(this.pageNo==2){
      this.questionnoList2.pop();
    } else if(this.pageNo==3){
      this.questionnoList3.pop();
    }
    this.myGroup = new FormGroup({
      questions: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z + 0-9]+')]),
      optionArray: new FormArray([])
      // questionArray:new FormArray([])
    });
    }
  }
 
  onQuestionDragStart(event: any, question: any) {
    event.dataTransfer.setData('question', JSON.stringify(question));
  }
 
  saveQuestion() {
    if (!this.questionText.trim() || (!this.options.length && this.selectedFieldType !== 'dropdown-one' && this.selectedFieldType !== 'multiple-choice-one')) {
      this.successMessage = '';
      return;
    }
 
    let answer: string | string[];
    if (this.selectedFieldType === 'dropdown-one' || this.selectedFieldType === 'multiple-choice-one') {
      answer = this.answerText;
    } else {
      answer = this.options.map(option => option);
    }
 
    const question = new SurveyQuestion(this.selectedFieldType, this.questionText, answer as string | string[]);
    if (this.selectedFieldType === 'dropdown' || this.selectedFieldType === 'dropdown-many') {
      question.options = this.options.map(option => option);
    } else if (this.selectedFieldType === 'checkbox' || this.selectedFieldType === 'radio') {
      question.options = this.options.map(option => option);
    }
 
    this.surveyPages[this.currentPageIndex].questions.push(question);
    this.successMessage = 'Question saved successfully!';
    this.resetFields();
  }
 
  removeField(pageIndex: number, fieldIndex: number) {
    this.surveyPages[pageIndex].questions.splice(fieldIndex, 1);
    if (this.surveyPages[pageIndex].questions.length === 0) {
      this.showPlaceholder[pageIndex] = true;
    }
  }
 
  addPage() {
    const pg = { index: this.surveyPages.length + 1, questions: [] };
    this.surveyPages.push(pg);
 
    this.showPlaceholder[pg.index - 1] = true;
    // this.questionnoList=[];
  }
 
  removePage(pageIndex: number) {
 
    this.surveyPages.splice(pageIndex, 1);
    for (let i = pageIndex; i < this.surveyPages.length; i++) {
      this.showPlaceholder[i] = true;
    }
    if (this.showPlaceholder[pageIndex - 1] === undefined) {
      this.showPlaceholder[pageIndex - 1] = true;
    }
 
  }
 
  getAnswerType(questionType: string, fieldType: string): string {
    if (questionType === 'multiple-choice-one' && fieldType === 'radio') {
      return 'radio';
    } else if (questionType === 'multiple-choice-many' && fieldType === 'checkbox') {
      return 'checkbox';
    } else if ((questionType === 'dropdown-one' || questionType === 'dropdown-many') && fieldType === 'dropdown') {
      return 'dropdown';
    } else {
      return ''; // Default to empty string if no match
    }
  }
 
  resetFields() {
    this.questionText = '';
    this.answerText = '';
    this.options = [];
  }
 
  addOption(): void {
    this.optionArray.push(this.addOptionGroup());
  }
 
  get optionArray(): FormArray {
    return <FormArray>this.myGroup.get('optionArray');
  }
 
  removeOption(index: number): void {
    this.optionArray.removeAt(index);
  }
 
 
  getPage() {
    this.referenceList;
  }
 
  // getquestions(data: any) {
  //   this.survey.surveyId = this.surveyId;
  //   this.service.getAllpages(this.survey).subscribe(pageSurvey => {
  //     this.pages = pageSurvey;
  //     console.log(this.pages)
  //   });
  //   this.service.getAllquestions(this.survey).subscribe(questionSurvey => {
  //     this.questionList = questionSurvey;
  //     console.log(this.question)
  //   });
  //   this.service.getAlloptions(this.survey).subscribe(optionSurvey => {
  //     this.options = optionSurvey;
  //     console.log(this.options)
  //   });
  // }
 
  insertPage(data: any) {
    console.log(data);
    console.log(this.myGroup.value.questions);
    this.quest = [];
    // this.questionList(this.myGroup.value.questions);
    this.newreference.optionType = this.optionType;
    this.newreference.questionNo = this.questionno;
    this.newreference.mandatory=this.mandatory;
   
    this.newreference.questions = this.myGroup.value.questions;
    // this.myGroup.value.optionArray.removeAt(this.removeValue);
    console.log(this.myGroup.value.optionArray);
    for (let i = 0; i < this.myGroup.value.optionArray.length; i++) {
      console.log(this.myGroup.value.optionArray.length - 1);
      console.log(i);
      // if (i == this.myGroup.value.optionArray.length - 1 || i==this.removeValue) {
      //   console.log("null");
      // }
      // if (i == this.myGroup.value.optionArray.length - 1) {
      //     console.log("null");
      //   }
      // else {
      // this.optionValue.options = data.optionArray[i].options;
      // this.optionValue.questionNo = data.questionNo;
      // this.optionValue.pageNo = data.pageNo;
      // this.optionValueList.push(this.optionValue);
      // this.optionValueList1.push(this.optionValue);
      // this.optionValue = new OptionValue;
      // this.newreference.options=this.myGroup.value.optionArray;
      // this.quest.push(this.myGroup.value.optionArray[i]);
      console.log(this.myGroup.value.optionArray[i]);
      // this.optionvalue=this.myGroup.value.optionArray[i];
      // this.quest.push(this.optionvalue);
 
      this.optionValue.options = this.myGroup.value.optionArray[i].options;
      this.optionValue.pageNo = this.pageNo;
      this.optionValue.questionNo = this.questionno;
      this.optionValueList1.push(this.optionValue);
      this.optionValueList.push(this.optionValue);
      this.optionValue = new OptionValue;
 
      // }
    }
    console.log(this.optionValue);
    console.log(this.myGroup.value.optionArray);
    this.newreference.option = this.optionValueList1;
    // this.newreference.pageTitle=this.myGroup.value.pageTitle;
    this.newreference.pageNo = this.pageNo;
    if (this.newreference.pageNo == 1) {
      this.newreference.pageTitle = this.pageTitle1;
    } else if (this.newreference.pageNo == 2) {
      this.newreference.pageTitle = this.pageTitle2;
    } else if (this.newreference.pageNo == 3) {
      this.newreference.pageTitle = this.pageTitle3;
    }
    this.optionValueList1 = [];
    console.log(this.newreference);
    this.newReferenceList.push(this.newreference);
    this.newReferenceList1.push(this.newreference);
    this.newreference = new NewReference;
    console.log(this.newReferenceList);
    this.hidden(this.pageNo - 1, 0);
    if(this.pageNo==1){
    if(this.questionnoList1.length<15){
      this.showPlaceholder[this.pageNo - 1] = true;
      }
    } else if (this.pageNo==2){
      if(this.questionnoList2.length<15){
        this.showPlaceholder[this.pageNo - 1] = true;
        }
    } else if (this.pageNo==3){
      if(this.questionnoList3.length<15){
        this.showPlaceholder[this.pageNo - 1] = true;
        }
    }
 
    // this.displaybox = false;
    if(this.pageNo==1){
    this.myGroup = new FormGroup({
      pageTitle2: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z + 0-9]+')]),
      pageTitle3: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z + 0-9]+')]),
      questions: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z + 0-9]+')]),
      optionArray: new FormArray([])
    });
  } else if(this.pageNo==2){
    this.myGroup = new FormGroup({
      pageTitle3: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z + 0-9]+')]),
      questions: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z + 0-9]+')]),
      optionArray: new FormArray([])
    });
  } else{
    this.myGroup = new FormGroup({
      questions: new FormControl('', [Validators.required, Validators.pattern('[a-zA-Z + 0-9]+')]),
      optionArray: new FormArray([])
    });
  }
 
 
 
 
 
 
 
 
    // this.newreferenceList.push(this.myGroup.value);
    // console.log(this.newreferenceList);
    // this.reference.pageTitle=data.pageTitle;
    // this.reference.questions=data.questions;
    // this.reference.options=data.options;
    // this.referenceList.push(this.reference);
    // console.log(this.referenceList);
    // this.getquestions(26);
    // this.pages.pageTitle=data.pageTitle;
    // // alert(this.responses.responseId);
    // this.pages.surveyId=data.surveyId=26;
    // this.service.insertPages(this.pages).subscribe({
    //   next: (res: any) => {
    //     alert("Successfully created");
    //   }, error: () => {
    //     // alert("Error in creating the survey");
    //   }
    // });
    // this.question.questions=data.questions;
    // // alert(this.responses.responseId);
    // this.service.insertQuestions(this.question).subscribe({
    //   next: (res: any) => {
    //     alert("Successfully created");
    //   }, error: () => {
    //     // alert("Error in creating the survey");
    //   }
    // });
    // this.option.options=data.options;
    // // alert(this.responses.responseId);
    // this.service.insertOptions(this.option).subscribe({
    //   next: (res: any) => {
    //     alert("Successfully created");
    //   }, error: () => {
    //     alert("Successfully Created");
    //   }
    // });
  }
 
 
  publish() {
   
    for (let i = 0; i < this.newReferenceList.length; i++) {
      console.log(this.newReferenceList[i]);
      if (this.newReferenceList[i].pageNo == 1) {
        this.pagevalue1.pageNo = this.newReferenceList[i].pageNo;
        this.pagevalue1.pageTitle = this.newReferenceList[i].pageTitle;
        this.pagevalue1.surveyId = this.surveyId;
        this.pagevalue1.question.push(this.newReferenceList[i]);
      } else if (this.newReferenceList[i].pageNo == 2) {
        this.pagevalue2.pageNo = this.newReferenceList[i].pageNo;
        this.pagevalue2.pageTitle = this.newReferenceList[i].pageTitle;
        this.pagevalue2.surveyId = this.surveyId;
        this.pagevalue2.question.push(this.newReferenceList[i]);
      } else if (this.newReferenceList[i].pageNo == 3) {
        this.pagevalue3.pageNo = this.newReferenceList[i].pageNo;
        this.pagevalue3.pageTitle = this.newReferenceList[i].pageTitle;
        this.pagevalue3.surveyId = this.surveyId;
        this.pagevalue3.question.push(this.newReferenceList[i]);
      }
    };
    console.log(this.pagevalue1);
    if (this.pagevalue1.pageNo != null) {
      this.pagevalueList.push(this.pagevalue1);
      // this.service.insertPages(this.pagevalue1).subscribe({
      //     next: (res: any) => {
      //       // this.router.navigate(['/create/builder']);
      //       // alert("Successfully created");
      //     }, error: () => {
      //       // alert("Successfully created");
      //     }
      //   });
    }
    if (this.pagevalue2.pageNo != null) {
      this.pagevalueList.push(this.pagevalue2);
      //  this.service.insertPages(this.pagevalue2).subscribe({
      //   next: (res: any) => {
      //     // this.router.navigate(['/create/builder']);
      //     // alert("Successfully created");
      //   }, error: () => {
      //     // alert("Successfully created");
      //   }
      // });
    }
    if (this.pagevalue3.pageNo != null) {
      this.pagevalueList.push(this.pagevalue3);
      // this.service.insertPages(this.pagevalue3).subscribe({
      //   next: (res: any) => {
      //     // this.router.navigate(['/create/builder']);
      //     // alert("Successfully created");
      //   }, error: () => {
      //     // alert("Successfully created");
      //   }
      // });
    }
    console.log(this.pagevalueList);
 
    //  for(let i=0;i<this.pagevalueList.length;i++){
    this.service.insertPages(this.pagevalueList).subscribe({
      next: (res: any) => {
        Swal.fire({
          title: 'Published',
          text: 'The survey has been published successfully!',
          icon: 'success'
        }).then(() => {
          this.router.navigate(['/survey']);
        });
        // this.router.navigate(['/survey']);
        // alert("Successfully created");
      }, error: () => {
        // alert("Successfully created");
        Swal.fire({
          title: 'Unable to Publish',
          text: 'Sorry,Unable to publish your survey!',
          icon: 'error'
        }).then(() => {
          this.router.navigate(['/survey']);
        });
      }
    });
  }
 
  hidden(pageIndex: number, fieldIndex: number) {
    this.surveyPages[pageIndex].questions.splice(fieldIndex, 1);
    if (this.surveyPages[pageIndex].questions.length === 0) {
      this.showPlaceholder[pageIndex] = false;
    }
  }
 
 
  // insertPage(formData: any) {
  //   // send formData to backend
  //   // this.http.post('your_backend_url', formData).subscribe(response => {
  //   //   console.log('Response from backend:', response);
  //   // });
  // }
 
 
  toggleDisplayShare() {
    this.mandatory = true;
  }
 
}
 
 