import {ParentChild} from './parent-child';

export class FamilyMember {
  id: number;
  name: string;
  sex: string;
  birth_year: number;
  parents: Array<ParentChild>;
  children: Array<ParentChild>;

 constructor() {
   this.id = 0;
   this.name = '';
   this.sex = '';
   this.birth_year = 0;
   this.parents = new Array<ParentChild>();
   this.children = new Array<ParentChild>();
 }
}
