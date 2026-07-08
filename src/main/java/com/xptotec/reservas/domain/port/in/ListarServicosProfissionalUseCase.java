package com.xptotec.reservas.domain.port.in;

import com.xptotec.reservas.domain.model.Servico;
import java.util.List;
import java.util.UUID;

public interface ListarServicosProfissionalUseCase {
    List<Servico> listarServicosPorProfissional(UUID profissionalId);
}
