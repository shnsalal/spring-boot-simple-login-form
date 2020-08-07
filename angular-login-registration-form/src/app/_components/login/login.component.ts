import { Component, OnInit } from '@angular/core';
import { AccountService } from 'src/app/_services/account.service';
import { User } from 'src/app/_models/User';
import { Router } from '@angular/router';
import { AlertifyService } from 'src/app/_services/alertify.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user: User;
  loginForm: FormGroup;

  constructor(
    private accountService: AccountService,
    private router: Router,
    private alertifyService: AlertifyService,
    private fb: FormBuilder,
  ) { }

  ngOnInit() {
    this.createLoginForm();
  }

  createLoginForm() {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  login() {
    this.user = Object.assign({}, this.loginForm.value);
    this.accountService.login(this.user).subscribe(res => {
      this.alertifyService.success('Login  successful');
      console.log(res);
    },
    error => {
      this.loginForm.reset();
      this.alertifyService.error('Invalid username and password!');
    },
    () => {
      this.router.navigate(['/welcome']);
    });
  }

}
