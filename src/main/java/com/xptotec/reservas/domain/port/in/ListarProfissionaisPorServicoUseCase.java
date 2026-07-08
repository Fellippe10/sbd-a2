package com.xptotec.reservas.domain.port.in;

import com.xptotec.reservas.domain.model.Profissional;
import java.util.List;
import java.util.UUID;

public interface ListarProfissionaisPorServicoUseCase {
    List<Profissional> listarProfissionaisPorServico(UUID servicoId);
}
