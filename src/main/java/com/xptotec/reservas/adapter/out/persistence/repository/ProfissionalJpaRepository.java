package com.xptotec.reservas.adapter.out.persistence.repository;
import com.xptotec.reservas.adapter.out.persistence.entity.ProfissionalJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface ProfissionalJpaRepository extends JpaRepository<ProfissionalJpaEntity, UUID> {
    List<ProfissionalJpaEntity> findAllByEspecialidadesContaining(String especialidade);
}
