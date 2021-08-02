import { Component, OnInit, ViewChild } from '@angular/core';

import { LazyLoadEvent } from 'primeng/api';
import { Table } from 'primeng/table';

import { LancamentoService, LauchingFilter } from './../lancamento.service';

@Component({
  selector: 'app-lancamentos-pesquisa',
  templateUrl: './lancamentos-pesquisa.component.html',
  styleUrls: ['./lancamentos-pesquisa.component.css']
})

export class LancamentosPesquisaComponent implements OnInit {

  totalRecords = 0;
  filter = new LauchingFilter();
  lancamentos: [] = [];
  @ViewChild('table') grid: Table

  constructor(private lancamentoService : LancamentoService){}

  ngOnInit() {
    //this.search();
  }

  search(page = 0) {
    this.filter.page = page;

    this.lancamentoService.search(this.filter)
      .then(result => {
        this.totalRecords = result.total;
        this.lancamentos = result.launchings;
    });
  }

  delete(lancamento: any){
    this.lancamentoService.delete(lancamento.id)
    .then(() =>{
      this.grid.reset();
      console.log('Excluído');
    });
  }

  onPageChange(event : LazyLoadEvent){
    //console.log(event);
    let page = 0;
    if (event.first && event.rows){
      page = event.first / event.rows;
    }
    this.search(page);
  }

}
