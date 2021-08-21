import { Router } from '@angular/router';
import { ErrorHandlerService } from 'src/app/core/error-handler.service';
import { AuthService } from './../auth.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent  {

  constructor(
    private auth: AuthService,
    private errorHandlerService: ErrorHandlerService,
    private router: Router
    ) { }

  login(user: string, password : string){
    this.auth.login(user,password)
    .then(() =>{
      this.router.navigate(['/lancamentos']);
    })
    .catch(erro => {
      this.errorHandlerService.handle(erro);
    })
  }

}
