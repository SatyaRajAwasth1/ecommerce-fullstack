import { Router } from '@angular/router';
import { CartItem } from './../../models/cart.model';
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/authentication/authentication.service';
import { Cart } from 'src/app/models/cart.model';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styles: [
  ]
})
export class CartComponent implements OnInit{

 

  cart: Cart = {items: []};

  dataSource: Array<CartItem> = [];
  columnToBeDisplayed: Array<string> = [
    'productImageLink',
    'productId',
    'productName',
    'price',
    'quantity',
    'total',
    'action'
  ]

  constructor(
    private _cartService: CartService,
    private _authService: AuthService,
    private _router: Router){

  }

  ngOnInit(): void {
    this.dataSource = this.cart.items
    this._cartService.cart.subscribe(
      (_cart: Cart) => {
        this.cart = _cart;
        this.dataSource = this.cart.items;  
      }
    )
    if(!this._authService.isUserLoggedIn()){
      this._router.navigate(['auth/login',{l: 'f'}]);
    }
  }

  getTotal(cartItems: Array<CartItem>):number{
   return this._cartService.getTotal(cartItems);
  }

  clearAll():void{
    this._cartService.clearCart();
  }

  onRemoveItemFromCart(item: CartItem):void{
    this._cartService.removeItemFromCart(item);
  }

  onIncreasingQuantity(item: CartItem):void{
    this._cartService.addItemToCart(item);
  }
  onDecreasingQuantity(item: CartItem):void{
    this._cartService.decreaseQuantity(item);
  }

  onCheckOut(){
    if(this._authService.isUserLoggedIn()){
      // Continue if user is loggedin to checkout
      this._router.navigateByUrl("/cart/continue");
    }
    else{
    this._router.navigate(["/auth/login",{items: this.cart.items.length}])
    }
  }

}
