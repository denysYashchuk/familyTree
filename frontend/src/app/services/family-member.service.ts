import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Page} from '../entities/page';
import {AppSettings} from '../app-settings';
import {FamilyMember} from '../entities/family-member';
import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FamilyMemberService {

  private url = AppSettings.BASE_API_ENDPOINT + '/family-members';

  constructor(private client: HttpClient) {
  }

  getMember(id: number): Observable<FamilyMember> {
    return this.client.get<FamilyMember>(this.url + '/' + id);
  }

  getPage(minAge: number, maxAge: number, page: number, sort: string): Observable<Page> {
    const params = new HttpParams().set('start', String(minAge))
      .append('end', String(maxAge))
      .append('page', String(page))
      .append('size', '10')
      .append('sort', sort === 'sex' ? 'sex,desc' : (sort === 'age' ? 'birthYear,asc' : 'id,asc'));

    return this.client.get<Page>(this.url, {params});
  }

  updateMember(id: number, member: FamilyMember): Observable<FamilyMember> {
    return this.client.put<FamilyMember>(this.url + '/' + id, member);
  }

  addMember(member: FamilyMember): Observable<FamilyMember> {
    return this.client.post<FamilyMember>(this.url + '/', member);
  }

  deleteMember(id: number): Observable<any> {
    return this.client.delete(this.url + '/' + id);
  }

}
