import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class LogoutService {

  tokensRevokeUrl = 'http://localhost:8080/tokens/revoke';

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) { }

  logout(){
    return this.http.delete( this.tokensRevokeUrl, { withCredentials: true })
    .toPromise()
    .then(() => {
      this.authService.cleanAccessToken();
    })
  }
}
