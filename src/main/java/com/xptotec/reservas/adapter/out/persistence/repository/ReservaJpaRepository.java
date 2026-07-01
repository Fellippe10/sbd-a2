package com.xptotec.reservas.adapter.out.persistence.repository;
import com.xptotec.reservas.adapter.out.persistence.entity.ReservaJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
public interface ReservaJpaRepository extends JpaRepository<ReservaJpaEntity, UUID> {
    @Query("SELECT r FROM ReservaJpaEntity r " +
           "WHERE r.profissional.id = :profissionalId " +
           "AND r.status NOT IN ('CANCELADA', 'NO_SHOW') " +
           "AND r.horarioInicio < :fim " +
           "AND r.horarioFim > :inicio")
    List<ReservaJpaEntity> findConflitantes(
            @Param("profissionalId") UUID profissionalId,
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim
    );
}
