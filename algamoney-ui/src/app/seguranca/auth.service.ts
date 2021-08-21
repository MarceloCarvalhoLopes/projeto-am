import { JwtHelperService } from '@auth0/angular-jwt';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  oauthTokenUrl = 'http://localhost:8080/oauth/token';
  jwtPayload: any;

  constructor(
    private http: HttpClient,
    private jwtHelperService :JwtHelperService
    ) {
      this.loadToken();
    }

  login(user: string, password: string) : Promise<void>{
    const headers = new HttpHeaders()
    .append('Content-Type', 'application/x-www-form-urlencoded')
    .append('Authorization', 'Basic YW5ndWxhcjpAbmd1bEByMA==');

    const body = `username=${user}&password=${password}&grant_type=password`;

    return this.http.post(this.oauthTokenUrl,body, { headers })
     .toPromise()
     .then((response : any) => {
        //console.log(response);
        this.storeToken(response['access_token']);
      })
      .catch((response : any) =>{
        if (response.status === 400) {
           if (response.error.error === 'invalid_grant')
           {
              return Promise.reject('Usuário ou senha inválida');
           }
        }
        return Promise.reject(response);
        //console.log(response);
      });

  }

  private storeToken(token : string){
    this.jwtPayload = this.jwtHelperService.decodeToken(token);
    localStorage.setItem('token', token);
  }

  private loadToken(){
    const token = localStorage.getItem('token');
    if (token){
      this.storeToken(token);
    }
  }

}
