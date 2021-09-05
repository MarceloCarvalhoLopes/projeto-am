import { LoadingService } from './loading/loading.service';
import { Title } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { NgModule, LOCALE_ID } from '@angular/core';
import { CommonModule } from '@angular/common';
import { JwtHelperService } from '@auth0/angular-jwt';

import { ToastModule } from 'primeng/toast';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import {MessageService} from 'primeng/api';
import {ConfirmationService} from 'primeng/api';
import {ProgressSpinnerModule} from 'primeng/progressspinner';

import { NavbarComponent } from './navbar/navbar.component';
import { ErrorHandlerService } from './error-handler.service';
import { LancamentoService } from '../lancamentos/lancamento.service';
import { PessoaService } from '../pessoas/pessoa.service';
import { PaginaNaoEncontradaComponent } from './pagina-nao-encontrada.component';
import { AuthService } from '../seguranca/auth.service';
import { NaoAutorizadoComponent } from './nao-autorizado.component';
import { LoadingComponent } from './loading/loading.component';




@NgModule({
  declarations: [
    NavbarComponent,
    PaginaNaoEncontradaComponent,
    NaoAutorizadoComponent,
    LoadingComponent],
  imports: [
    CommonModule,
    RouterModule,

    ToastModule,
    ConfirmDialogModule,
    ProgressSpinnerModule
  ],
  exports:[
    NavbarComponent,
    ToastModule,
    ConfirmDialogModule,
    LoadingComponent
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
    AuthService,
    LoadingService

  ]
})
export class CoreModule { }
