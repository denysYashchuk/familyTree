import {Component, OnInit} from '@angular/core';
import {FamilyMemberService} from '../services/family-member.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FamilyMember} from '../entities/family-member';
import {ParentChild} from '../entities/parent-child';
import {UploadFileService} from '../services/upload-file.service';
import {HttpEventType, HttpResponse} from '@angular/common/http';
import {AppSettings} from '../app-settings';
import {File} from '../entities/file';

@Component({
  selector: 'app-family-members-edit',
  templateUrl: './family-members-edit.component.html',
  styleUrls: ['./family-members-edit.component.css']
})
export class FamilyMembersEditComponent implements OnInit {

  member: FamilyMember;
  manyParents: boolean;
  manyFiles: boolean;
  selectedFiles: FileList;
  progressInfos = [];
  baseImageUrl = AppSettings.BASE_API_ENDPOINT + '/files/';

  constructor(private familyMemberService: FamilyMemberService,
              private uploadFileService: UploadFileService,
              private router: Router,
              private route: ActivatedRoute) {
    this.manyParents = false;
    this.manyFiles = false;
  }

  ngOnInit(): void {
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
    this.member.birth_year = String(this.member.birth_year);
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

  selectFiles(event): void {
    this.progressInfos = [];
    this.selectedFiles = event.target.files;
  }

  uploadFiles(): void {
    if (this.selectedFiles.length + this.member.files.length > 10) {
      this.manyFiles = true;
    } else {
      for (let i = 0; i < this.selectedFiles.length; i++) {
        this.upload(i, this.selectedFiles[i]);
      }
    }
  }

  upload(idx, file): void {
    this.progressInfos[idx] = {value: 0, fileName: file.name};

    this.uploadFileService.upload(file, this.member.id).subscribe(
      event => {
        console.log(event);
        if (event.type === HttpEventType.UploadProgress) {
          this.progressInfos[idx].value = Math.round(100 * event.loaded / event.total);
        } else if (event instanceof HttpResponse) {
          if (!this.member.files) {
            this.member.files = new Array<File>();
          }
          console.log(event.body);
          this.member.files.push(event.body);
        }
      },
      err => {
        this.progressInfos[idx].value = 0;
      });
  }

}
