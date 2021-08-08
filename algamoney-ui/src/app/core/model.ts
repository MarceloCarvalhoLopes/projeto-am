export class People{
  id : number;
}

export class Category{
  id : number;
}

export class Launching {
  id : number;
  description : string;
  dueDate : Date;
  paymentDate : Date;
  value : number;
  observation: string;
  type : string = 'RECEIPT';
  person = new People();
  category = new Category();
}
