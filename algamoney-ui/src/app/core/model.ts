export class Address{
  address: string;
  number: string;
  additional: string;
  neighborhood: string;
  city: string;
  state: string;
  zipCode: string;
}

export class People{
  id : number;
  name : string;
  address = new Address();
  active = true;

}

export class Category{
  id : number;
  name: string;
}

export class Launching {
  id : number;
  description : string;
  dueDate : Date;
  paymentDate : Date;
  value : number;
  observation: string;
  type : string = 'RECEIPT';
  people = new People();
  category = new Category();
}
