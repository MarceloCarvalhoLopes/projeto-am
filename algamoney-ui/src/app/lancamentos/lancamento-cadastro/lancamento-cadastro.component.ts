import { FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { MessageService } from 'primeng/api';

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
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {

    //console.log(this.route.snapshot.params['id']);
    const launchingId = this.route.snapshot.params['id'];

    if (launchingId) {
      this.loadLaunching(launchingId);
    }

    this.loadCategories();
    this.loadPeople();
  }

  get editing(){
    return Boolean(this.launching.id);
  }

  loadLaunching( id : number ){
    this.lancamentoService.findById(id)
      .then(launching => {
        this.launching = launching;
      })
      .catch(erro => this.errorHandlerService.handle(erro));
  }

  save(form: FormControl  ){
    if (this.editing){
      this.update(form);
    }else{
      this.create(form)
    }
  }

  create(form: FormControl ){
    //console.log(this.launching);
    this.lancamentoService.create(this.launching)
      .then(savedLauching => {
        this.messageService.add({ severity: 'success', detail: 'Lançamento adicionado com sucesso!' });
        //form.reset();
        //this.launching = new Launching();

        this.router.navigate(['/lancamentos',savedLauching.id]);
      })
      .catch(erro => this.errorHandlerService.handle(erro));

  }

  update(form: FormControl){
    this.lancamentoService.update(this.launching)
      .then(Launching =>{
        this.launching = Launching;
        this.messageService.add({ severity: 'success', detail: 'Lançamento alterado com sucesso!' });
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

  new(form: FormControl){
    form.reset(new Launching());
    this.router.navigate(['/lancamentos/novo']);
  }

}
