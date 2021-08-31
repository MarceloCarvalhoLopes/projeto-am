import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  categoriesUrl : string;


  constructor(private http: HttpClient) {
    this.categoriesUrl = `${environment.apiUrl}/categories`;
  }

  listAll(): Promise<any>{
    return this.http.get(`${this.categoriesUrl}`)
      .toPromise();
      //.then((response : any) => response['content']);


  }

}
