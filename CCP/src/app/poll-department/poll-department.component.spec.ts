import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PollDepartmentComponent } from './poll-department.component';

describe('PollDepartmentComponent', () => {
  let component: PollDepartmentComponent;
  let fixture: ComponentFixture<PollDepartmentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PollDepartmentComponent]
    });
    fixture = TestBed.createComponent(PollDepartmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
