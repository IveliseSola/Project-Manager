import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MyAppService {

  public API = '//localhost:8080';
  public USER_API = this.API + '/users';

  constructor(private http: HttpClient) { }

  save(user: any): Observable<any> {
    let result: Observable<Object>;
      result = this.http.post(this.USER_API + "/sign-up", user);
    return result;
  }
}
