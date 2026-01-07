import {Component} from '@angular/core';
import {ApiService} from '../../service/api.service';
import {DatePipe} from '@angular/common';
import {AddTootajaComponent} from '../addTootaja/addTootaja.component';
import {EditTootajaComponent} from '../editTootaja/editTootaja.component';
import {Tootaja} from '../../type/tootaja.type';
import {PaginationComponent} from '../pagination/pagination.component';

@Component({
  selector: 'TableComponent',
  standalone: true,
  templateUrl:'./table.component.html',
  styleUrl: './table.component.css',
  imports: [
    DatePipe,
    AddTootajaComponent,
    EditTootajaComponent,
    PaginationComponent
  ]
})

export class TableComponent {
  selectedTootaja!: Tootaja
  constructor(public apiService: ApiService) {}

  selectTootaja(tootaja: Tootaja): void {
    this.selectedTootaja = tootaja
  }
}
