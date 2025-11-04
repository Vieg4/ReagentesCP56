package br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.model;

public enum TipoMovimentacao {
    Entrada_nota,
    Saida_uso_analisador,
    Saida_descarte_vencimento,
    Saida_descarte_controle_qualidade,
    Ajuste_inventario_positivo,
    Ajuste_inventario_negativo;
}
