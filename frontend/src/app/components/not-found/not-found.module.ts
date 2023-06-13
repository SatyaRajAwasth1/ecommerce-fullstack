import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NotFoundRoutingModule } from './not-found-routing.module';
import { NotFoundComponent } from './404/404.component';


@NgModule({
  declarations: [
    NotFoundComponent
  ],
  imports: [
    CommonModule,
    NotFoundRoutingModule
  ]
})
export class NotFoundModule { }
