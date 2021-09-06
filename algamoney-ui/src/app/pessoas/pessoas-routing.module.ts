import { NgModule } from '@angular/core';
import { RouterModule, Routes } from "@angular/router";

import { PessoasPesquisaComponent } from "./pessoas-pesquisa/pessoas-pesquisa.component";
import { PessoaCadastroComponent } from './pessoa-cadastro/pessoa-cadastro.component';
import { AuthGuard } from '../seguranca/auth.guard';

const routes: Routes = [

  {
    path: '',
    component: PessoasPesquisaComponent,
    canActivate : [AuthGuard],
    data : { roles :['ROLE_SEARCH_PEOPLE'] }
  },
  {
    path: 'nova',
    component: PessoaCadastroComponent,
    canActivate : [AuthGuard],
    data : { roles :['ROLE_REGISTER_PEOPLE'] }

  },
  {
    path: ':id',
    component: PessoaCadastroComponent,
    canActivate : [AuthGuard],
    data : { roles :['ROLE_REGISTER_PEOPLE'] }
  },

];

@NgModule({

  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class PessoasRoutingModule { }
