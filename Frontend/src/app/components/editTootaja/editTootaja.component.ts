import {Component, Input} from '@angular/core';
import {ApiService} from '../../service/api.service';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {NgClass} from '@angular/common';
import {Tootaja} from '../../type/tootaja.type';

@Component({
  selector: 'EditTootajaComponent',
  standalone: true,
  templateUrl:'./editTootaja.component.html',
  styleUrl: './editTootaja.component.css',
  imports: [
    ReactiveFormsModule,
    NgClass
  ]
})

export class EditTootajaComponent {
  tootajaForm!: FormGroup;
  @Input() tootaja!: Tootaja

  constructor(private fb: FormBuilder, private apiService: ApiService) {
    this.tootajaForm = this.fb.group({
      id: [''],
      nimi: ['', [Validators.required, Validators.maxLength(40), Validators.pattern('[a-zA-ZÕÄÖÜõäöü -]+')]], // tahed, tuhikud, (Eesti) tapitahed ja "-"
      email: ['', [Validators.required, Validators.email]],
      telefon: ['', [Validators.required, Validators.pattern('[+]?[0-9]{7,12}')]], // lubatud alguses + ning peale seda 7-12 numbrit
      elukoht: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(50), Validators.pattern('[a-zA-Z0-9ÕÄÖÜõäöü ,-]+')]], // sama mis nimi, aga numbrid ja koma juurde pandud
    })
  }

  ngOnChanges() {
    if (this.tootaja) {
      this.tootajaForm.patchValue(this.tootaja);
    }
  }

  submit() {
    if (this.tootajaForm.valid) {
      this.apiService.editTootaja(this.tootajaForm.value)
    }
  }

  delete() {
    this.apiService.deleteTootaja(this.tootajaForm.value.id)
  }
}
