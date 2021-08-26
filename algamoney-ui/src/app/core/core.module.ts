import { Title } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { NgModule, LOCALE_ID } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ToastModule } from 'primeng/toast';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import {MessageService} from 'primeng/api';
import {ConfirmationService} from 'primeng/api';
import { JwtHelperService } from '@auth0/angular-jwt';

import { NavbarComponent } from './navbar/navbar.component';
import { ErrorHandlerService } from './error-handler.service';
import { LancamentoService } from '../lancamentos/lancamento.service';
import { PessoaService } from '../pessoas/pessoa.service';
import { PaginaNaoEncontradaComponent } from './pagina-nao-encontrada.component';
import { AuthService } from '../seguranca/auth.service';
import { NaoAutorizadoComponent } from './nao-autorizado.component';




@NgModule({
  declarations: [NavbarComponent, PaginaNaoEncontradaComponent, NaoAutorizadoComponent],
  imports: [
    CommonModule,
    RouterModule,

    ToastModule,
    ConfirmDialogModule,
  ],
  exports:[
    NavbarComponent,
    ToastModule,
    ConfirmDialogModule
  ],
  providers:[
    { provide: LOCALE_ID, useValue: 'pt-BR' },

    MessageService,
    ConfirmationService,
    Title,
    JwtHelperService,

    LancamentoService,
    PessoaService,
    ErrorHandlerService,
    AuthService

  ]
})
export class CoreModule { }
