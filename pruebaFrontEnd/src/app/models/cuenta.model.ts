export interface Cuenta{
    id: number;
    numeroCuenta: string;
    tipoCuenta: 'AHORROS' | 'CORRIENTE';
    saldoInicial: number;
    estado: 'ACTIVO' | 'INACTIVO';
    idCliente: number;
}