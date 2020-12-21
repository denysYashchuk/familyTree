import {Component, OnInit} from '@angular/core';
import {AuthService} from '../services/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginError = false;

  constructor(private authService: AuthService,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  login(username: string, password: string): void {
    this.authService.authenticate(username, password);
    if (this.authService.isAuthenticated()) {
      this.router.navigate(['']);
    } else {
      this.loginError = true;
    }
  }

}
