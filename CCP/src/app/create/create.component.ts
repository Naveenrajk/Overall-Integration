import { Component, ElementRef, Renderer2 } from "@angular/core";
import {
  AbstractControl,
  FormArray,
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from "@angular/forms";
import { PollService } from "../Services/poll.service";
import { Department } from "src/app/model/Department";
import { PollValue } from "src/app/model/PollValue";
import { Region } from "src/app/model/Region";
import { Poll } from "src/app/model/poll";
import { DatePipe } from "@angular/common";
import { Project } from "src/app/model/Project";
import { Router } from "@angular/router";
import { Mapping } from "src/app/model/Mapping";
import { Question } from "src/app/model/Question";
import { Option } from "src/app/model/Option";

@Component({
  selector: "app-create",
  templateUrl: "./create.component.html",
  styleUrls: ["./create.component.css"],
})
export class CreateComponent {
  title = "PollCreate";

  // options = FormArray;
  pollQuestion = "";
  value = [];

  previewImages: { [key: number]: string } = {};
  MaxSize: number = 1048576; //1MB (1024 * 1024)

  result: any;

  Show = true;

  Hide = true;

  toggleDisplayView() {
    this.Show = !this.Show;
    this.Hide = !this.Hide;
  }

  toggle = true;

  label = false;
  toggleDisplayShare() {
    this.toggle = !this.toggle;
    this.label = !this.label;
  }

  RegionList: Region[] = [];
  DeptList: Department[] = [];
  ProjectList: Project[] = [];
  mapList: Mapping[] = [];
  department: Department;
  project: Project;
  region: Region;
  Allreg!: number;
  Alldept!: number;
  Allproj!: number;
  poll: Poll;
  userId!: string;
  public pollForm: FormGroup;

  deptValue: string = "";

  projectId: string = "";

  departmentId: string = "";

  regionId: string = "";

  deptVal: number = 0;

  startDate: any;

  compareDate: any;

  msg: string = "";



  pollCreatemsg: boolean = false;

  pollCreate: boolean = true;

  pollDraftmsg: boolean = false;

  pl: PollValue = new PollValue();

  pollQue: Question;

  pollOpt: Option;

  pollImageList: File[] = [];
  pollValueList: string[] = [];

  ngOnInit() {
    this.projectId = sessionStorage.getItem("projectId") || "";
    this.deptValue = this.projectId.substring(2, 1);
    this.deptVal = parseInt(this.deptValue);
  }

  constructor(
    private _fb: FormBuilder,
    private PollService: PollService,
    private datePipe: DatePipe,
    private elementRef: ElementRef,
    private renderer: Renderer2
  ) {
    this.region = new Region();

    this.department = new Department();

    this.project = new Project();

    this.Allreg = 1;

    this.Alldept = 1;

    this.Allproj = 1;
    this.poll = new Poll();

    this.pollQue = new Question();

    this.pollOpt = new Option();

    this.pollForm = this._fb.group({
      pollQuestion: new FormControl("", [Validators.required]),
      options: this._fb.array(
        [this.addPollGroup()],
        [Validators.required, Validators.minLength(2)]
      ),
      regionId: new FormControl("", [Validators.pattern("")]),
      departmentId: new FormControl("", []),
      projectId: new FormControl("", []),
      endDate: new FormControl("", []),
      commentStatus: new FormControl(this.disabledComments),
      map: new FormControl("", []),
    });
    this.getRegion();
    this.getRegDepPro();
  }


  private addPollGroup(): FormGroup {
    return this._fb.group({
      value: ["", Validators.required],
      file: [null]
    });
  }

  minLengthArray(min: number) {
    return (c: AbstractControl): { [key: string]: any } => {
      if (c.value.length >= min) {
        return { minLengthArray: { valid: true } };
      }
      return { minLengthArray: { valid: false } };
    };
  }

  showPreviewImage(event: any, index: number): void {
    const file = event.target.files[0];

    const reader = new FileReader();
    this.previewImages[index] = URL.createObjectURL(file);
    reader.onload = (e: any) => {
      this.previewImages[index] = e.target.result as string;
    };
    reader.readAsDataURL(file);

  }

  onFileSelected(event: any, index: number) {
    const file = event.target.files[0];
    if (file.size <= this.MaxSize) {
      console.log(file);
      (this.pollForm?.get('options') as FormArray).at(index).get('file')?.setValue(file);
      //  console.log((this.pollForm?.get('options') as FormArray).at(index).get('file'))
    }
    else {
      console.log("File Size is too long")
    }
  }




  //Add Fields
  addOption(): void {
    this.optionArray.push(this.addPollGroup());
  }

  //Remove Fields
  removeOption(index: number): void {
    this.optionArray.removeAt(index);
  }
  //Fields Array
  get optionArray(): FormArray {
    return <FormArray>this.pollForm.get("options");
  }


  pollMsgClose() {
    this.pollCreatemsg = false;
  }

  pollDraftClose() {
    this.pollDraftmsg = false;
  }

  disabledComments = false;

  toggleDisabledComments() {
    this.disabledComments = true;
  }

  performPollInsert(data: any) {
    if (this.pollForm?.valid) {

      var str = data.map

      var splitter = str.split("+");

      this.pollQue.pollQuestion = this.pollForm.value.pollQuestion;
      this.pollQue.endDate = this.pollForm.value.endDate;
      this.pollQue.visibility = "Open";
      this.pollQue.status = true;
      this.pollQue.id = splitter[0];
      this.pollQue.values = splitter[1];
      this.pollQue.userId = parseInt(sessionStorage.getItem("userId") || "");

      const items = this.pollForm.value.options;

      items.forEach((item: { file: any; value: any; }) => {
        const { file, value } = item;

        this.pollImageList.push(this.pollOpt.picture = file);
        this.pollValueList.push(this.pollOpt.value = value);

       

      });
      console.log(this.pollValueList);
      console.log(this.pollImageList);

      this.PollService.insertPoll(this.pollQue.pollQuestion, this.pollQue.endDate,this.pollQue.status, this.pollQue.visibility, this.pollQue.id, this.pollQue.values,this.pollQue.userId, this.pollImageList, this.pollValueList)
        .subscribe(() => {
          console.log('Poll inserted successfully.');


        }, (error: any) => {
          console.error('Error inserting poll:', error);

          this.pollForm.reset();
          this.pollForm.get('value') as FormArray
          this.previewImages = '';
        });
    } else {
      console.error('Please fill in all required fields.');
    }

  }


  //Without Image (Previous Method we use for save)
  createPoll(data: any) {

    var str = data.map

    var splitter = str.split("+");

    this.pl.pollQuestion = data.pollQuestion;
    this.pl.options = data.options;
    this.pl.status = true;
    this.pl.endDate = data.endDate;
    this.pl.id = splitter[0];
    this.pl.value = splitter[1];
    this.pl.user = sessionStorage.getItem("userId") || "";

    this.result = this.PollService.addPoll(this.pl).subscribe((res: any) => {
      this.pollCreate = false;
      this.pollForm.reset();
      this.pollCreatemsg = true;
    });

  }

  createPollPreference(data: any) {

    var str = data.map

    var splitter = str.split("+");
    this.pl.pollQuestion = data.pollQuestion;
    this.pl.options = data.options;
    this.pl.status = true;
    this.pl.endDate = data.endDate;

    this.pl.id = splitter[0];
    this.pl.value = splitter[1];
    this.pl.user = sessionStorage.getItem("userId") || "";
    this.result = this.PollService.addPoll(this.pl).subscribe((res: any) => {
      this.pollCreate = false;
      this.pollForm.reset();
      this.pollCreatemsg = true;
    });
  }

  draft(data: any) {
    var str = data.map

    var splitter = str.split("+");
    this.pl.pollQuestion = data.pollQuestion;
    this.pl.options = data.options;
    this.pl.status = false;
    this.pl.endDate = data.endDate;
    // this.pl.region = data.regionId;
    // this.pl.department = data.departmentId;
    // this.pl.project = data.projectId;
    this.pl.region = data.regionId;
    this.pl.department = data.departmentId;
    this.pl.project = data.projectId;

    this.pl.id = splitter[0];
    this.pl.value = splitter[1];
    this.pl.user = sessionStorage.getItem("userId") || "";
    this.result = this.PollService.addPoll(this.pl).subscribe((res: any) => {
      this.pollCreate = false;
      this.pollForm.reset();
      this.pollDraftmsg = true;
    });
  }

  getRegion() {
    this.PollService.getAllRegions().subscribe(
      (regions) => (this.RegionList = regions)
    );
  }
  getDepartment(data: any) {
    this.region.regionId = data.regionId;

    this.PollService.getAllDepartments(this.region).subscribe(
      (departments) => (this.DeptList = departments)
    );
  }
  getProject(data: any) {
    this.department.departmentId = data.departmentId;

    this.PollService.getAllProjects(this.department).subscribe(
      (projects) => (this.ProjectList = projects)
    );
  }

  validDate(data: any) {
    this.compareDate = data.endDate;
    this.startDate = this.datePipe.transform(new Date(), "yyyy-MM-dd ");
    if (this.compareDate < this.startDate) {
      this.msg = "Poll End date is invalid";
    }
  }

  getRegDepPro() {
    this.result = sessionStorage.getItem("userId") || "";
    this.pl.user = this.result;

    console.log(this.pl.user);
    this.PollService.getRegDepPro(this.pl).subscribe(all => {
      this.mapList = all;
      console.log(this.mapList);
    })
  }
}
