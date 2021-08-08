import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';


export class PeopleFilter{
  name : string = '';
  page = 0;
  itemsPerPage = 5;
}

@Injectable({
  providedIn: 'root'
})
export class PessoaService {

  peopleUrl = 'http://localhost:8080/people'

  constructor( private http: HttpClient) { }

  search(filter: PeopleFilter): Promise<any>  {
    const headers = new HttpHeaders()
      .append('Authorization', 'Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg==');

    let params =  new HttpParams();

    params = params.set('page', filter.page.toString());
    params = params.set('size', filter.itemsPerPage.toString());

    if(filter.name){
      params = params.set('name', filter.name);
    }

    return this.http.get(`${this.peopleUrl}`,{headers,params})
      .toPromise()
      .then((response : any) =>{
        const people = response['content']

        const result = {
          people,
          total: response['totalElements']
        }
        return result;
      });

  }

  listAll(): Promise<any>{
    const headers = new HttpHeaders()
      .append('Authorization', 'Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg==');

      return this.http.get(`${this.peopleUrl}`,{ headers })
      .toPromise()
      .then((response : any) => response['content']);

  }

  delete(id: number): Promise<any>{
    const headers = new HttpHeaders()
    .append('Authorization', 'Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg==');

    return this.http.delete(`${this.peopleUrl}/${id}`, { headers })
      .toPromise()
      .then(() => null);

  }

  changeStatus(id: number, active: boolean): Promise<void>{
    const headers = new HttpHeaders()
    .append('Authorization', 'Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg==')
    .append('Content-Type', 'application/json');

    return this.http.put(`${this.peopleUrl}/${id}/active`, active, { headers })
      .toPromise()
      .then(() => null);

  }

}
