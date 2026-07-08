package com.xptotec.reservas.domain.port.in;

import com.xptotec.reservas.domain.model.Profissional;
import java.util.List;

public interface ListarProfissionaisUseCase {
    List<Profissional> listarTodos();
}
