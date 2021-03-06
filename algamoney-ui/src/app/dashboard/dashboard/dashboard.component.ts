import { Component, OnInit } from '@angular/core';

import { DashboardService } from '../dashboard.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit{

  pieChartData: any;

  lineChartData = {
    labels: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'],
    datasets: [
      {
        label: 'Receitas',
        data: [4, 10, 18, 5, 1, 20, 3],
        borderColor: '#3366CC'
      }, {
        label: 'Despesas',
        data: [10, 15, 8, 5, 1, 7, 9],
        borderColor: '#D62B00'
      }
    ]
  };

  constructor(private dashboardService : DashboardService){}

  ngOnInit(){
    this.setUpPizzaGraphic();
  }

  setUpPizzaGraphic(){
    this.dashboardService.lauchingByCategory()
      .then(dados => {
        this.pieChartData = {
          labels : dados.map(dado => dado.category.name),
          datasets: [
            {
              data : dados.map(dado => dado.total),
              backgroundColor: ['#FF9900', '#109618', '#990099', '#3B3EAC', '#0099C6',
              '#DD4477', '#3366CC', '#DC3912']
            }
          ]
        };
      });
  }

}
