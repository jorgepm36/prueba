import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filter',
  standalone: true
})
export class FilterPipe implements PipeTransform {

 transform(clientes: any[], texto: string): any[] {
    if (!texto) return clientes;
    return clientes.filter(cli => 
      cli.nombre.toLowerCase().includes(texto.toLowerCase()) || 
      cli.identificacion.toLowerCase().includes(texto.toLowerCase())
    );
  }

}
