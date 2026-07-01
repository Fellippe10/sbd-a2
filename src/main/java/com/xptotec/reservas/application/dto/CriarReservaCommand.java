package com.xptotec.reservas.application.dto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
public record CriarReservaCommand(
        UUID clienteId,
        UUID profissionalId,       
        List<UUID> servicoIds,
        LocalDateTime horarioInicio
) {
    public CriarReservaCommand {
        if (clienteId == null) throw new IllegalArgumentException("clienteId é obrigatório.");
        if (servicoIds == null || servicoIds.isEmpty()) throw new IllegalArgumentException("Pelo menos um serviço deve ser informado.");
        if (horarioInicio == null) throw new IllegalArgumentException("horarioInicio é obrigatório.");
    }
}
