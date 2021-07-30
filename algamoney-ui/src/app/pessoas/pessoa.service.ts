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

  searchAll(): Promise<any>{
    const headers = new HttpHeaders()
      .append('Authorization', 'Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg==');

      return this.http.get(`${this.peopleUrl}`,{ headers })
      .toPromise()
      .then((response : any) => response['content']);

  }
}
