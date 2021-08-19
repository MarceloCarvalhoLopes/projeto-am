import { PessoaService } from './../pessoa.service';
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Title } from '@angular/platform-browser';

import { People, Address } from './../../core/model';
import { MessageService } from 'primeng/api';
import { ErrorHandlerService } from 'src/app/core/error-handler.service';


@Component({
  selector: 'app-pessoa-cadastro',
  templateUrl: './pessoa-cadastro.component.html',
  styleUrls: ['./pessoa-cadastro.component.css']
})
export class PessoaCadastroComponent implements OnInit {

  people = new People;
  address = new Address;

  constructor(
    private pessoaService : PessoaService,
    private messageService : MessageService,
    private errorHandlerService : ErrorHandlerService,
    private route: ActivatedRoute,
    private router: Router,
    private title:  Title
    ) { }

  ngOnInit(): void {

    const personId = this.route.snapshot.params['id'];

    this.title.setTitle('Nova pessoa');

    if (personId){
      this.loadPeople(personId);
    }

  }

  get editing(){
    return Boolean(this.people.id);
  }

  create(form: FormControl ){
    this.pessoaService.create(this.people)
      .then(savedPeople => {
        this.messageService.add({ severity: 'success', detail: 'Pessoa adicionado com sucesso!'});
        this.router.navigate(['/pessoas', savedPeople.id]);

        //form.reset();
        //this.people = new People();
      })
      .catch(erro => this.errorHandlerService.handle(erro));
  }

  update(form: FormControl ){
    this.pessoaService.update(this.people)
      .then(people => {
        this.people = people;

        this.messageService.add({ severity: 'success', detail: 'Pessoa alterada com sucesso!' })
        this.updateTitleEdition();

      })
      .catch(erro => this.errorHandlerService.handle(erro));
  }

  loadPeople(id: number){
    this.pessoaService.findById(id)
      .then(people => {
        this.people = people;
        this.updateTitleEdition();
      })
    .catch(erro => this.errorHandlerService.handle(erro)) ;
  }


  new(form: FormControl){
    form.reset(new People());
    this.router.navigate(['/pessoas/nova']);
  }

  updateTitleEdition(){
    this.title.setTitle(`Edição de pessoa: ${this.people.name}`);
  }

  save(form : FormControl){
    if(this.editing){
      this.update(form)
    }else{
      this.create(form)
    }

  }

}
