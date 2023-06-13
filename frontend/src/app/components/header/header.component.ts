import { Component, Input, OnInit } from '@angular/core';
import { Cart, CartItem } from 'src/app/models/cart.model';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit{

  private _cart: Cart = { items: [] }
  itemsQuantity = 0;

  @Input() 
  
  public get cart() : Cart {
    return this._cart;
  }
  
  public set cart(cart : Cart) {
    this._cart = cart;
    this.itemsQuantity 
      = cart
        .items
        .map( (item) =>item.quantity )
        .reduce( (previous, current) => previous + current, 0 );
  }
  
  

  constructor(private _cartService: CartService){
  }
  ngOnInit(): void {
  
  }

  getTotal( items: Array<CartItem> ):number{
    return this._cartService.getTotal(items);
  }

  onClearCart(){
    this._cartService.clearCart();
  }

 
}
