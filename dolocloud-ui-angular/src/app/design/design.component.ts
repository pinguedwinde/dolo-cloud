import { Component, OnInit, Injectable, Input } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router/';
import { CartService } from '../cart/cart-service';

@Component({
  selector: 'dolo-design',
  templateUrl: 'design.component.html',
  styleUrls: ['./design.component.css']
})

@Injectable()
export class DesignComponent implements OnInit {

  model = {
    name: '',
    ingredients: []
  };

  allIngredients: any;
  sugars = [];
  alcohols = [];
  nonAlcohols = [];
  sours = [];

  constructor(private httpClient: HttpClient, private router: Router, private cart: CartService) {
  }

  // tag::ngOnInit[]
  ngOnInit() {
    this.httpClient.get('http://localhost:8080/ingredients')
        .subscribe(data => {
          this.allIngredients = data;
          this.sugars = this.allIngredients.filter(su => su.type === 'SUGAR');
          this.alcohols = this.allIngredients.filter(al => al.type === 'ALCOHOL');
          this.nonAlcohols = this.allIngredients.filter(no => no.type === 'NON_ALCOHOL');
          this.sours = this.allIngredients.filter(so => so.type === 'SOUR');
        });
  }
  // end::ngOnInit[]

  updateIngredients(ingredient, event) {
    if (event.target.checked) {
      this.model.ingredients.push(ingredient);
    } else {
      this.model.ingredients.splice(this.model.ingredients.findIndex(i => i === ingredient), 1);
    }
  }

  // tag::onSubmit[]
  onSubmit() {
    this.httpClient.post(
        'http://localhost:8080/design',
        this.model, {
            headers: new HttpHeaders().set('Content-type', 'application/json'),
        }).subscribe(dolo => this.cart.addToCart(dolo));

    this.router.navigate(['/cart']);
  }
  // end::onSubmit[]

}
