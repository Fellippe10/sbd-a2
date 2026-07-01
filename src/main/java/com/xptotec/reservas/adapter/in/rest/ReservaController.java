package com.xptotec.reservas.adapter.in.rest;
import com.xptotec.reservas.adapter.in.rest.dto.CriarReservaRequest;
import com.xptotec.reservas.adapter.in.rest.dto.NoShowResponse;
import com.xptotec.reservas.adapter.in.rest.dto.ReservaResponse;
import com.xptotec.reservas.adapter.in.rest.mapper.ReservaRestMapper;
import com.xptotec.reservas.application.ReservaApplicationService;
import com.xptotec.reservas.application.dto.CriarReservaCommand;
import com.xptotec.reservas.domain.model.Reserva;
import com.xptotec.reservas.domain.port.in.RegistrarNoShowUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;
@RestController
@RequestMapping("/api/reservas")
public class ReservaController {
    private final ReservaApplicationService reservaService;
    private final ReservaRestMapper mapper;
    public ReservaController(ReservaApplicationService reservaService,
                             ReservaRestMapper mapper) {
        this.reservaService = reservaService;
        this.mapper = mapper;
    }
    @PostMapping
    public ResponseEntity<ReservaResponse> criarReserva(@Valid @RequestBody CriarReservaRequest request) {
        CriarReservaCommand command = mapper.toCommand(request);
        Reserva reserva = reservaService.executar(command);
        ReservaResponse response = mapper.toResponse(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReservaResponse> buscarReserva(@PathVariable UUID id) {
        Reserva reserva = reservaService.buscarPorId(id);
        ReservaResponse response = mapper.toResponse(reserva);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/{id}/no-show")
    public ResponseEntity<NoShowResponse> registrarNoShow(@PathVariable UUID id) {
        RegistrarNoShowUseCase.NoShowResult result = reservaService.executar(id);
        NoShowResponse response = mapper.toNoShowResponse(result);
        return ResponseEntity.ok(response);
    }
}
