import { LancamentoService } from './../lancamento.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-lancamentos-pesquisa',
  templateUrl: './lancamentos-pesquisa.component.html',
  styleUrls: ['./lancamentos-pesquisa.component.css']
})

export class LancamentosPesquisaComponent implements OnInit {

  lancamentos =  [] ;

  constructor(private lancamentoService : LancamentoService){}

  ngOnInit() {
    this.search();
  }

  search() {
    this.lancamentoService.search()
      .then(lancamentos => this.lancamentos = lancamentos);
  }

}
