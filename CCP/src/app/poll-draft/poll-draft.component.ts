import { Component, ElementRef, Renderer2 } from "@angular/core";

import { Option } from "src/app/model/Option";
import { PollDTO } from "../../model/PollDTO";
import { User } from "../../model/User";
import { Poll } from "../../model/poll";
import { PollService } from "../Services/poll.service";
import Swal from "sweetalert2";
import { Router } from "@angular/router";
import { AbstractControl, FormArray, FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";
import { Department } from "src/app/model/Department";
import { Project } from "src/app/model/Project";
import { Region } from "src/app/model/Region";
import { DatePipe } from "@angular/common";

@Component({
  selector: "app-poll-draft",
  templateUrl: "./poll-draft.component.html",
  styleUrls: ["./poll-draft.component.css"],
})
export class PollDraftComponent {
  Poll: any;

  polls: Poll[] = [];

  optionData: any;

  User: any;

  users: User[] = [];

  optionId: any;

  Option: Option;

  option: Option[] = [];

  OptionList: Option[] = [];

  date: Date | undefined;

  pollData: PollDTO;

  pl: PollDTO = new PollDTO();
  result: any;

  valuesForm!: FormGroup;

  public pollForm: FormGroup;

  pollQuestion:string='';

  values: any[] = [];

  pollValue:any;

  disabledComments: boolean = false;

  deptValue: string = "";

  projectId: string = "";

  departmentId: string = "";

  regionId: string = "";

  deptVal: number = 0;

  startDate: any;

  compareDate: any;

  msg: string = "";

  RegionList: Region[] = [];
  DeptList: Department[] = [];
  ProjectList: Project[] = [];
  department: Department;
  project: Project;
  region: Region;
  Allreg!: number;
  Alldept!: number;
  Allproj!: number;

  toggle = true;

  ngOnInit() {
    this.initForm();
    this.pollService.getDraft().subscribe((AllPoll) => {
      this.polls = AllPoll;
    });
    this.pollService.getOption().subscribe((option) => {
      this.option = option;
    });
  }

  constructor(
    private pollService: PollService,
    private elementRef: ElementRef,
    private renderer: Renderer2,
    private router: Router,
    private _fb:FormBuilder,
    private datePipe: DatePipe,
  ) {
    this.Option = new Option();

    this.optionData = new Option();

    this.pollData = new PollDTO();

    this.region = new Region();

    this.department = new Department();

    this.project = new Project();

    this.Allreg = 1;

    this.Alldept = 1;

    this.Allproj = 1;

    this.pollForm = this._fb.group({
      pollQuestion: new FormControl("", [Validators.required]),
      options: this._fb.array(
        [this.addPollGroup()],
        [Validators.required, Validators.minLength(2)]
      ),
    });
  
  
  }

  removeOption(index: number): void {
    this.optionArray.removeAt(index);
  }

  minLengthArray(min: number) {
    return (c: AbstractControl): { [key: string]: any } => {
      if (c.value.length >= min) {
        return { minLengthArray: { valid: true } };
      }
      return { minLengthArray: { valid: false } };
    };
  }

  addOption(): void {
    this.optionArray.push(this.addPollGroup());
  }

  private addPollGroup(): FormGroup {
    return this._fb.group({
      value: ["", Validators.required],
    });
  }

  get optionArray(): FormArray {
    return <FormArray>this.pollForm.get("options");
  }

  initForm(): void {
    this.valuesForm = this._fb.group({});
        this.values.forEach((_, index) => {
          this.valuesForm.addControl(
            'valueGroup${index}',
            this.valueFormGroup(index)
          );
        });
      }
     
      valueFormGroup(index: number): FormGroup {
      return this._fb.group({
          value: [this.values[index].value, Validators.required],
        });
      }

      toggleDisabledComments()
      {
        this.disabledComments = true;
      }


    

      validDate(data: any) {
        this.compareDate = data.endDate;
        this.startDate = this.datePipe.transform(new Date(), "yyyy-MM-dd ");
        if (this.compareDate < this.startDate) {
          this.msg = "Poll End date is invalid";
        }
      }

  getdraft() {
    this.pollService.getDraft().subscribe(() => {
      this.pollService.getOption().subscribe((save) => {
        this.option = save;
      });
    });
  }

  refreshPage() {
    location.reload();
  }

  deleteDraft(pollId: number) {
    Swal.fire({
      title: "Are you sure you want to delete the draft!!!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#1B193F",
      confirmButtonText: "Ok!",
      cancelButtonColor: "#97247E",
      cancelButtonText: "cancel",
      customClass: {
        popup: "my-popup-class",
      },
    }).then((result) => {
      if (result.isConfirmed) {
        this.pollService.deleteDraft(pollId).subscribe((res) => {
          this.refreshPage();
        });
      } else {
        this.refreshPage();
      }
    });
  }

  getDraftById(pollId: number, question:any,values:any) {
    this.pollValue = pollId;
    console.log(this.pollValue)
    this.pollQuestion = question;
    console.log(this.pollQuestion)
    this.values = this.option
    .filter((opt) => opt.pollId === this.pollValue)
    .map((opt) => ({ value: opt.value, optionId: opt.optionId }));
    console.log(this.values)
    this.pollService.getDraftById(pollId).subscribe((value) => {
      this.pollData = value;
    });
    this.pollService.getDraftOptionsById(pollId).subscribe((value) => {
      this.OptionList = value;
    });
  }

  sharePoll(id: any, question:any, value:any) {
    console.log(value)
    this.pl.pollId = id;
    this.pl.status = true;
    this.pl.pollQuestion =this.pollQuestion;
    this.pl.options = value;
    console.log(this.pl.options);
    console.log(this.pl);
    this.result = this.pollService.updateDraft(this.pl).subscribe();
    alert("poll shared successfully");
  }
}
