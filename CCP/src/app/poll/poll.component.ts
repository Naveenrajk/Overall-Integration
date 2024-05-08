import { Component, ElementRef, Renderer2 } from '@angular/core';

@Component({
  selector: 'app-poll',
  templateUrl: './poll.component.html',
  styleUrls: ['./poll.component.css']
})
export class PollComponent {
  constructor (private elementRef: ElementRef, private renderer: Renderer2) {
   
  }
 
  // ngAfterViewInit() {
  //   const navItems = this.elementRef.nativeElement.querSelectorAll('.nav-pills li');
 
  //   navItems.forEach((item: any) => {
  //     item.addEventListener('click', () => {
  //       navItems.forEach((navItem: any) => {
  //         this.renderer.removeClass(navItem, 'active');
  //       });
  //       this.renderer.addClass(item, 'active');
  //     });
  //   });
  // }

  activeLiIndex: number | null = null;
 
  setActiveLi(index: number) {
    this.activeLiIndex = index;
  }
 
  // create: boolean = false;
  // view:boolean = false;
  // draft:boolean = false;
  // employee: boolean = false;
  // allPoll: boolean = false;
  // appView: boolean = true;
 


  // callCreate() {
  //   this.appView = false;
  //   this.create = true;
  //   this.draft = false;
  //   this.view = false;
  //   this.employee = false;
  //   this.allPoll = false;
  // }
  // callView() {
  //   this.appView = false;
  //   this.create = false;
  //   this.draft = false;
  //   this.view = true;
  //   this.employee = false;
  //   this.allPoll = false;
  // }
  // callDraft() {
  //   this.appView = false;
  //   this.create = false;
  //   this.draft = true;
  //   this.view = false;
  //   this.employee = false;
  //   this.allPoll = false;
  // }
  // callEmployee() {
  //   this.appView = false;
  //   this.create = false;
  //   this.draft = false;
  //   this.view = false;
  //   this.employee = true;
  //   this.allPoll = false;
  // }

  // callAllPoll(){
  //   this.appView = false;
  //   this.create = false;
  //   this.draft = false;
  //   this.view = false;
  //   this.employee = false;
  //   this.allPoll = true;
  // }
}
