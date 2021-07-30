import { Component, OnInit } from '@angular/core';

import { LazyLoadEvent } from 'primeng/api';

import { PeopleFilter,PessoaService } from './../pessoa.service';

@Component({
  selector: 'app-pessoas-pesquisa',
  templateUrl: './pessoas-pesquisa.component.html',
  styleUrls: ['./pessoas-pesquisa.component.css']
})
export class PessoasPesquisaComponent implements OnInit {

  totalRecords = 0;
  filter = new PeopleFilter();
  pessoas: any[] = [];

  constructor(private pessoaService : PessoaService){}

  ngOnInit(){
    //this.search(0);
  }

  search(page = 0){
    this.filter.page = page;

    this.pessoaService.search(this.filter)
      .then(result => {
        this.totalRecords = result.total;
        this.pessoas = result.people;
      });
  }

  onPageChange(event : LazyLoadEvent){
    let page = 0;
    if (event.first && event.rows){
      page = event.first / event.rows;
    }
    this.search(page);
  }
}
