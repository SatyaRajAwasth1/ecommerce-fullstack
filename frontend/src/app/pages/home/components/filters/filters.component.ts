import { Component, EventEmitter, OnDestroy, OnInit, Output } from '@angular/core';
import { Subscription } from 'rxjs';
import { StoreService } from 'src/app/services/store.service';

@Component({
  selector: 'app-filters',
  templateUrl: './filters.component.html',
  styles: [
  ]
})
export class FiltersComponent implements OnInit, OnDestroy{
 @Output() categoryToBeShown= new EventEmitter<string>();
  
 categories: Array<string> | undefined;
 categoriesSubscription: Subscription | undefined;

 constructor(private storeService: StoreService){

 }
  
  ngOnInit(): void {
    this.categoriesSubscription = this.storeService.getAllCategories()
      .subscribe(
        (response) => {
          this.categories = response;
        }
      )
  }


updateCategoryOnSelection(categorySelected:string){
  this.categoryToBeShown.emit(categorySelected);
}

ngOnDestroy(): void {
    if(this.categoriesSubscription){
      this.categoriesSubscription.unsubscribe();
    }
  }
}
