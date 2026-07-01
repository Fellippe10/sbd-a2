package com.xptotec.reservas.adapter.out.persistence.repository;
import com.xptotec.reservas.adapter.out.persistence.entity.ClienteJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
public interface ClienteJpaRepository extends JpaRepository<ClienteJpaEntity, UUID> {
}
