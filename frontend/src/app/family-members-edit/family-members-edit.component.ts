import {Component, OnInit} from '@angular/core';
import {FamilyMemberService} from '../services/family-member.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FamilyMember} from '../entities/family-member';
import {ParentChild} from '../entities/parent-child';

@Component({
  selector: 'app-family-members-edit',
  templateUrl: './family-members-edit.component.html',
  styleUrls: ['./family-members-edit.component.css']
})
export class FamilyMembersEditComponent implements OnInit {

  member: FamilyMember;
  manyParents: boolean;

  constructor(private familyMemberService: FamilyMemberService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.manyParents = false;
    this.route.params.subscribe(
      params => {
        if (params.memberId === 'new') {
          this.member = new FamilyMember();
        } else {
          this.familyMemberService.getMember(params.memberId).subscribe(
            response => {
              console.log(response);
              this.member = response;
            }, error => {
              if (error.status === 404) {
                this.router.navigate(['404']);
              } else {
                console.log(error);
              }
            }
          );
        }
      }
    );
  }

  addOrSave(): void {
    if (this.member.id === 0) {
      this.familyMemberService.addMember(this.member).subscribe(
        response => {
          this.member = response;
          this.router.navigate(['/family-members/' + this.member.id]);
        }, error => {
          console.log(error);
        }
      );
    } else {
      this.familyMemberService.updateMember(this.member.id, this.member).subscribe(
        response => {
          this.member = response;
          this.router.navigate(['/family-members/' + this.member.id]);
        }, error => {
          console.log(error);
        }
      );
    }
  }

  addChild(id: number): void {
    if (!this.member.children) {
      this.member.children = new Array<ParentChild>();
    }
    this.member.children.push(new ParentChild(id));
  }

  addParent(id: number): void {
    if (!this.member.parents) {
      this.member.parents = new Array<ParentChild>();
    }
    if (this.member.parents.length === 2) {
      this.manyParents = true;
    } else {
      this.member.parents.push(new ParentChild(id));
    }
  }

  deleteChild(index: number): void {
    this.member.parents.splice(index, 1);
  }

  deleteParent(index: number): void {
    this.member.parents.splice(index, 1);
    if (this.manyParents) {
      this.manyParents = false;
    }
  }

}
