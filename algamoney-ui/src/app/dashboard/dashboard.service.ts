import { HttpClient } from '@angular/common/http';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';

import {  parse } from 'date-fns';


@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  launchingsURL : string;

  constructor( private http: HttpClient) {
    this.launchingsURL = `${environment.apiUrl}/financial`;
  }

  lauchingByCategory() : Promise<Array<any>>{
    return this.http.get<any>(`${this.launchingsURL}/statistic/by-category`)
      .toPromise();
  }

  lauchingByDay() : Promise<Array<any>>{
    return this.http.get<any>(`${this.launchingsURL}/statistic/by-day`)
    .toPromise()
    .then(response => {
      this.convertStringsToDate(response);
      return response;
    })
  }


  private convertStringsToDate(launchings : Array<any>){
    for( const launching of launchings){
      launching.day = parse(launching.day.toString(),'yyyy-MM-dd', new Date());
      //launching.dueDate = parse(launching.dueDate.toString(),'yyyy-MM-dd', new Date());
      //launching.dueDate = parse(launching.dueDate.toString(),'yyyy-MM-dd', new Date());
    }
  }

}
