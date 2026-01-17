import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Cuenta } from '../../models/cuenta.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-editar-cuenta',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './editar-cuenta.component.html',
  styleUrl: './editar-cuenta.component.css'
})
export class EditarCuentaComponent {
  @Input() idCliente!: number;
  cuenta: Cuenta;
  @Output() cuentaCambiada = new EventEmitter<any>();

  ngOnInit(): void {
    console.log("this.idCliente: ", this.idCliente);
     this.cuenta = {
      id:null,
      numeroCuenta: "",
      tipoCuenta: 'AHORROS',
      saldoInicial: 0,
      estado: 'ACTIVO',
      idCliente:this.idCliente
     };
  }

  onCambioCuenta(cuenta: any) {
    this.cuentaCambiada.emit(cuenta); // Emite cada vez que cambia
  }
}
