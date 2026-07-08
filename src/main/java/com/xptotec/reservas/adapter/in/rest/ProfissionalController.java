package com.xptotec.reservas.adapter.in.rest;

import com.xptotec.reservas.adapter.in.rest.dto.ProfissionalResponse;
import com.xptotec.reservas.adapter.in.rest.dto.ServicoResponse;
import com.xptotec.reservas.domain.port.in.ListarProfissionaisUseCase;
import com.xptotec.reservas.domain.port.in.ListarServicosProfissionalUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/profissionais")
public class ProfissionalController {

    private final ListarProfissionaisUseCase listarProfissionaisUseCase;
    private final ListarServicosProfissionalUseCase listarServicosProfissionalUseCase;

    public ProfissionalController(ListarProfissionaisUseCase listarProfissionaisUseCase,
                                  ListarServicosProfissionalUseCase listarServicosProfissionalUseCase) {
        this.listarProfissionaisUseCase = listarProfissionaisUseCase;
        this.listarServicosProfissionalUseCase = listarServicosProfissionalUseCase;
    }

    @GetMapping
    public ResponseEntity<List<ProfissionalResponse>> listarTodos() {
        List<ProfissionalResponse> response = listarProfissionaisUseCase.listarTodos().stream()
                .map(ProfissionalResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/servicos")
    public ResponseEntity<List<ServicoResponse>> listarServicos(@PathVariable UUID id) {
        List<ServicoResponse> response = listarServicosProfissionalUseCase.listarServicosPorProfissional(id).stream()
                .map(ServicoResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(response);
    }
}
