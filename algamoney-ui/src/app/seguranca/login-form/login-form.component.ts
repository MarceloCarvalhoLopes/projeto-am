import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';

import { ErrorHandlerService } from 'src/app/core/error-handler.service';
import { AuthService } from './../auth.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit  {

  constructor(
    private auth: AuthService,
    private errorHandlerService: ErrorHandlerService,
    private router: Router,
    private title: Title
    ) { }

  ngOnInit(): void {
      this.title.setTitle('Login');
  }

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
