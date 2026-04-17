export interface Status {
    code: '1' | '2' | '3';
    description: 'Pendente' | 'Em Andamento' | 'Concluída';
}