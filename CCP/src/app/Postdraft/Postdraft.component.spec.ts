import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostdraftComponent } from './Postdraft.component';

describe('DraftComponent', () => {
  let component:PostdraftComponent;
  let fixture: ComponentFixture<PostdraftComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PostdraftComponent]
    });
    fixture = TestBed.createComponent(PostdraftComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
