import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FamilyMembersListComponent } from './family-members-list.component';

describe('FamilyMembersListComponent', () => {
  let component: FamilyMembersListComponent;
  let fixture: ComponentFixture<FamilyMembersListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FamilyMembersListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FamilyMembersListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
