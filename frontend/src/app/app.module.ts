import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {FamilyMemberComponent} from './family-member/family-member.component';
import {RouterModule} from '@angular/router';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {FamilyMemberService} from './services/family-member.service';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FamilyMembersListComponent} from './family-members-list/family-members-list.component';
import {FamilyMembersEditComponent} from './family-members-edit/family-members-edit.component';
import {FormsModule} from '@angular/forms';
import {LoginComponent} from './login/login.component';
import {AuthInterceptor} from './services/auth.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    FamilyMemberComponent,
    PageNotFoundComponent,
    FamilyMembersListComponent,
    FamilyMembersEditComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot([
      {path: 'login', component: LoginComponent},
      {path: 'family-members', component: FamilyMembersListComponent},
      {path: 'family-members/:memberId', component: FamilyMemberComponent},
      {path: 'family-members/edit/:memberId', component: FamilyMembersEditComponent},
      {path: '', redirectTo: '/family-members', pathMatch: 'full'},
      {path: '**', component: PageNotFoundComponent}
    ]),
    FormsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
