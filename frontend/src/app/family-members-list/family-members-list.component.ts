import {Component, OnInit} from '@angular/core';
import {Page} from '../entities/page';
import {FamilyMemberService} from '../services/family-member.service';
import {ActivatedRoute} from '@angular/router';
import {AuthService} from '../services/auth.service';

@Component({
  selector: 'app-family-members-list',
  templateUrl: './family-members-list.component.html',
  styleUrls: ['./family-members-list.component.css']
})
export class FamilyMembersListComponent implements OnInit {

  page: Page;
  minAge: number;
  maxAge: number;
  sort: string;

  constructor(private familyMemberService: FamilyMemberService,
              public authService: AuthService,
              private route: ActivatedRoute) {
    this.minAge = 0;
    this.maxAge = 2020;
    this.sort = 'default';
  }

  ngOnInit(): void {
    this.search();
  }

  search(): void {
    this.familyMemberService.getPage(this.minAge, this.maxAge, 0, this.sort).subscribe(
      response => {
        this.page = response;
      }, error => {
        console.log(error);
      }
    );
  }

  resetAge(): void {
    this.minAge = 0;
    this.maxAge = 2020;
    this.search();
  }

  delete(index: number, id: number): void {
    this.familyMemberService.deleteMember(id).subscribe(
      response => {
        this.page.members.splice(index, 1);
      }, error => {
        console.log(error);
      }
    );
  }

  prevPage(): void {
    this.route.params.subscribe(
      params => {
        this.familyMemberService.getPage(this.minAge, this.maxAge, this.page.current_page - 1, this.sort).subscribe(
          response => {
            console.log(response);
            this.page = response;
          }, error => {
            console.log(error);
          }
        );
      });
  }

  nextPage(): void {
    this.route.params.subscribe(
      params => {
        this.familyMemberService.getPage(this.minAge, this.maxAge, this.page.current_page + 1, this.sort).subscribe(
          response => {
            console.log(response);
            this.page = response;
          }, error => {
            console.log(error);
          }
        );
      });
  }

}
