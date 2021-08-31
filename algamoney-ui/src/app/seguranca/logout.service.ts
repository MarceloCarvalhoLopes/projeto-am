import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class LogoutService {

  tokensRevokeUrl : string;

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {
    this.tokensRevokeUrl = `${environment.apiUrl}/tokens/revoke`;
  }

  logout(){
    return this.http.delete( this.tokensRevokeUrl, { withCredentials: true })
    .toPromise()
    .then(() => {
      this.authService.cleanAccessToken();
    })
  }
}
