package com.xptotec.reservas.adapter.out.persistence.adapter;
import com.xptotec.reservas.adapter.out.persistence.mapper.ReservaJpaMapper;
import com.xptotec.reservas.adapter.out.persistence.repository.ServicoJpaRepository;
import com.xptotec.reservas.domain.model.Servico;
import com.xptotec.reservas.domain.port.out.ServicoRepository;
import org.springframework.stereotype.Component;
import com.xptotec.reservas.domain.model.Especialidade;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
@Component
public class ServicoRepositoryImpl implements ServicoRepository {
    private final ServicoJpaRepository jpaRepository;
    private final ReservaJpaMapper mapper;
    public ServicoRepositoryImpl(ServicoJpaRepository jpaRepository, ReservaJpaMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Servico> buscarPorId(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Servico> buscarPorIds(List<UUID> ids) {
        return jpaRepository.findAllByIdIn(ids).stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Servico> buscarPorEspecialidades(Set<Especialidade> especialidades) {
        List<String> especialidadesString = especialidades.stream()
                .map(Enum::name)
                .toList();
        
        return jpaRepository.findAllByEspecialidadeIn(especialidadesString).stream()
                .map(mapper::toDomain)
                .toList();
    }
}
