package com.xptotec.reservas.adapter.in.rest;

import com.xptotec.reservas.adapter.in.rest.dto.CadastrarClienteRequest;
import com.xptotec.reservas.adapter.in.rest.dto.ClienteResponse;
import com.xptotec.reservas.domain.model.Cliente;
import com.xptotec.reservas.domain.port.in.CadastrarClienteCommand;
import com.xptotec.reservas.domain.port.in.CadastrarClienteUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final CadastrarClienteUseCase cadastrarClienteUseCase;

    public ClienteController(CadastrarClienteUseCase cadastrarClienteUseCase) {
        this.cadastrarClienteUseCase = cadastrarClienteUseCase;
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> cadastrar(@RequestBody @Valid CadastrarClienteRequest request) {
        CadastrarClienteCommand command = new CadastrarClienteCommand(
                request.nome(),
                request.email(),
                request.telefone()
        );

        Cliente cliente = cadastrarClienteUseCase.cadastrar(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(ClienteResponse.fromDomain(cliente));
    }
}
