import { Component } from '@angular/core';
import { ClientesService } from '../clientes.service';
import { Cliente } from '../../models/cliente.model';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Respuesta } from '../../models/respuesta.model';
import { EditarCuentaComponent } from '../../cuenta/editar-cuenta/editar-cuenta.component';
import { Cuenta } from '../../models/cuenta.model';

@Component({
  selector: 'app-editar-cliente',
  standalone: true,
  imports: [CommonModule, FormsModule, EditarCuentaComponent],
  templateUrl: './editar-cliente.component.html',
  styleUrl: './editar-cliente.component.css'
})
export class EditarClienteComponent {

  cliente: Cliente;
  respuesta: Respuesta;
  cuenta: Cuenta;

  constructor(private clientesService: ClientesService,
    private router: Router
  ) {}

  ngOnInit(): void {
    if(this.clientesService.clienteSeleccionado){
      this.cliente = this.clientesService.clienteSeleccionado;
      console.log(this.cliente);
      
    }
    else{
      this.cliente = { 
        id: null,
        identificacion: '',
        nombre: '',
        genero: '',
        edad: 0,
        direccion: '',
        telefono: '',
        clienteid: '',
        contrasenia: '',
        estado: 'ACTIVO',
        cuenta: null
       };
    }
  }

  guardarCliente() {
    this.cliente.nombre = this.cliente.nombre.toUpperCase();
    this.cliente.direccion = this.cliente.direccion.toUpperCase();


    if(this.cliente.id != null && this.cliente.id > 0){
      if(this.cuenta){
        this.cliente.cuenta = this.cuenta;
      }
    }

      this.clientesService.guardarCliente(this.cliente).subscribe({
        next: (response: Respuesta) => {
          console.log('Cliente guardado:', response);
          if(response.estado === 'OK')
            this.cliente.id = response.idEntidad;

          this.respuesta = response;
          //this.router.navigate(['/editar-cliente']);
        },
        error: (err) => {
          console.error('Error al guardar', err);
          if(err.error)
          this.respuesta = new Respuesta(null, "ERROR", err.error?.error, 0);
        }
      });
  }

  cancelar(){
    this.router.navigate(['/clientes']);
  }
}
