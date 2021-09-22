import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DashboardComponent } from './dashboard/dashboard.component';

import { AuthGuard } from '../seguranca/auth.guard';

const routes: Routes = [

  {
    path: '',
    component : DashboardComponent,
    canActivate : [AuthGuard],
    data : { roles :['ROLE_SEARCH_FINANCIAL_RELEASE'] }
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
