import { Cuenta } from "./cuenta.model";

export interface Cliente {
  id: number;
  identificacion: string;
  nombre: string;
  genero: string;
  edad: number;
  direccion: string;
  telefono: string;
  
  clienteid: string;
  contrasenia: string;
  estado: 'ACTIVO' | 'INACTIVO';

  cuenta: Cuenta;
}