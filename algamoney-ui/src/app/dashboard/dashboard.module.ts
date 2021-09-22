import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PanelModule } from 'primeng/panel';

import { DashboardRoutingModule } from './dashboard-routing.module';
import { SharedModule } from './../shared/shared.module';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,

    PanelModule,

    SharedModule,
    DashboardRoutingModule
  ]
})
export class DashboardModule { }
