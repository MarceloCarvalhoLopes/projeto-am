import { HttpClient, HttpHeaders, HttpParams  } from '@angular/common/http';
import { Injectable } from '@angular/core';


export interface LauchingFilter{
  description : string ;
}

@Injectable({
  providedIn: 'root'
})
export class LancamentoService {

  launchingsURL = 'http://localhost:8080/financial'

  constructor(private http: HttpClient) { }

  search(filter: LauchingFilter): Promise<any>{
    const headers = new HttpHeaders()
      .append('Authorization', 'Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg==');

      let params = new HttpParams();

      if (filter.description){
        params = params.set('description', filter.description);
      }

      return this.http.get(`${this.launchingsURL}?resume`, { headers, params })
        .toPromise()
        .then((response: any) => response['content']);

        //.then((response: any) =>
        //  console.log( response['content']));
  }


}
