package com.xptotec.reservas.adapter.in.rest.dto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
public record ReservaResponse(
        UUID id,
        String clienteNome,
        String profissionalNome,
        List<String> servicos,
        LocalDateTime horarioInicio,
        LocalDateTime horarioFim,
        BigDecimal valorTotal,
        String status
) {}
