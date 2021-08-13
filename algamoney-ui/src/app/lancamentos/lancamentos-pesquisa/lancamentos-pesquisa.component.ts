import { ErrorHandlerService } from './../../core/error-handler.service';
import { Component, OnInit, ViewChild } from '@angular/core';

import { ConfirmationService, LazyLoadEvent, MessageService } from 'primeng/api';
import { Table } from 'primeng/table';

import { LancamentoService, LauchingFilter } from './../lancamento.service';
import { Title } from '@angular/platform-browser';

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

  constructor(
    private lancamentoService : LancamentoService,
    private messageService : MessageService,
    private confirmationService : ConfirmationService,
    private errorHandlerService : ErrorHandlerService,
    private title : Title
    ){}

  ngOnInit() {
    //this.search();
    this.title.setTitle('Pesquisa de Lançamentos');
  }

  search(page = 0) {
    this.filter.page = page;

    this.lancamentoService.search(this.filter)
      .then(result => {
        this.totalRecords = result.total;
        this.lancamentos = result.launchings;
    })
    .catch(error => this.errorHandlerService.handle(error));
  }

  delete(lancamento: any){
    this.lancamentoService.delete(lancamento.id)
    .then(() =>{
      if(this.grid.first === 0){
        this.search();
      }else{
        this.grid.reset();
        //console.log('Excluído');
      }
      this.messageService.add({ severity: 'success', detail: 'Lançamento excluído com sucesso!' });

    })
    .catch(error => this.errorHandlerService.handle(error));
  }

  confirmDeletion(lancamento: any) {
    this.confirmationService.confirm({
      message: 'Tem certeza que deseja excluir?',
      accept: () => {
        this.delete(lancamento);
      }
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
