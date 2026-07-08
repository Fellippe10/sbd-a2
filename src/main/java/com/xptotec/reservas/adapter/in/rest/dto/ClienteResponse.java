package com.xptotec.reservas.adapter.in.rest.dto;

import com.xptotec.reservas.domain.model.Cliente;
import java.math.BigDecimal;
import java.util.UUID;

public record ClienteResponse(
        UUID id,
        String nome,
        String email,
        String telefone,
        BigDecimal creditoDisponivel
) {
    public static ClienteResponse fromDomain(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getCreditoDisponivel().getValor()
        );
    }
}
