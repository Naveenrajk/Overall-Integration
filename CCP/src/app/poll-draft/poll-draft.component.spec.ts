import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PollDraftComponent } from './poll-draft.component';

describe('DraftComponent', () => {
  let component: PollDraftComponent;
  let fixture: ComponentFixture<PollDraftComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PollDraftComponent]
    });
    fixture = TestBed.createComponent(PollDraftComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
