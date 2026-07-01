package com.xptotec.reservas.adapter.out.persistence.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "reservas")
public class ReservaJpaEntity {
    @Id
    private UUID id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteJpaEntity cliente;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profissional_id")
    private ProfissionalJpaEntity profissional;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "reserva_servicos",
            joinColumns = @JoinColumn(name = "reserva_id"),
            inverseJoinColumns = @JoinColumn(name = "servico_id")
    )
    private List<ServicoJpaEntity> servicos = new ArrayList<>();
    @Column(name = "horario_inicio", nullable = false)
    private LocalDateTime horarioInicio;
    @Column(name = "horario_fim", nullable = false)
    private LocalDateTime horarioFim;
    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;
    @Column(nullable = false)
    private String status;
    public ReservaJpaEntity() {}
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public ClienteJpaEntity getCliente() { return cliente; }
    public void setCliente(ClienteJpaEntity cliente) { this.cliente = cliente; }
    public ProfissionalJpaEntity getProfissional() { return profissional; }
    public void setProfissional(ProfissionalJpaEntity profissional) { this.profissional = profissional; }
    public List<ServicoJpaEntity> getServicos() { return servicos; }
    public void setServicos(List<ServicoJpaEntity> servicos) { this.servicos = servicos; }
    public LocalDateTime getHorarioInicio() { return horarioInicio; }
    public void setHorarioInicio(LocalDateTime horarioInicio) { this.horarioInicio = horarioInicio; }
    public LocalDateTime getHorarioFim() { return horarioFim; }
    public void setHorarioFim(LocalDateTime horarioFim) { this.horarioFim = horarioFim; }
    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
