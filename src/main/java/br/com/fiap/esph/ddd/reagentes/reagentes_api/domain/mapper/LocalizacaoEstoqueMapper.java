package br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.mapper;

import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto.LocalizacaoEstoqueDTO;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.model.LocalizacaoEstoque;

public class LocalizacaoEstoqueMapper {

    public static LocalizacaoEstoqueDTO toDTO(LocalizacaoEstoque localizacao) {
        if (localizacao == null) return null;
        return new LocalizacaoEstoqueDTO(
                localizacao.getId(),
                localizacao.getCodigoLocal(),
                localizacao.getDescricao(),
                localizacao.getTipo(),
                localizacao.getFaixaTemperaturaNominal(),
                localizacao.getSetor()
        );
    }

    public static LocalizacaoEstoque toEntity(LocalizacaoEstoqueDTO dto) {
        if (dto == null) return null;
        LocalizacaoEstoque localizacao = new LocalizacaoEstoque();
        localizacao.setId(dto.id());
        localizacao.setCodigoLocal(dto.codigoLocal());
        localizacao.setDescricao(dto.descricao());
        localizacao.setTipo(dto.tipo());
        localizacao.setFaixaTemperaturaNominal(dto.faixaTemperaturaNominal());
        localizacao.setSetor(dto.setor());
        return localizacao;
    }
}
