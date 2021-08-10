import { MessageService } from 'primeng/api';
import { FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

import { ErrorHandlerService } from './../../core/error-handler.service';
import { CategoriaService } from './../../categorias/categoria.service';
import { PessoaService } from './../../pessoas/pessoa.service';
import { LancamentoService } from './../lancamento.service';
import { Launching, Category, People } from './../../core/model';

export interface Pessoa{
  id: number,
  name: string
}

@Component({
  selector: 'app-lancamento-cadastro',
  templateUrl: './lancamento-cadastro.component.html',
  styleUrls: ['./lancamento-cadastro.component.css']
})

export class LancamentoCadastroComponent implements OnInit {

  tipos = [
    { label: 'Receita', value: 'RECEIPT' },
    { label: 'Despesa', value: 'EXPENSE' },
  ];

  //categorias = [] = [];
  category = new Category;
  //pessoas = [] = [];
  people = new People;
  launching = new Launching;

  constructor(
    private categoriaService : CategoriaService,
    private pessoaService : PessoaService,
    private lancamentoService : LancamentoService,
    private messageService : MessageService,
    private errorHandlerService : ErrorHandlerService,

  ) { }

  ngOnInit(): void {
    this.loadCategories();
    this.loadPeople();
  }

  create(form: FormControl ){
    //console.log(this.launching);
    this.lancamentoService.create(this.launching)
      .then(() => {
        this.messageService.add({ severity: 'success', detail: 'Lançamento adicionado com sucesso!' });
        form.reset();
        this.launching = new Launching();
      })
      .catch(erro => this.errorHandlerService.handle(erro));

  }

  loadCategories(){
    return this.categoriaService.listAll()
    .then(category => {
      this.category = category.map((c: Category) =>  ({ label: c.name, value: c.id}));
    })
    .catch(erro => this.errorHandlerService.handle(erro));
  }

  loadPeople(){
    this.pessoaService.listAll()
    .then(people => {
      this.people = people.map((p: People) => ({label : p.name, value: p.id }));
    })
    .catch(erro => this.errorHandlerService.handle(erro));
  }

}
