import { Component, ElementRef, Renderer2 } from "@angular/core";
import { FormBuilder, FormGroup, FormControl } from "@angular/forms";
import { Department } from "../model/Department";
import { User } from "../model/User";
import { PollService } from "../Services/poll.service";

@Component({
  selector: "app-employee",
  templateUrl: "./employee.component.html",
  styleUrls: ["./employee.component.css"],
})
export class EmployeeComponent {
  [x: string]: any;

  Employee = true;
  user: User[] = [];

  department?: Department;
  users: User[] = [];
  searchName: string = "";

  isView = true;
  isViewEmployee = true;

  constructor(
    private _fb: FormBuilder,
    private Polling: PollService,
    private elementRef: ElementRef,
    private renderer: Renderer2
  ) {}

  form = new FormGroup({
    userFirstName: new FormControl(),
  });

  ngOnInit(): void {
    this.getUser();
  }
  ngAfterViewInit() {
    const navItems =
      this.elementRef.nativeElement.querySelectorAll(".nav-pills li");

    navItems.forEach((item: any) => {
      item.addEventListener("click", () => {
        navItems.forEach((navItem: any) => {
          this.renderer.removeClass(navItem, "active");
        });
        this.renderer.addClass(item, "active");
      });
    });
  }

  searchUsers(): void {
    if (this.searchName.trim() !== "") {
      this.Polling.getUsersByFirstName(this.searchName).subscribe((users) => {
        this.user = users;
      });
      this.searchName = "";
    }
  }

  toggleDisplayEmployee() {
    this.Employee = !this.Employee;
  }

  toggleViewEmployee() {
    this.isViewEmployee = !this.isViewEmployee;
    this.isView = !this.isView;
  }
  private getUser() {
    this.Polling.viewAllUserDetails().subscribe((data) => {
      this.user = data;
      this.user = data;
    });
  }

  searchForm(_searchInfo: any) {
    this.getUser = this["userFirstName"].value;
  }
  getData() {
    return this["form"].get("userFirstName");
  }
}
