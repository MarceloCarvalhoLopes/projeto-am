import { Component, OnInit } from '@angular/core';

import { ErrorHandlerService } from './../../core/error-handler.service';
import { CategoriaService } from './../../categorias/categoria.service';

export interface Categoria {
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
    { label: 'Receita', value: 'RECEITA' },
    { label: 'Despesa', value: 'DESPESA' },
  ];

  categorias = [] = [];

  pessoas = [
    { label: 'João da Silva', value: 4 },
    { label: 'Sebastião Souza', value: 9 },
    { label: 'Maria Abadia', value: 3 },
  ];

  constructor(
    private categoriaService : CategoriaService,
    private errorHandlerService : ErrorHandlerService
  ) { }

  ngOnInit(): void {
    this.loadCategories();
  }

  loadCategories(){
    return this.categoriaService.listAll()
    .then(categorias => {
      this.categorias = categorias.map((c: Categoria) =>  ({ label: c.name, value: c.id}));
    })
    .catch(erro => this.errorHandlerService.handle(erro));
  }



}
