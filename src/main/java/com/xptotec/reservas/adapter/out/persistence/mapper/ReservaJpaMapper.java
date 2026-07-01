package com.xptotec.reservas.adapter.out.persistence.mapper;
import com.xptotec.reservas.adapter.out.persistence.entity.ClienteJpaEntity;
import com.xptotec.reservas.adapter.out.persistence.entity.ProfissionalJpaEntity;
import com.xptotec.reservas.adapter.out.persistence.entity.ReservaJpaEntity;
import com.xptotec.reservas.adapter.out.persistence.entity.ServicoJpaEntity;
import com.xptotec.reservas.domain.model.Cliente;
import com.xptotec.reservas.domain.model.Dinheiro;
import com.xptotec.reservas.domain.model.Especialidade;
import com.xptotec.reservas.domain.model.Profissional;
import com.xptotec.reservas.domain.model.Reserva;
import com.xptotec.reservas.domain.model.Servico;
import com.xptotec.reservas.domain.model.StatusReserva;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
public class ReservaJpaMapper {
    public Cliente toDomain(ClienteJpaEntity jpa) {
        return new Cliente(
                jpa.getId(),
                jpa.getNome(),
                jpa.getEmail(),
                jpa.getTelefone(),
                Dinheiro.of(jpa.getCreditoDisponivel())
        );
    }
    public ClienteJpaEntity toJpa(Cliente domain) {
        return new ClienteJpaEntity(
                domain.getId(),
                domain.getNome(),
                domain.getEmail(),
                domain.getTelefone(),
                domain.getCreditoDisponivel().getValor()
        );
    }
    public Profissional toDomain(ProfissionalJpaEntity jpa) {
        Set<Especialidade> especialidades = Arrays.stream(jpa.getEspecialidades().split(","))
                .map(String::trim)
                .map(Especialidade::valueOf)
                .collect(Collectors.toSet());
        return new Profissional(jpa.getId(), jpa.getNome(), especialidades);
    }
    public ProfissionalJpaEntity toJpa(Profissional domain) {
        String csv = domain.getEspecialidades().stream()
                .map(Especialidade::name)
                .collect(Collectors.joining(","));
        return new ProfissionalJpaEntity(domain.getId(), domain.getNome(), csv);
    }
    public Servico toDomain(ServicoJpaEntity jpa) {
        return new Servico(
                jpa.getId(),
                jpa.getNome(),
                Especialidade.valueOf(jpa.getEspecialidade()),
                Duration.ofMinutes(jpa.getDuracaoMinutos()),
                Dinheiro.of(jpa.getPreco())
        );
    }
    public ServicoJpaEntity toJpa(Servico domain) {
        return new ServicoJpaEntity(
                domain.getId(),
                domain.getNome(),
                domain.getEspecialidade().name(),
                (int) domain.getDuracao().toMinutes(),
                domain.getPreco().getValor()
        );
    }
    public Reserva toDomain(ReservaJpaEntity jpa) {
        Cliente cliente = toDomain(jpa.getCliente());
        Profissional profissional = jpa.getProfissional() != null ? toDomain(jpa.getProfissional()) : null;
        List<Servico> servicos = jpa.getServicos().stream().map(this::toDomain).toList();
        return new Reserva(
                jpa.getId(),
                cliente,
                profissional,
                servicos,
                jpa.getHorarioInicio(),
                jpa.getHorarioFim(),
                Dinheiro.of(jpa.getValorTotal()),
                StatusReserva.valueOf(jpa.getStatus())
        );
    }
    public ReservaJpaEntity toJpa(Reserva domain) {
        ReservaJpaEntity jpa = new ReservaJpaEntity();
        jpa.setId(domain.getId());
        jpa.setCliente(toJpa(domain.getCliente()));
        jpa.setProfissional(domain.getProfissional() != null ? toJpa(domain.getProfissional()) : null);
        jpa.setServicos(domain.getServicos().stream().map(this::toJpa).toList());
        jpa.setHorarioInicio(domain.getHorarioInicio());
        jpa.setHorarioFim(domain.getHorarioFim());
        jpa.setValorTotal(domain.getValorTotal().getValor());
        jpa.setStatus(domain.getStatus().name());
        return jpa;
    }
}
