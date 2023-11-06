export interface Technician {
    id?: any;                       // --> A UTILIZAÇÃO DO "?" SIGNIFICA QUE O CAMPO É OPCIONAL
    name: string;
    cpf: string;
    email: string;
    password: string;
    role: string[];
    creationDate: any;              // --> A UTILIZAÇÃO DO "any" SIGNIFICA QUE O CAMPO PODE SER DE QUALQUER TIPO
}
