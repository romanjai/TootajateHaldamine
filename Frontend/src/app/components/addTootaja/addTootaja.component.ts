import {Component} from '@angular/core';
import {ApiService} from '../../service/api.service';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {NgClass} from '@angular/common';

@Component({
  selector: 'AddTootajaComponent',
  standalone: true,
  templateUrl:'./addTootaja.component.html',
  styleUrl: './addTootaja.component.css',
  imports: [
    ReactiveFormsModule,
    NgClass
  ]
})

export class AddTootajaComponent {
  tootajaForm: FormGroup;

  constructor(private fb: FormBuilder, public apiService: ApiService) {
    this.tootajaForm = this.fb.group({
      nimi: ['', [Validators.required, Validators.maxLength(40), Validators.pattern('[a-zA-ZÕÄÖÜõäöü -]+')]], // tahed, tuhikud, (Eesti) tapitahed ja "-"
      email: ['', [Validators.required, Validators.email]],
      telefon: ['', [Validators.required, Validators.pattern('[+]?[0-9]{7,12}')]], // lubatud alguses + ning peale seda 7-12 numbrit
      elukoht: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(50), Validators.pattern('[a-zA-Z0-9ÕÄÖÜõäöü ,-]+')]], // sama mis nimi, aga numbrid ja koma juurde pandud
    })
  }

  submit() {
    if (this.tootajaForm.valid) {
      this.apiService.addTootaja(this.tootajaForm.value)
      this.tootajaForm.reset();
    }
  }
}
