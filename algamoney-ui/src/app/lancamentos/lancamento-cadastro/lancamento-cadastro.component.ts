import { FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

import { ErrorHandlerService } from './../../core/error-handler.service';
import { CategoriaService } from './../../categorias/categoria.service';
import { PessoaService } from './../../pessoas/pessoa.service';
import { Launching } from './../../core/model';

export interface Categoria {
  id: number,
  name: string
}

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

  categorias = [] = [];
  pessoas = [] = [];
  launching = new Launching;

  constructor(
    private categoriaService : CategoriaService,
    private errorHandlerService : ErrorHandlerService,
    private pessoaService : PessoaService
  ) { }

  ngOnInit(): void {
    this.loadCategories();
    this.loadPeople();
  }

  save(form: FormControl ){
    console.log(this.launching);
  }

  loadCategories(){
    return this.categoriaService.listAll()
    .then(categorias => {
      this.categorias = categorias.map((c: Categoria) =>  ({ label: c.name, value: c.id}));
    })
    .catch(erro => this.errorHandlerService.handle(erro));
  }

  loadPeople(){
    this.pessoaService.listAll()
    .then(pessoas => {
      this.pessoas = pessoas.map((p: Pessoa) => ({label : p.name, value: p.id }));
    })
    .catch(erro => this.errorHandlerService.handle(erro));
  }

}
