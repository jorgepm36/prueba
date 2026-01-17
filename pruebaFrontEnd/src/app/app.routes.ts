import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: 'clientes',
        loadComponent: () => import('./clientes/listado-clientes/listado-clientes.component').then(c => c.ListadoClientesComponent)
    },
    {
        path: 'editar-cliente',
        loadComponent: () => import('./clientes/editar-cliente/editar-cliente.component')
        .then(c => c.EditarClienteComponent)
    }
];
