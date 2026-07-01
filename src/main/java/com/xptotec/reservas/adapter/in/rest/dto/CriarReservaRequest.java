package com.xptotec.reservas.adapter.in.rest.dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
public record CriarReservaRequest(
        @NotNull(message = "clienteId é obrigatório")
        UUID clienteId,
        UUID profissionalId,   
        @NotEmpty(message = "Pelo menos um serviço deve ser informado")
        List<UUID> servicoIds,
        @NotNull(message = "horarioInicio é obrigatório")
        LocalDateTime horarioInicio
) {}
