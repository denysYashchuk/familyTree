import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FamilyMembersEditComponent } from './family-members-edit.component';

describe('FamilyMembersEditComponent', () => {
  let component: FamilyMembersEditComponent;
  let fixture: ComponentFixture<FamilyMembersEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FamilyMembersEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FamilyMembersEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
