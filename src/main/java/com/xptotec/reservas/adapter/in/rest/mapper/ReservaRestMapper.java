package com.xptotec.reservas.adapter.in.rest.mapper;
import com.xptotec.reservas.adapter.in.rest.dto.CriarReservaRequest;
import com.xptotec.reservas.adapter.in.rest.dto.NoShowResponse;
import com.xptotec.reservas.adapter.in.rest.dto.ReservaResponse;
import com.xptotec.reservas.application.dto.CriarReservaCommand;
import com.xptotec.reservas.domain.model.Reserva;
import com.xptotec.reservas.domain.model.Servico;
import com.xptotec.reservas.domain.port.in.RegistrarNoShowUseCase;
public class ReservaRestMapper {
    public CriarReservaCommand toCommand(CriarReservaRequest request) {
        return new CriarReservaCommand(
                request.clienteId(),
                request.profissionalId(),
                request.servicoIds(),
                request.horarioInicio()
        );
    }
    public ReservaResponse toResponse(Reserva reserva) {
        return new ReservaResponse(
                reserva.getId(),
                reserva.getCliente().getNome(),
                reserva.getProfissional() != null ? reserva.getProfissional().getNome() : null,
                reserva.getServicos().stream().map(Servico::getNome).toList(),
                reserva.getHorarioInicio(),
                reserva.getHorarioFim(),
                reserva.getValorTotal().getValor(),
                reserva.getStatus().name()
        );
    }
    public NoShowResponse toNoShowResponse(RegistrarNoShowUseCase.NoShowResult result) {
        return new NoShowResponse(
                result.reservaId(),
                result.status(),
                result.valorRetido(),
                result.creditoGerado(),
                result.creditoTotalCliente(),
                result.mensagem()
        );
    }
}
