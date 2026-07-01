package com.xptotec.reservas.application;
import com.xptotec.reservas.application.dto.CriarReservaCommand;
import com.xptotec.reservas.domain.exception.ReservaException;
import com.xptotec.reservas.domain.model.Cliente;
import com.xptotec.reservas.domain.model.Dinheiro;
import com.xptotec.reservas.domain.model.Profissional;
import com.xptotec.reservas.domain.model.Reserva;
import com.xptotec.reservas.domain.model.Servico;
import com.xptotec.reservas.domain.model.SlotHorario;
import com.xptotec.reservas.domain.port.in.CriarReservaUseCase;
import com.xptotec.reservas.domain.port.in.RegistrarNoShowUseCase;
import com.xptotec.reservas.domain.port.out.ClienteRepository;
import com.xptotec.reservas.domain.port.out.ProfissionalRepository;
import com.xptotec.reservas.domain.port.out.ReservaRepository;
import com.xptotec.reservas.domain.port.out.ServicoRepository;
import java.util.List;
import java.util.UUID;
public class ReservaApplicationService implements CriarReservaUseCase, RegistrarNoShowUseCase {
    private final ReservaRepository reservaRepository;
    private final ClienteRepository clienteRepository;
    private final ProfissionalRepository profissionalRepository;
    private final ServicoRepository servicoRepository;
    public ReservaApplicationService(ReservaRepository reservaRepository,
                                     ClienteRepository clienteRepository,
                                     ProfissionalRepository profissionalRepository,
                                     ServicoRepository servicoRepository) {
        this.reservaRepository = reservaRepository;
        this.clienteRepository = clienteRepository;
        this.profissionalRepository = profissionalRepository;
        this.servicoRepository = servicoRepository;
    }
    @Override
    public Reserva executar(CriarReservaCommand command) {
        Cliente cliente = clienteRepository.buscarPorId(command.clienteId())
                .orElseThrow(() -> new ReservaException("Cliente não encontrado: " + command.clienteId()));
        Profissional profissional = null;
        if (command.profissionalId() != null) {
            profissional = profissionalRepository.buscarPorId(command.profissionalId())
                    .orElseThrow(() -> new ReservaException("Profissional não encontrado: " + command.profissionalId()));
        }
        List<Servico> servicos = servicoRepository.buscarPorIds(command.servicoIds());
        if (servicos.size() != command.servicoIds().size()) {
            throw new ReservaException("Um ou mais serviços não foram encontrados.");
        }
        Reserva reserva = new Reserva(
                UUID.randomUUID(),
                cliente,
                profissional,
                servicos,
                command.horarioInicio()
        );
        if (profissional != null) {
            List<SlotHorario> agendaOcupada = reservaRepository.buscarSlotsPorProfissionalEPeriodo(
                    profissional.getId(),
                    command.horarioInicio(),
                    reserva.getHorarioFim()
            );
            reserva.validarDisponibilidade(agendaOcupada);
        }
        reserva.confirmar();
        return reservaRepository.salvar(reserva);
    }
    @Override
    public NoShowResult executar(UUID reservaId) {
        Reserva reserva = reservaRepository.buscarPorId(reservaId)
                .orElseThrow(() -> new ReservaException("Reserva não encontrada: " + reservaId));
        Dinheiro creditoGerado = reserva.registrarNoShow();
        Dinheiro valorRetido = reserva.getValorTotal().percentual(50);
        reservaRepository.salvar(reserva);
        clienteRepository.salvar(reserva.getCliente());
        return new NoShowResult(
                reserva.getId(),
                reserva.getStatus().name(),
                valorRetido.getValor(),
                creditoGerado.getValor(),
                reserva.getCliente().getCreditoDisponivel().getValor(),
                String.format("No-show registrado. 50%% retido (%s) e 50%% convertido em crédito (%s).",
                        valorRetido, creditoGerado)
        );
    }
    public Reserva buscarPorId(UUID id) {
        return reservaRepository.buscarPorId(id)
                .orElseThrow(() -> new ReservaException("Reserva não encontrada: " + id));
    }
}
