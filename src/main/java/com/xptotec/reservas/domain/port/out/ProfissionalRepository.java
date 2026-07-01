package com.xptotec.reservas.domain.port.out;
import com.xptotec.reservas.domain.model.Profissional;
import java.util.Optional;
import java.util.UUID;
public interface ProfissionalRepository {
    Optional<Profissional> buscarPorId(UUID id);
}
