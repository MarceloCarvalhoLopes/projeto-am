import { AuthService } from './auth.service';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(
    private authService : AuthService,
    private router : Router
  ){}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {


    if(this.authService.isAccessTokenInvalid()){
      console.log('Navegação com access token invalido, obtendo novo token');

      return this.authService.getNewAccessToken()
        .then(() => {
          if(this.authService.isAccessTokenInvalid()){
            this.router.navigate(['/login']);
            return false;
          }
          return true;
        });
    }else if (next.data.roles && !this.authService.haveAnyPermission(next.data.roles) ) {
      this.router.navigate(['/nao-autorizado'])
      return false;
    }

    return true;
  }
}
