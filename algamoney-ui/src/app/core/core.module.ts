import { NgModule, LOCALE_ID } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ToastModule } from 'primeng/toast';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import {MessageService} from 'primeng/api';
import {ConfirmationService} from 'primeng/api';

import { NavbarComponent } from './navbar/navbar.component';
import { ErrorHandlerService } from './error-handler.service';
import { LancamentoService } from '../lancamentos/lancamento.service';
import { PessoaService } from '../pessoas/pessoa.service';



@NgModule({
  declarations: [NavbarComponent],
  imports: [
    CommonModule,

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

    LancamentoService,
    PessoaService,
    ErrorHandlerService

  ]
})
export class CoreModule { }
