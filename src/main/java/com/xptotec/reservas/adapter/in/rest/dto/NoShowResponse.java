package com.xptotec.reservas.adapter.in.rest.dto;
import java.math.BigDecimal;
import java.util.UUID;
public record NoShowResponse(
        UUID reservaId,
        String status,
        BigDecimal valorRetido,
        BigDecimal creditoGerado,
        BigDecimal creditoTotalCliente,
        String mensagem
) {}
