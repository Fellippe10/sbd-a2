package com.xptotec.reservas.adapter.out.persistence.adapter;
import com.xptotec.reservas.adapter.out.persistence.entity.ReservaJpaEntity;
import com.xptotec.reservas.adapter.out.persistence.mapper.ReservaJpaMapper;
import com.xptotec.reservas.adapter.out.persistence.repository.ReservaJpaRepository;
import com.xptotec.reservas.domain.model.Reserva;
import com.xptotec.reservas.domain.model.SlotHorario;
import com.xptotec.reservas.domain.port.out.ReservaRepository;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Component
public class ReservaRepositoryImpl implements ReservaRepository {
    private final ReservaJpaRepository jpaRepository;
    private final ReservaJpaMapper mapper;
    public ReservaRepositoryImpl(ReservaJpaRepository jpaRepository, ReservaJpaMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }
    @Override
    public Reserva salvar(Reserva reserva) {
        ReservaJpaEntity jpaEntity = mapper.toJpa(reserva);
        ReservaJpaEntity saved = jpaRepository.save(jpaEntity);
        return mapper.toDomain(saved);
    }
    @Override
    public Optional<Reserva> buscarPorId(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }
    @Override
    public List<SlotHorario> buscarSlotsPorProfissionalEPeriodo(UUID profissionalId,
                                                                 LocalDateTime inicio,
                                                                 LocalDateTime fim) {
        List<ReservaJpaEntity> conflitantes = jpaRepository.findConflitantes(profissionalId, inicio, fim);
        return conflitantes.stream()
                .map(r -> new SlotHorario(r.getHorarioInicio(), r.getHorarioFim()))
                .toList();
    }
}
