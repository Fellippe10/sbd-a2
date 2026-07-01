package com.xptotec.reservas.domain.port.out;
import com.xptotec.reservas.domain.model.Reserva;
import com.xptotec.reservas.domain.model.SlotHorario;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
public interface ReservaRepository {
    Reserva salvar(Reserva reserva);
    Optional<Reserva> buscarPorId(UUID id);
    List<SlotHorario> buscarSlotsPorProfissionalEPeriodo(UUID profissionalId,
                                                         LocalDateTime inicio,
                                                         LocalDateTime fim);
}
