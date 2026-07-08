package com.xptotec.reservas.domain.port.out;
import com.xptotec.reservas.domain.model.Servico;
import com.xptotec.reservas.domain.model.Especialidade;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ServicoRepository {
    Optional<Servico> buscarPorId(UUID id);
    
    List<Servico> buscarPorIds(List<UUID> ids);
    
    List<Servico> buscarPorEspecialidades(Set<Especialidade> especialidades);
}
