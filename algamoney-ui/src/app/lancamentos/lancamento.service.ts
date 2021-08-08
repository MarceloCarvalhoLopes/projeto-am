import { HttpClient, HttpHeaders, HttpParams  } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { format } from 'date-fns';

export class LauchingFilter{
  description : string = '';
  dueDateOf?: Date ;
  dueDateBy?:  Date ;
  page = 0;
  itemsPerPage = 5;
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

      params = params.set('page', filter.page.toString());
      params = params.set('size', filter.itemsPerPage.toString());

      if (filter.description){
        params = params.set('description', filter.description);
      }

      if (filter.dueDateOf){
        params = params.set('dueDateOf', format(filter.dueDateOf,'yyyy-MM-dd'));
      }

      if (filter.dueDateBy){
        params = params.set('dueDateBy', format(filter.dueDateBy,'yyyy-MM-dd'));
      }

      return this.http.get(`${this.launchingsURL}?resume`, { headers, params })
        .toPromise()
        .then((response : any)  => {
           const launchings = response['content']
           //console.log (launchings);

           const result = {
            launchings,
            total: response['totalElements']
           };

           return result;
          });

        //.then((response: any) =>
        //  console.log( response['content']));
  }

  delete(id: number): Promise<void>{
    const headers = new HttpHeaders()
    .append('Authorization', 'Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg==');

    return this.http.delete(`${this.launchingsURL}/${id}`, { headers })
      .toPromise()
      .then(() => null);
  }



}
