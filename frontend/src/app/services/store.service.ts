import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../models/product.model';
import { Observable } from 'rxjs';

const STORE_BASE_URL = 'http://localhost:8080';

@Injectable({
  providedIn: 'root'
})
export class StoreService {

  constructor(private httpClient: HttpClient) { }

  getAllProducts(limit:number, sort:string, category?: string): Observable<Array<Product>>{

    if(category === ''){
    return this.httpClient.get<Product[]>(
      // `${STORE_BASE_URL}/products?sort=${sort}&limit=${limit}`) // for pagination
      `${STORE_BASE_URL}/api/products`, {
        headers: {
          'requiredToken': 'false'
        }
      })
    }
    else{
      return this.httpClient.get<Array<Product>>(
        // `${STORE_BASE_URL}/products/category/${category}?sort=${sort}&limit=${limit}`)
        `${STORE_BASE_URL}/api/products/${category}`, {
          headers: {
            'requiredToken': 'false'
          }
        })
    }
  }


    getAllCategories(): Observable<Array<string>>{
      console.log("updated api")
      return this.httpClient.get<Array<string>>(
        `${STORE_BASE_URL}/api/products/categories`, {
          headers: {
            'requiredToken': 'false'
          }
        }
      )}

      sayHello(): Observable<any>{
        console.log("called sayhello service observable")
        return this.httpClient.get(
          `${STORE_BASE_URL}/api/`, {
            headers: {
              'requiredToken': 'false'
            }
          }
        )}

        
      }


