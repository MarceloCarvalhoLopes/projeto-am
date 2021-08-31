import { environment } from './../../environments/environment';
import { HttpClient, HttpHeaders, HttpParams  } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { format, parse } from 'date-fns';
import { Launching } from './../core/model';

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

  launchingsURL : string;

  constructor(private http: HttpClient) {
    this.launchingsURL = `${environment.apiUrl}/financial`;
  }

  search(filter: LauchingFilter): Promise<any>{

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

      return this.http.get(`${this.launchingsURL}?resume`, { params })
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
    return this.http.delete(`${this.launchingsURL}/${id}`)
      .toPromise()
      .then(() => null);
  }

  create(launching:Launching): Promise<Launching> {
    return this.http.post<Launching>(this.launchingsURL,launching)
    .toPromise();
  }

  update(launching:Launching): Promise<Launching> {
    return this.http.put<Launching>(`${this.launchingsURL}/${launching.id}`,launching)
      .toPromise()
      .then( response => {
        const updatedLaunching = response;

        this.convertStringsToDate([updatedLaunching]);

        return updatedLaunching;
    });
  }

  findById(id: number): Promise<Launching>{
    return this.http.get<Launching>(`${this.launchingsURL}/${id}`)
      .toPromise()
      .then(response => {
        const lauching = response;
        this.convertStringsToDate([lauching]);

        return lauching;
      });

  }

  private convertStringsToDate(launchings :Launching[]){
    for( const launching of launchings){
      launching.dueDate = parse(launching.dueDate.toString(),'yyyy-MM-dd', new Date());
      launching.paymentDate = launching.paymentDate ? parse(launching.paymentDate.toString(),'yyyy-MM-dd', new Date()) : null;

    }
  }
}

