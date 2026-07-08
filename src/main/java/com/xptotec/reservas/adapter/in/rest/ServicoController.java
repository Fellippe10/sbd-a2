package com.xptotec.reservas.adapter.in.rest;

import com.xptotec.reservas.adapter.in.rest.dto.ProfissionalResponse;
import com.xptotec.reservas.domain.port.in.ListarProfissionaisPorServicoUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    private final ListarProfissionaisPorServicoUseCase listarProfissionaisPorServicoUseCase;

    public ServicoController(ListarProfissionaisPorServicoUseCase listarProfissionaisPorServicoUseCase) {
        this.listarProfissionaisPorServicoUseCase = listarProfissionaisPorServicoUseCase;
    }

    @GetMapping("/{id}/profissionais")
    public ResponseEntity<List<ProfissionalResponse>> listarProfissionais(@PathVariable UUID id) {
        List<ProfissionalResponse> response = listarProfissionaisPorServicoUseCase.listarProfissionaisPorServico(id).stream()
                .map(ProfissionalResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(response);
    }
}
