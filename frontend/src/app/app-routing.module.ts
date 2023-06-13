import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './pages/home/home.component';
import { CartComponent } from './pages/cart/cart.component';
import { OrderComponent } from './pages/order/order.component';

const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'cart',
    component: CartComponent
  },
  {
    path: 'cart/continue',
    component: OrderComponent
  },
  {
    path: '',
    redirectTo: "home",
    pathMatch: "full"
  },
  { path: '404', 
  loadChildren: 
    () => import('./components/not-found/not-found.module')
      .then(m => m.NotFoundModule) 
  },
  { 
    path: 'auth', 
    loadChildren: 
    () => import('./authentication/authentication.module')
    .then(m => m.AuthenticationModule) 
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
