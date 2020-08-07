import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../_models/User';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  baseUrl = 'http://localhost:8880/api/';

  constructor(private http: HttpClient) { }

  login(user: User) {
    return this.http.post(this.baseUrl + 'login/', user);
  }

  register(user: User) {
    return this.http.post(this.baseUrl + 'register/', user);
  }

}
