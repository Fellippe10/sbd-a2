package com.xptotec.reservas.config;
import com.xptotec.reservas.adapter.out.persistence.entity.ClienteJpaEntity;
import com.xptotec.reservas.adapter.out.persistence.entity.ProfissionalJpaEntity;
import com.xptotec.reservas.adapter.out.persistence.entity.ServicoJpaEntity;
import com.xptotec.reservas.adapter.out.persistence.repository.ClienteJpaRepository;
import com.xptotec.reservas.adapter.out.persistence.repository.ProfissionalJpaRepository;
import com.xptotec.reservas.adapter.out.persistence.repository.ServicoJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
@Configuration
public class DataInitializer {
    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);
    @Bean
    CommandLineRunner initDatabase(ClienteJpaRepository clienteRepo,
                                   ProfissionalJpaRepository profissionalRepo,
                                   ServicoJpaRepository servicoRepo) {
        return args -> {
            log.info("Iniciando carga de dados iniciais via ORM...");
            clienteRepo.saveAll(List.of(
                    new ClienteJpaEntity(
                            UUID.fromString("550e8400-e29b-41d4-a716-446655440001"),
                            "Maria Silva", "maria.silva@email.com", "11999990001",
                            BigDecimal.ZERO),
                    new ClienteJpaEntity(
                            UUID.fromString("550e8400-e29b-41d4-a716-446655440002"),
                            "João Santos", "joao.santos@email.com", "11999990002",
                            new BigDecimal("50.00")),
                    new ClienteJpaEntity(
                            UUID.fromString("550e8400-e29b-41d4-a716-446655440003"),
                            "Ana Oliveira", "ana.oliveira@email.com", "11999990003",
                            BigDecimal.ZERO)
            ));
            log.info("  → {} clientes inseridos.", clienteRepo.count());
            profissionalRepo.saveAll(List.of(
                    new ProfissionalJpaEntity(
                            UUID.fromString("550e8400-e29b-41d4-a716-446655440010"),
                            "Ana Costa", "CABELO,ESTETICA"),
                    new ProfissionalJpaEntity(
                            UUID.fromString("550e8400-e29b-41d4-a716-446655440011"),
                            "Carlos Lima", "UNHA,DEPILACAO"),
                    new ProfissionalJpaEntity(
                            UUID.fromString("550e8400-e29b-41d4-a716-446655440012"),
                            "Beatriz Mendes", "MAQUIAGEM,MASSAGEM")
            ));
            log.info("  → {} profissionais inseridos.", profissionalRepo.count());
            servicoRepo.saveAll(List.of(
                    new ServicoJpaEntity(
                            UUID.fromString("550e8400-e29b-41d4-a716-446655440100"),
                            "Corte Feminino", "CABELO", 60, new BigDecimal("120.00")),
                    new ServicoJpaEntity(
                            UUID.fromString("550e8400-e29b-41d4-a716-446655440101"),
                            "Escova Progressiva", "CABELO", 60, new BigDecimal("130.00")),
                    new ServicoJpaEntity(
                            UUID.fromString("550e8400-e29b-41d4-a716-446655440102"),
                            "Manicure Gel", "UNHA", 45, new BigDecimal("80.00")),
                    new ServicoJpaEntity(
                            UUID.fromString("550e8400-e29b-41d4-a716-446655440103"),
                            "Pedicure Completa", "UNHA", 50, new BigDecimal("90.00")),
                    new ServicoJpaEntity(
                            UUID.fromString("550e8400-e29b-41d4-a716-446655440104"),
                            "Limpeza de Pele", "ESTETICA", 90, new BigDecimal("200.00")),
                    new ServicoJpaEntity(
                            UUID.fromString("550e8400-e29b-41d4-a716-446655440105"),
                            "Maquiagem Noiva", "MAQUIAGEM", 120, new BigDecimal("350.00")),
                    new ServicoJpaEntity(
                            UUID.fromString("550e8400-e29b-41d4-a716-446655440106"),
                            "Depilação Completa", "DEPILACAO", 60, new BigDecimal("150.00")),
                    new ServicoJpaEntity(
                            UUID.fromString("550e8400-e29b-41d4-a716-446655440107"),
                            "Massagem Relaxante", "MASSAGEM", 60, new BigDecimal("180.00"))
            ));
            log.info("  → {} serviços inseridos.", servicoRepo.count());
            log.info("Carga de dados iniciais concluída com sucesso!");
        };
    }
}
