package com.xptotec.reservas.domain.port.in;
import java.util.UUID;
public interface RegistrarNoShowUseCase {
    NoShowResult executar(UUID reservaId);
    record NoShowResult(
            UUID reservaId,
            String status,
            java.math.BigDecimal valorRetido,
            java.math.BigDecimal creditoGerado,
            java.math.BigDecimal creditoTotalCliente,
            String mensagem
    ) {}
}
