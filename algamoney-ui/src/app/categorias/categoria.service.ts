import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  categoriesUrl = 'http://localhost:8080/categories';


  constructor(private http: HttpClient) { }

  listAll(): Promise<any>{

    const headers = new HttpHeaders()
    .append('Authorization', 'Basic YWRtaW5AYWxnYW1vbmV5LmNvbTphZG1pbg==');

      return this.http.get(`${this.categoriesUrl}`,{ headers })
      .toPromise();
      //.then((response : any) => response['content']);


  }

}
