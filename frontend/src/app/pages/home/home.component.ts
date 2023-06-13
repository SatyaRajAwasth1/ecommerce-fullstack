import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Product } from 'src/app/models/product.model';
import { CartService } from 'src/app/services/cart.service';
import { StoreService } from 'src/app/services/store.service';

const ROWS_HEIGHT: { [id: number] :number } = {1:400, 3:335, 4: 350}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styles: [
  ]
})
export class HomeComponent implements OnInit, OnDestroy{

  numberOfItemsToBeShownPerRow = 3;
  rowHeight = ROWS_HEIGHT[this.numberOfItemsToBeShownPerRow]
  categoryToBeShown = ''
  sort='desc';
  count=12;
  products: Array<Product> | undefined
  productSubscription: Subscription | undefined

  // inject CartService / constructor injection
  constructor(private cartService: CartService, private storeService: StoreService){  
  }
 
  ngOnInit(): void {
    this.sayHello();
    this.getProducts();
  }

  sayHello(){
    this.storeService.sayHello()
      .subscribe(
        (response) => {
          console.log("random api ",response.message)
        });
  }

  getProducts(): void{
    this.productSubscription = this.storeService.getAllProducts(this.count, this.sort, this.categoryToBeShown)
      .subscribe(
        (_products) => {
          this.products = _products;
        });
  }

  // child passes the number when the event is triggered
  columnLayoutUpdate(event: number):void {
    this.numberOfItemsToBeShownPerRow = event
    this. rowHeight = ROWS_HEIGHT[this.numberOfItemsToBeShownPerRow]
  }

  limitNoOfItemsPerPage(noOfItems: number){
    this.count = noOfItems;
    this.getProducts();
  }
  updateItemsSortBy(sortBy: string){
    this.sort = sortBy;
    this.getProducts();
  }

  showProductsOfCategory(event:string):void{
    this.categoryToBeShown = event
    this.getProducts();
  }

  onAddProductToCart(product: Product):void{
    this.cartService.addItemToCart( {
      productId: product.productId,
      productImageLink: product.image,
      productName: product.productName,
      quantity: 1,
      price: product.price
    } )
  }



  ngOnDestroy(): void {
    if(this.productSubscription){
      this.productSubscription.unsubscribe();
    }
  }


}
