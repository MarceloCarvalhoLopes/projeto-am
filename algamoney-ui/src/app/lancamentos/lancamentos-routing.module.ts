import { AuthGuard } from './../seguranca/auth.guard';
import { Routes, RouterModule, CanActivate } from '@angular/router';
import { NgModule } from '@angular/core';

import { LancamentoCadastroComponent } from './lancamento-cadastro/lancamento-cadastro.component';
import { LancamentosPesquisaComponent } from './lancamentos-pesquisa/lancamentos-pesquisa.component';

const routes: Routes = [
  {
    path: '',
    component: LancamentosPesquisaComponent,
    canActivate : [AuthGuard],
    data : {roles :['ROLE_SEARCH_FINANCIAL_RELEASE']}
  },
  {
    path: 'novo',
    component: LancamentoCadastroComponent,
    canActivate : [AuthGuard],
    data : { roles :['ROLE_REGISTER_FINANCIAL_RELEASE'] }
  },
  {
    path: ':id',
    component: LancamentoCadastroComponent,
    canActivate : [AuthGuard],
    data : { roles :['ROLE_REGISTER_FINANCIAL_RELEASE'] }
  },
];

@NgModule({

  imports: [
    RouterModule.forChild(routes)
  ],

  exports:[RouterModule]
})
export class LancamentosRoutingModule { }
