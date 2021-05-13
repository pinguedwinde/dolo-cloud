export class CartItem {

  quantity = 1;

  dolo: any;

  constructor(dolo: any) {
    this.dolo = dolo;
  }

  get lineTotal() {
    return this.quantity * 4.99;
  }

}
