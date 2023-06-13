import { Injectable } from '@angular/core';
import { Cart, CartItem } from '../models/cart.model';
import { BehaviorSubject } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  cart = new BehaviorSubject<Cart>( { items: [] } )

  // takes single parameter snackBar so _snackBar
  constructor(private _snackBar: MatSnackBar) { }

  addItemToCart(item: CartItem): void{
    // spread all array elements of items in the cart to items
    const items = [...this.cart.value.items];

    // Check whether the item is already added to the cart if added increase the amount else add
    const itemInCart: CartItem | undefined = items.find(
      (_item) => _item.productId === item.productId );
      console.log("items in cart: ",itemInCart);

      if(!itemInCart){
        items.push(item);
      } else{
        itemInCart.quantity++;
      }

      // add array of items object as next value of behaviour subject
      this.cart.next( {items} )
      // open snackbar and show item is added for 4 seconds
      this._snackBar.open(item.productName+" added to the cart. ", "OK", { duration: 4000, verticalPosition: 'top', horizontalPosition: 'right' })
      console.log(this.cart.value)
  }

  getTotal(cartItems: Array<CartItem>):number{
    return cartItems
     .map( (item) => item.price*item.quantity)
     .reduce( (previousValue , currentValue) => previousValue + currentValue , 0 )
   }

  clearCart():void{
    this.cart.next( {items: []} )
    this._snackBar.open("Cart cleared. ", "Ok", {duration:3000})
  }

  removeItemFromCart(item: CartItem, updateCart = true):CartItem[]{
    const filteredItems = this.cart.value.items.filter(
      (_item) => _item.productId !== item.productId
    );

    if (updateCart) {
      this.cart.next({ items: filteredItems });
      this._snackBar.open('1 item removed from cart.', 'Ok', {
        duration: 3000,
      });
    }

    return filteredItems;
  }

  decreaseQuantity(item: CartItem){
    let itemForRemoval!: CartItem;

    let filteredItems = this.cart.value.items.map((_item) => {
      if (_item.productId === item.productId) {
        _item.quantity--;
        if (_item.quantity === 0) {
          itemForRemoval = _item;
        }
      }

      return _item;
    });

    if (itemForRemoval) {
      filteredItems = this.removeItemFromCart(itemForRemoval, false);
    }

    this.cart.next({ items: filteredItems });
    this._snackBar.open('1 item removed from cart.', 'Ok', {
      duration: 3000,
    });

    }
}
