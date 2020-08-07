import { Component, OnInit, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AccountService } from 'src/app/_services/account.service';
import { User } from 'src/app/_models/User';
import { EventEmitter } from '@angular/core';
import { AlertifyService } from 'src/app/_services/alertify.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  @Output() cancelRegister  = new EventEmitter();
  registerForm: FormGroup;
  user: User;
  userError: User;
  // backhome = false;
  constructor(
    private fb: FormBuilder,
    private accountService: AccountService,
    private alertifyService: AlertifyService,
    private router: Router,
    ) { }

  ngOnInit() {
    this.createRegisterForm();
  }

  createRegisterForm() {
    this.registerForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  register() {
    this.user = Object.assign({}, this.registerForm.value);
    this.accountService.register(this.user).subscribe(res => {
      this.alertifyService.success('Registration  successful');
      this.registerForm.reset();
      console.log(res);
    },
    error => {
      this.alertifyService.error(error);
    },
    () => {
      this.router.navigate(['/home']);
    });
  }

  cancel() {
    this.cancelRegister.emit(false);
    console.log(this.cancelRegister);
  }

}
