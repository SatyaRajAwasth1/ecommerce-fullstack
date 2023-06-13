import {Component} from '@angular/core';
import {FormControl, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {NgFor} from '@angular/common';
import {MatSelectModule} from '@angular/material/select';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';


interface Address {
  value: string;
  viewValue: string;
}

interface AddressGroup {
  disabled?: boolean;
  name: string;
  address: Address[];
}

@Component({
  selector: 'stepper-intl-example',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css'],
  standalone: true,
  imports: [
    MatFormFieldModule,
    MatSelectModule,
    FormsModule,
    ReactiveFormsModule,
    NgFor,
    MatInputModule,
    MatButtonModule,
    MatIconModule
  ],
})
export class OrderComponent {
  
  disableContinue = true;
  private deliveryAddress = '';
  private contactNum: number | undefined;

  updateContinueStatus(selectedAddress: any){
    if(this.contactNum !== undefined){
      this.disableContinue = false;
    }
    this.deliveryAddress = selectedAddress;
  }

  addressControl = new FormControl('');
  addressGroups: AddressGroup[] = [
    {
      name: 'Kathmandu',
      address: [
        {value: 'Kathmandu-1', viewValue: 'Baneshwor'},
        {value: 'Kathmandu-2', viewValue: 'Maharajgunj'},
        {value: 'Kathmandu-3', viewValue: 'Kalanki'},
        {value: 'Kathmandu-4', viewValue: 'Kirtipur'},
        {value: 'Kathmandu-5', viewValue: 'NayaBazzar'},
      ],
    },
    {
      name: 'LalitPur',
      address: [
        {value: 'squirtle-3', viewValue: 'Squirtle'},
        {value: 'psyduck-4', viewValue: 'Psyduck'},
        {value: 'horsea-5', viewValue: 'Horsea'},
      ],
    },
    {
      name: 'Bhaktpur',
      address: [
        {value: 'charmander-6', viewValue: 'Charmander'},
        {value: 'vulpix-7', viewValue: 'Vulpix'},
        {value: 'flareon-8', viewValue: 'Flareon'},
      ],
    },
    {
      name: 'Dhangadhi',
      address: [
        {value: 'mew-9', viewValue: 'Mew'},
        {value: 'mewtwo-10', viewValue: 'Mewtwo'},
      ],
    },
  ];

}

