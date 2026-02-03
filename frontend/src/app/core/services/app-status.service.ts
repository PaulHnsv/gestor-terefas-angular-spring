import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AppStatusService {

  private readonly API = environment.apiUrl;

  constructor(private http: HttpClient) {}

  getPing(): Observable<any> {
    return this.http.get(`${this.API}/api/ping`);
  }

  getVersion(): Observable<any> {
    return this.http.get(`${this.API}/api/version`);
  }
}
