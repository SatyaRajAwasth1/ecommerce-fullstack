import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

// Material UI imports
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatMenuModule} from '@angular/material/menu';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatIconModule} from '@angular/material/icon';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatListModule} from '@angular/material/list';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatTableModule} from '@angular/material/table';
import {MatBadgeModule} from '@angular/material/badge';
import {MatSnackBarModule} from '@angular/material/snack-bar';

// HTTP client


import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './pages/home/home.component';
import { ProductsHeaderComponent } from './pages/home/components/products-header/products-header.component';
import { FiltersComponent } from './pages/home/components/filters/filters.component';
import { ProductsBoxComponent } from './pages/home/components/products-box/products-box.component';
import { CartComponent } from './pages/cart/cart.component';
import { FooterComponent } from './components/footer/footer.component';

import { CartService } from './services/cart.service';
import { StoreService } from './services/store.service';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { NotFoundModule } from './components/not-found/not-found.module';
import { TokenInterceptor } from './services/interceptors/token.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    ProductsHeaderComponent,
    FiltersComponent,
    ProductsBoxComponent,
    CartComponent,
    FooterComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,

    // HTTP client
    HttpClientModule,

   // Material UI Component Modules
    MatSidenavModule,
    MatGridListModule,
    MatMenuModule,
    MatButtonModule,
    MatCardModule,
    MatIconModule,
    MatExpansionModule,
    MatListModule,
    MatToolbarModule,
    MatTableModule,
    MatBadgeModule,
    MatSnackBarModule,

    // custom modules
    NotFoundModule
  ],
  providers: [
    CartService, 
    StoreService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
