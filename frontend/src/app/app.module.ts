import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {FamilyMemberComponent} from './family-member/family-member.component';
import {RouterModule} from '@angular/router';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import {FamilyMemberService} from './services/family-member.service';
import {HttpClientModule} from '@angular/common/http';
import { FamilyMembersListComponent } from './family-members-list/family-members-list.component';
import { FamilyMembersEditComponent } from './family-members-edit/family-members-edit.component';
import {FormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    FamilyMemberComponent,
    PageNotFoundComponent,
    FamilyMembersListComponent,
    FamilyMembersEditComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot([
      {path: 'family-members', component: FamilyMembersListComponent},
      {path: 'family-members/:memberId', component: FamilyMemberComponent},
      {path: 'family-members/edit/:memberId', component: FamilyMembersEditComponent},
      {path: '', redirectTo: '/family-members', pathMatch: 'full'},
      {path: '**', component: PageNotFoundComponent}
    ]),
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
