package br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.mapper;

import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto.ReagenteDTO;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.model.Reagente;

public class ReagenteMapper {

    public static ReagenteDTO toDTO(Reagente reagente) {
        if (reagente == null) return null;
        return new ReagenteDTO(
                reagente.getId(),
                reagente.getNome(),
                reagente.getCodigoSku(),
                reagente.getLote(),
                reagente.getDataValidade(),
                reagente.getDataRecebimento(),
                reagente.getQuantidadeEmEstoque(),
                reagente.getStatus(),
                FabricanteMapper.toDTO(reagente.getFabricante()),
                LocalizacaoEstoqueMapper.toDTO(reagente.getLocalizacaoEstoque())
        );
    }

    public static Reagente toEntity(ReagenteDTO dto) {
        if (dto == null) return null;
        Reagente reagente = new Reagente();
        reagente.setId(dto.id());
        reagente.setNome(dto.nome());
        reagente.setCodigoSku(dto.codigoSku());
        reagente.setLote(dto.lote());
        reagente.setDataValidade(dto.dataValidade());
        reagente.setDataRecebimento(dto.dataRecebimento());
        reagente.setQuantidadeEmEstoque(dto.quantidadeEmEstoque());
        reagente.setStatus(dto.status());
        reagente.setFabricante(FabricanteMapper.toEntity(dto.fabricante()));
        reagente.setLocalizacaoEstoque(LocalizacaoEstoqueMapper.toEntity(dto.localizacaoEstoque()));
        return reagente;
    }
}
