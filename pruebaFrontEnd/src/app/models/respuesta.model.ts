export class Respuesta {
    constructor(
        public dto: object,
        public estado: string,
        public mensaje: string,
        public idEntidad: number
    ){}
}