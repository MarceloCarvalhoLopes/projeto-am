import { PessoaService } from './../pessoa.service';
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

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
    ) { }

  ngOnInit(): void {
  }

  create(form: FormControl ){
    this.pessoaService.create(this.people)
      .then(() => {
        this.messageService.add({ severity: 'success', detail: 'Pessoa adicionado com sucesso!'});
        form.reset();
        this.people = new People();
      })
      .catch(erro => this.errorHandlerService.handle(erro));
  }


}
