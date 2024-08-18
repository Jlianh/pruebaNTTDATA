import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-client-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './client-form.component.html',
  styleUrl: './client-form.component.css'
})
export class ClientFormComponent {

  clientForm!: FormGroup;

  documentTypes = [
    { label: 'Cedula de ciudadania', value: 'CC' },
    { label: 'Pasaporte ', value: 'PP' },
  ]

  constructor(private fb: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    this.clientForm = this.fb.group({
      documentType: ['', Validators.required],
      document: ['', [Validators.required, Validators.pattern(/^[0-9]+$/), Validators.minLength(8), Validators.maxLength(11)]]
    })
  }

  getClientData(): void {
    if (this.clientForm.valid) {
      this.router.navigate(['/client-result'], { queryParams: this.clientForm.value });
    }
  }



}
