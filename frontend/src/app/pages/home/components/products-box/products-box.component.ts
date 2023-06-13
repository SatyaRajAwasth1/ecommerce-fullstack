import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { Product } from 'src/app/models/product.model';

@Component({
  selector: 'app-products-box',
  templateUrl: './products-box.component.html',
  styles: [
  ]
})
export class ProductsBoxComponent implements OnInit{
  @Input() fullWidthMode = false

  @Input() 
  product!: Product;
  // send to parent i.e. home component
  @Output() addProductToCart = new EventEmitter()

  constructor(){

  }
 ngOnInit(): void {
  }
  addToCart(){
    this.addProductToCart.emit(this.product);
  }



}
