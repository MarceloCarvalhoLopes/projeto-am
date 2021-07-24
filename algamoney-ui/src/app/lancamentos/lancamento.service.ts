import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class LancamentoService {

  launchingsURL = 'http://localhost:8080/financial'

  constructor(private http: HttpClient) { }

  search(): Promise<any>{
    const headers = new HttpHeaders()
    .append('Authorization', 'Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg==');

      return this.http.get(`${this.launchingsURL}?resume`, { headers })
        .toPromise()
        .then((response: any) => response['content']);

        //.then((response: any) =>
        //  console.log( response['content']));
  }


}
