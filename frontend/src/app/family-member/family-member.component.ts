import {Component, OnInit} from '@angular/core';
import {FamilyMember} from '../entities/family-member';
import {FamilyMemberService} from '../services/family-member.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-family-member',
  templateUrl: './family-member.component.html',
  styleUrls: ['./family-member.component.css']
})
export class FamilyMemberComponent implements OnInit {

  member: FamilyMember;

  constructor(private familyMemberService: FamilyMemberService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(
      params => {
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
    );
  }

}
