import { Component } from '@angular/core';
import { Cliente } from '../../models/cliente.model';
import { ClientesService } from '../clientes.service';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { Respuesta } from '../../models/respuesta.model';
import { FilterPipe } from '../../filter.pipe';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-listado-clientes',
  standalone: true,
  imports: [CommonModule, RouterLink, FormsModule, FilterPipe],
  templateUrl: './listado-clientes.component.html',
  styleUrl: './listado-clientes.component.css'
})
export class ListadoClientesComponent {

  clientes: Cliente[] = [];
  respuesta: Respuesta;
  filtroNombre: string = '';

  constructor(
    private clientesService: ClientesService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.clientesService.getClientes().subscribe({
    next: (data) => {
      this.clientes = data;
      console.log('Clientes:', data);
    },
    error: (err) => {
      console.error('Error al cargar clientes', err);
    }
  });
  }

  editarCliente(cliente: Cliente) {
    this.clientesService.setCliente(cliente);
    this.router.navigate(['/editar-cliente']);
  }

  nuevoCliente(){
    this.router.navigate(['/editar-cliente']);
  }

  eliminarCliente(cliente: Cliente){
    this.clientesService.eliminarCliente(cliente).subscribe({
      next: (response: Respuesta) => {
        console.log('Cliente eliminado:', response);
        this.respuesta = response;
        //this.router.navigate(['/editar-cliente']);
        location.reload();
      },
      error: (err) => {
        console.error('Error al guardar', err);
        if(err.error)
        this.respuesta = new Respuesta(null, "ERROR", err.error?.error, 0);
      }
    });

    
  }

}
