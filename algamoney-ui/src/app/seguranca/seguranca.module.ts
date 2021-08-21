import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { JwtHelperService, JwtModule } from '@auth0/angular-jwt';


import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';


import { LoginFormComponent } from './login-form/login-form.component';
import { SegurancaRoutingModule } from './seguranca-routing.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,

    InputTextModule,
    ButtonModule,

    JwtModule.forRoot({
      config:{
        tokenGetter: () =>{
          return '';
        }
      }
    }),

    SegurancaRoutingModule
  ],
  declarations: [LoginFormComponent],
  providers:[JwtHelperService]
})
export class SegurancaModule { }
