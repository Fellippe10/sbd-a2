package com.xptotec.reservas.domain.port.in;
import com.xptotec.reservas.application.dto.CriarReservaCommand;
import com.xptotec.reservas.domain.model.Reserva;
public interface CriarReservaUseCase {
    Reserva executar(CriarReservaCommand command);
}
