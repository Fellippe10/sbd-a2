package com.xptotec.reservas.adapter.out.persistence.adapter;
import com.xptotec.reservas.adapter.out.persistence.mapper.ReservaJpaMapper;
import com.xptotec.reservas.adapter.out.persistence.repository.ProfissionalJpaRepository;
import com.xptotec.reservas.domain.model.Profissional;
import com.xptotec.reservas.domain.port.out.ProfissionalRepository;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.UUID;
@Component
public class ProfissionalRepositoryImpl implements ProfissionalRepository {
    private final ProfissionalJpaRepository jpaRepository;
    private final ReservaJpaMapper mapper;
    public ProfissionalRepositoryImpl(ProfissionalJpaRepository jpaRepository, ReservaJpaMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }
    @Override
    public Optional<Profissional> buscarPorId(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }
}
