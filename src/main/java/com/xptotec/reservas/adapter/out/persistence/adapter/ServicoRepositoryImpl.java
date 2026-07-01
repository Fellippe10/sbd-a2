package com.xptotec.reservas.adapter.out.persistence.adapter;
import com.xptotec.reservas.adapter.out.persistence.mapper.ReservaJpaMapper;
import com.xptotec.reservas.adapter.out.persistence.repository.ServicoJpaRepository;
import com.xptotec.reservas.domain.model.Servico;
import com.xptotec.reservas.domain.port.out.ServicoRepository;
import org.springframework.stereotype.Component;
import java.util.List;
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
    public List<Servico> buscarPorIds(List<UUID> ids) {
        return jpaRepository.findAllByIdIn(ids).stream()
                .map(mapper::toDomain)
                .toList();
    }
}
