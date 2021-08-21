import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  categoriesUrl = 'http://localhost:8080/categories';


  constructor(private http: HttpClient) { }

  listAll(): Promise<any>{
    return this.http.get(`${this.categoriesUrl}`)
      .toPromise();
      //.then((response : any) => response['content']);


  }

}
