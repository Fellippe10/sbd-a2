package com.xptotec.reservas.adapter.in.rest.dto;

import com.xptotec.reservas.domain.model.Servico;
import java.math.BigDecimal;
import java.util.UUID;

public record ServicoResponse(
        UUID id,
        String nome,
        String especialidade,
        long duracaoMinutos,
        BigDecimal preco
) {
    public static ServicoResponse fromDomain(Servico servico) {
        return new ServicoResponse(
                servico.getId(),
                servico.getNome(),
                servico.getEspecialidade().name(),
                servico.getDuracao().toMinutes(),
                servico.getPreco().getValor()
        );
    }
}
