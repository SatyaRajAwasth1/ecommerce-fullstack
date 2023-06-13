import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-products-header',
  templateUrl:'./products-header.component.html',
  styles: [
  ]
})
export class ProductsHeaderComponent {

  @Output() noOfItemsToBeShownPerRow = new EventEmitter<number>()
  @Output() noOfItemsToBeShownPerPage = new EventEmitter<number>();
  @Output() sortItemsBy = new EventEmitter<string>();

  sortBy="ascending"
  itemsCount = 10  

  updateSortBy(sortyBY: string):void{
    if(this.sortBy === 'asc'){
      this.sortBy = 'ascending';
    } else{
      this.sortBy = 'descending';
    }
    console.log("sorting by: ",sortyBY)
    this.sortItemsBy.emit(sortyBY)
  }

  updateItemsAmount(amountOfItem: number):void{
    this.itemsCount = amountOfItem;
    this.noOfItemsToBeShownPerPage.emit(amountOfItem)
  }

  columnLayoutUpdate(noOfItemsPerRow: number):void{
    this.noOfItemsToBeShownPerRow.emit(noOfItemsPerRow);
  }

}
