package com.xptotec.reservas.adapter.out.persistence.adapter;
import com.xptotec.reservas.adapter.out.persistence.entity.ClienteJpaEntity;
import com.xptotec.reservas.adapter.out.persistence.mapper.ReservaJpaMapper;
import com.xptotec.reservas.adapter.out.persistence.repository.ClienteJpaRepository;
import com.xptotec.reservas.domain.model.Cliente;
import com.xptotec.reservas.domain.port.out.ClienteRepository;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.UUID;
@Component
public class ClienteRepositoryImpl implements ClienteRepository {
    private final ClienteJpaRepository jpaRepository;
    private final ReservaJpaMapper mapper;
    public ClienteRepositoryImpl(ClienteJpaRepository jpaRepository, ReservaJpaMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }
    @Override
    public Optional<Cliente> buscarPorId(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }
    @Override
    public Cliente salvar(Cliente cliente) {
        ClienteJpaEntity jpaEntity = mapper.toJpa(cliente);
        ClienteJpaEntity saved = jpaRepository.save(jpaEntity);
        return mapper.toDomain(saved);
    }
}
