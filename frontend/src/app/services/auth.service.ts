import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AppSettings} from '../app-settings';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public username: string;
  public password: string;

  constructor(private client: HttpClient,
              private router: Router) {
  }

  authenticate(username: string, password: string): void {
    const headers = new HttpHeaders().append('Authorization', this.generateBasicAuthToken(username, password));
    this.client.get(AppSettings.BASE_API_ENDPOINT + '/auth', {headers}).subscribe(
      response => {
        this.username = username;
        this.password = password;
        this.router.navigate(['']);
      }, error => {
        console.log(error);
      }
    );
  }

  logout(): void {
    this.username = null;
    this.password = null;
  }

  generateBasicAuthToken(username: string, password: string): string {
    return 'Basic ' + window.btoa(username + ':' + password);
  }

  getToken(): string {
    return this.generateBasicAuthToken(this.username, this.password);
  }

  isAuthenticated(): boolean {
    if (this.username && this.password) {
      return true;
    } else {
      return false;
    }
  }

}
