package com.xptotec.reservas.domain.port.out;
import com.xptotec.reservas.domain.model.Especialidade;
import com.xptotec.reservas.domain.model.Profissional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
public interface ProfissionalRepository {
    Optional<Profissional> buscarPorId(UUID id);
    
    List<Profissional> buscarTodos();
    
    List<Profissional> buscarPorEspecialidade(Especialidade especialidade);
}
