import { Injectable } from '@angular/core';
import { Cliente } from '../models/cliente.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Respuesta } from '../models/respuesta.model';

@Injectable({
  providedIn: 'root'
})
export class ClientesService {
  private apiUrl = 'http://localhost:8080/clientes/';

  constructor(private http: HttpClient) { }

  getClientes(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(this.apiUrl+"listar");
  }

  clienteSeleccionado: Cliente | null = null;

  setCliente(cliente: Cliente) {
    this.clienteSeleccionado = cliente;
  }

  guardarCliente(cliente: Cliente): Observable<Respuesta>{
    if(cliente.id != null && cliente.id > 0){
      return this.http.put<Respuesta>(this.apiUrl+cliente.id, cliente);
    }
    else{
      return this.http.post<Respuesta>(this.apiUrl+"guardar", cliente);
    }
  }

  eliminarCliente(cliente: Cliente): Observable<Respuesta>{
    return this.http.delete<Respuesta>(this.apiUrl+cliente.id);
  }

}
