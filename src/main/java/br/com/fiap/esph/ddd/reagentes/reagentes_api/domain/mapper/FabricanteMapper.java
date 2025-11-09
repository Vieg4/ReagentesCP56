package br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.mapper;

import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.dto.FabricanteDTO;
import br.com.fiap.esph.ddd.reagentes.reagentes_api.domain.model.Fabricante;

public class FabricanteMapper {

    public static FabricanteDTO toDTO(Fabricante fabricante) {
        if (fabricante == null) return null;
        return new FabricanteDTO(
                fabricante.getId(),
                fabricante.getNomeOficial(),
                fabricante.getNomeFantasia(),
                fabricante.getCnpj(),
                fabricante.getPaisOrigem()
        );
    }

    public static Fabricante toEntity(FabricanteDTO dto) {
        if (dto == null) return null;
        Fabricante fabricante = new Fabricante();
        fabricante.setId(dto.id());
        fabricante.setNomeOficial(dto.nomeOficial());
        fabricante.setNomeFantasia(dto.nomeFantasia());
        fabricante.setCnpj(dto.cnpj());
        fabricante.setPaisOrigem(dto.paisOrigem());
        return fabricante;
    }
}
