import { Component, OnInit, ViewChild } from '@angular/core';

import { LazyLoadEvent, MessageService, ConfirmationService } from 'primeng/api';
import { Table } from 'primeng/table';

import { PeopleFilter,PessoaService } from './../pessoa.service';
import { ErrorHandlerService } from './../../core/error-handler.service';

@Component({
  selector: 'app-pessoas-pesquisa',
  templateUrl: './pessoas-pesquisa.component.html',
  styleUrls: ['./pessoas-pesquisa.component.css']
})
export class PessoasPesquisaComponent implements OnInit {

  totalRecords = 0;
  filter = new PeopleFilter();
  pessoas: [] = [];
  @ViewChild('table') grid: Table;

  constructor(
    private pessoaService : PessoaService,
    private messageService : MessageService,
    private confirmationService : ConfirmationService,
    private errorHandlerService : ErrorHandlerService
    ){}

  ngOnInit(){
    //this.search(0);
  }

  search(page = 0){
    this.filter.page = page;

    this.pessoaService.search(this.filter)
      .then(result => {
        this.totalRecords = result.total;
        this.pessoas = result.people;
      })
      .catch(error => this.errorHandlerService.handle(error));
  }

  delete(pessoa : any){
    this.pessoaService.delete(pessoa.id)
    .then(() =>{
      if (this.grid.first === 0){
        this.search();
      }else{
        this.grid.reset();
      }
      this.messageService.add({ severity: 'success', detail: 'Pessoa excluído com sucesso"'});
    })
    .catch(error => this.errorHandlerService.handle(error));
  }

  confirmDeletion(pessoa: any){
    this.confirmationService.confirm({
      message: 'Tem certeza que deseja excluir?',
      accept:() => {
        this.delete(pessoa);
      }
    });
  }

  onPageChange(event : LazyLoadEvent){
    let page = 0;
    if (event.first && event.rows){
      page = event.first / event.rows;
    }
    this.search(page);
  }

  changeStatus(pessoa: any): void{
    const newStatus = !pessoa.active;

    this.pessoaService.changeStatus(pessoa.id, newStatus)
      .then(() => {
        const action = newStatus ? 'ativada' : 'desativada';

        pessoa.active = newStatus;
        this.messageService.add({ severity: 'success', detail: `Pessoa ${action} com sucesso!`});
      })
      .catch(erro => this.errorHandlerService.handle(erro));
  }
}
