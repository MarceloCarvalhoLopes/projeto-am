import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoadingService {

  showProgressSpiner = false;

  show(status : boolean){
    this.showProgressSpiner = status;
  }

}
