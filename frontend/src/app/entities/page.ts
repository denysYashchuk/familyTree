import {FamilyMember} from './family-member';

export class Page {
  members: Array<FamilyMember>;
  current_page: number;
  total_pages: number;
}
