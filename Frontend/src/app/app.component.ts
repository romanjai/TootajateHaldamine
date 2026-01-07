import {Component} from '@angular/core';
import {TableComponent} from './components/table/table.component';
import {AddTootajaComponent} from './components/addTootaja/addTootaja.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [TableComponent, AddTootajaComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {}
