import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { People } from './../core/model';

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

    let params =  new HttpParams();

    params = params.set('page', filter.page.toString());
    params = params.set('size', filter.itemsPerPage.toString());

    if(filter.name){
      params = params.set('name', filter.name);
    }

    return this.http.get(`${this.peopleUrl}`,{ params })
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
    return this.http.get(`${this.peopleUrl}`)
      .toPromise()
      .then((response : any) => response['content']);

  }

  create(people:People): Promise<People>{
    return this.http.post<People>( this.peopleUrl, people )
    .toPromise();

  }

  delete(id: number): Promise<any>{
    return this.http.delete(`${this.peopleUrl}/${id}`)
      .toPromise()
      .then(() => null);

  }

  changeStatus(id: number, active: boolean): Promise<void>{
    return this.http.put(`${this.peopleUrl}/${id}/active`, active)
      .toPromise()
      .then(() => null);

  }

  update(people : People): Promise<People> {
    return this.http.put<People>(`${this.peopleUrl}/${people.id}`,people)
      .toPromise();
  }

  findById(id : number) : Promise<People>{
    return this.http.get<People>(`${this.peopleUrl}/${id}`)
      .toPromise();
  }


}
