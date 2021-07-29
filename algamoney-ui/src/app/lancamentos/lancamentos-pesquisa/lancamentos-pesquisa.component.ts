import { LancamentoService, LauchingFilter } from './../lancamento.service';
import { Component, OnInit } from '@angular/core';
import { LazyLoadEvent } from 'primeng/api';

@Component({
  selector: 'app-lancamentos-pesquisa',
  templateUrl: './lancamentos-pesquisa.component.html',
  styleUrls: ['./lancamentos-pesquisa.component.css']
})

export class LancamentosPesquisaComponent implements OnInit {

  totalRecords = 0;
  filter = new LauchingFilter();
  lancamentos: [] = [];

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

  onPageChange(event : LazyLoadEvent){
    //console.log(event);
    let page = 0;
    if (event.first && event.rows){
      page = event.first / event.rows;
    }
    this.search(page);
  }

}
