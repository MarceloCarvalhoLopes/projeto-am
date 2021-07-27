import { LancamentoService, LauchingFilter } from './../lancamento.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-lancamentos-pesquisa',
  templateUrl: './lancamentos-pesquisa.component.html',
  styleUrls: ['./lancamentos-pesquisa.component.css']
})

export class LancamentosPesquisaComponent implements OnInit {

  description: string = '';
  dueDateOf!: Date;
  dueDateBy!:  Date;
  lancamentos =  [] ;

  constructor(private lancamentoService : LancamentoService){}

  ngOnInit() {
    this.search();
  }

  search() {

    const filter: LauchingFilter = {
      description: this.description,
      dueDateOf: this.dueDateOf,
      dueDateBy: this.dueDateBy
    };


    this.lancamentoService.search(filter)
      .then(lancamentos => this.lancamentos = lancamentos);
  }

}
