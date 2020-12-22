import {ParentChild} from './parent-child';
import {File} from './file';

export class FamilyMember {
  id: number;
  name: string;
  sex: string;
  birth_year: string;
  parents: Array<ParentChild>;
  children: Array<ParentChild>;
  files: Array<File>;

  constructor() {
    this.id = 0;
    this.name = '';
    this.sex = '';
    this.birth_year = '';
    this.parents = new Array<ParentChild>();
    this.children = new Array<ParentChild>();
    this.files = new Array<File>();
  }
}
