import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { from, Observable } from 'rxjs';
import { catchError, mergeMap, tap } from 'rxjs/operators';

import { AuthService } from './auth.service';
import { LoadingService } from './../core/loading/loading.service';

export class NotAuthenticatedError {}


@Injectable({
  providedIn: 'root'
})
export class MoneyHttpInterceptor implements HttpInterceptor {

  constructor(
    private authService: AuthService,
    private loadingService : LoadingService
    ) { }

  intercept(req: HttpRequest<any>,  next: HttpHandler): Observable<HttpEvent<any>>{

    this.loadingService.show(true);

    if (!req.url.includes('/oauth/token')) {
      if (this.authService.isAccessTokenInvalid()) {
        return from(this.authService.getNewAccessToken())
        .pipe(
          mergeMap(() => {
            if(this.authService.isAccessTokenInvalid()){
              throw new NotAuthenticatedError();
            }

            req = req.clone({
              setHeaders: {
                Authorization: `Bearer ${localStorage.getItem('token')}`
              }
            });

            return this.handleNextReq(req,next);
          })
        );
      }
      req = req.clone({
        setHeaders: {
          Authorization: `Bearer ${localStorage.getItem('token')}`
        }
      });

    }

    return this.handleNextReq(req,next);
  }

  private handleNextReq(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    return next.handle(req).pipe(
      tap(evt => {
        if (evt instanceof HttpResponse) {
          // alguma ação global quando ocorre sucesso
          this.loadingService.show(false);
        }
      }),
      catchError(err => {
        if (err instanceof HttpErrorResponse) {
          // algum tratamento global quando ocorre erro
          this.loadingService.show(false);
        }
        throw err;
      })
    );

  }


}


