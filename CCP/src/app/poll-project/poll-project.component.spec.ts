import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PollProjectComponent } from './poll-project.component';

describe('PollProjectComponent', () => {
  let component: PollProjectComponent;
  let fixture: ComponentFixture<PollProjectComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PollProjectComponent]
    });
    fixture = TestBed.createComponent(PollProjectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
