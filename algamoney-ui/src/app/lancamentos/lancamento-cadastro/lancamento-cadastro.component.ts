import { Title } from '@angular/platform-browser';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
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

  category = [] = [];
  people = [] = [];
  //people = new People;
  //launching = new Launching;
  formulario: FormGroup;

  constructor(
    private categoriaService : CategoriaService,
    private pessoaService : PessoaService,
    private lancamentoService : LancamentoService,
    private messageService : MessageService,
    private errorHandlerService : ErrorHandlerService,
    private route: ActivatedRoute,
    private router: Router,
    private title:  Title,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.configForm();
    //console.log(this.route.snapshot.params['id']);
    const launchingId = this.route.snapshot.params['id'];

    this.title.setTitle('Novo lançamento');

    if (launchingId) {
      this.loadLaunching(launchingId);
    }

    this.loadCategories();
    this.loadPeople();
  }

  configForm(){
    this.formulario = this.formBuilder.group({
      id:[],
      type: ['RECEIPT', Validators.required],
      dueDate: [null, Validators.required],
      paymentDate: [],
      description: [null, [ Validators.required, Validators.minLength(5) ]],
      value: [ null, Validators.required ],
      people: this.formBuilder.group({
        id: [ null, Validators.required ],
        name: []
      }),
      category: this.formBuilder.group({
        id: [ null, Validators.required ],
        name: []
      }),
      observation: []
    });
  }

  get editing(){
    return Boolean(this.formulario.get('id').value);
  }

  loadLaunching( id : number ){
    this.lancamentoService.findById(id)
      .then(launching => {
        //this.launching = launching;
        this.formulario.patchValue(launching) ;
        this.updateTitleEdition();
      })
      .catch(erro => this.errorHandlerService.handle(erro));
  }

  create(){
    //console.log(this.launching);
    this.lancamentoService.create(this.formulario.value)
      .then(savedLauching => {
        this.messageService.add({ severity: 'success', detail: 'Lançamento adicionado com sucesso!' });
        //form.reset();
        //this.launching = new Launching();

        this.router.navigate(['/lancamentos',savedLauching.id]);
      })
      .catch(erro => this.errorHandlerService.handle(erro));

  }

  update(){
    this.lancamentoService.update(this.formulario.value)
      .then(launching =>{
        this.formulario.patchValue(launching) ;
        //this.launching = Launching;
        this.messageService.add({ severity: 'success', detail: 'Lançamento alterado com sucesso!' });
        this.updateTitleEdition();
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

  new(){
    this.formulario.reset(new Launching());
    this.router.navigate(['/lancamentos/novo']);
  }

  updateTitleEdition(){
    this.title.setTitle(`Edição de lançamento: ${this.formulario.get('description').value}`);
  }

  save(){
    if (this.editing){
      this.update();
    }else{
      this.create();
    }
  }


}
