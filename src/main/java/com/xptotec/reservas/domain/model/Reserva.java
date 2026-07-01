package com.xptotec.reservas.domain.model;
import com.xptotec.reservas.domain.exception.HorarioIndisponivelException;
import com.xptotec.reservas.domain.exception.ReservaException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
public class Reserva {
    private final UUID id;
    private final Cliente cliente;
    private Profissional profissional; 
    private final List<Servico> servicos;
    private LocalDateTime horarioInicio;
    private LocalDateTime horarioFim;
    private Dinheiro valorTotal;
    private StatusReserva status;
    public Reserva(UUID id, Cliente cliente, Profissional profissional,
                   List<Servico> servicos, LocalDateTime horarioInicio) {
        validarCriacao(id, cliente, servicos, horarioInicio);
        this.id = id;
        this.cliente = cliente;
        this.profissional = profissional;
        this.servicos = new ArrayList<>(servicos);
        this.horarioInicio = horarioInicio;
        this.status = StatusReserva.PENDENTE;
        this.valorTotal = calcularValorTotal();
        this.horarioFim = calcularHorarioFim();
    }
    public Reserva(UUID id, Cliente cliente, Profissional profissional,
                   List<Servico> servicos, LocalDateTime horarioInicio,
                   LocalDateTime horarioFim, Dinheiro valorTotal,
                   StatusReserva status) {
        this.id = id;
        this.cliente = cliente;
        this.profissional = profissional;
        this.servicos = new ArrayList<>(servicos);
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.valorTotal = valorTotal;
        this.status = status;
    }
    public void confirmar() {
        if (this.status != StatusReserva.PENDENTE) {
            throw new ReservaException("Apenas reservas pendentes podem ser confirmadas. Status atual: " + this.status);
        }
        this.status = StatusReserva.CONFIRMADA;
    }
    public Dinheiro registrarNoShow() {
        if (this.status != StatusReserva.CONFIRMADA) {
            throw new ReservaException(
                    "No-show só pode ser registrado para reservas confirmadas. Status atual: " + this.status);
        }
        Dinheiro valorRetido = this.valorTotal.percentual(50);
        Dinheiro creditoGerado = this.valorTotal.percentual(50);
        this.cliente.adicionarCredito(creditoGerado);
        this.status = StatusReserva.NO_SHOW;
        return creditoGerado;
    }
    public void concluir() {
        if (this.status != StatusReserva.CONFIRMADA) {
            throw new ReservaException("Apenas reservas confirmadas podem ser concluídas.");
        }
        this.status = StatusReserva.CONCLUIDA;
    }
    public void cancelar() {
        if (this.status == StatusReserva.CONCLUIDA || this.status == StatusReserva.NO_SHOW) {
            throw new ReservaException("Não é possível cancelar uma reserva já concluída ou com no-show.");
        }
        this.status = StatusReserva.CANCELADA;
    }
    public void validarDisponibilidade(List<SlotHorario> agendaOcupada) {
        SlotHorario slotReserva = new SlotHorario(this.horarioInicio, this.horarioFim);
        boolean conflito = agendaOcupada.stream().anyMatch(slotReserva::conflitaCom);
        if (conflito) {
            throw new HorarioIndisponivelException(
                    "O horário " + slotReserva + " conflita com outra reserva existente na agenda.");
        }
    }
    public boolean estaDentroToleranciaAtraso(LocalDateTime horarioChegada) {
        LocalDateTime limiteTolerancia = this.horarioInicio.plusMinutes(15);
        return !horarioChegada.isAfter(limiteTolerancia);
    }
    private Dinheiro calcularValorTotal() {
        return servicos.stream()
                .map(Servico::getPreco)
                .reduce(Dinheiro.ZERO, Dinheiro::somar);
    }
    private LocalDateTime calcularHorarioFim() {
        Duration duracaoTotal = servicos.stream()
                .map(Servico::getDuracao)
                .reduce(Duration.ZERO, Duration::plus);
        return this.horarioInicio.plus(duracaoTotal);
    }
    private void validarCriacao(UUID id, Cliente cliente, List<Servico> servicos, LocalDateTime horarioInicio) {
        if (id == null) throw new ReservaException("ID da reserva é obrigatório.");
        if (cliente == null) throw new ReservaException("Cliente é obrigatório para criar uma reserva.");
        if (servicos == null || servicos.isEmpty()) throw new ReservaException("Pelo menos um serviço deve ser selecionado.");
        if (horarioInicio == null) throw new ReservaException("Horário de início é obrigatório.");
        if (horarioInicio.isBefore(LocalDateTime.now())) {
            throw new ReservaException("Não é possível criar reserva para um horário no passado.");
        }
    }
    public UUID getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public Profissional getProfissional() { return profissional; }
    public List<Servico> getServicos() { return Collections.unmodifiableList(servicos); }
    public LocalDateTime getHorarioInicio() { return horarioInicio; }
    public LocalDateTime getHorarioFim() { return horarioFim; }
    public Dinheiro getValorTotal() { return valorTotal; }
    public StatusReserva getStatus() { return status; }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return id.equals(reserva.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public String toString() {
        return "Reserva{id=" + id + ", status=" + status + ", valor=" + valorTotal + '}';
    }
}
