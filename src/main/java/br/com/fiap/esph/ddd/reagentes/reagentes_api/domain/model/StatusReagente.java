package br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.model;

public enum StatusReagente {
    /**
     * O caso esta em andamento.
     */
    ATIVO,

    /**
     * O caso esta em fase de analise ou consulta inicial.
     */
    Consultoria,

    /**
     * O caso foi concluido e esta aguardando arquivamento.
     */
    Fechado,

    /**
     * O caso foi consluido e arquivado.
     */
    Arquivado
}
