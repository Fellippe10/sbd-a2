package com.xptotec.reservas.adapter.out.persistence.repository;
import com.xptotec.reservas.adapter.out.persistence.entity.ServicoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface ServicoJpaRepository extends JpaRepository<ServicoJpaEntity, UUID> {
    List<ServicoJpaEntity> findAllByIdIn(List<UUID> ids);
    
    List<ServicoJpaEntity> findAllByEspecialidadeIn(Collection<String> especialidades);
}
