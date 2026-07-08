package com.xptotec.reservas.application;

import com.xptotec.reservas.domain.model.Cliente;
import com.xptotec.reservas.domain.model.Dinheiro;
import com.xptotec.reservas.domain.port.in.CadastrarClienteCommand;
import com.xptotec.reservas.domain.port.in.CadastrarClienteUseCase;
import com.xptotec.reservas.domain.port.out.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ClienteApplicationService implements CadastrarClienteUseCase {

    private final ClienteRepository clienteRepository;

    public ClienteApplicationService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional
    public Cliente cadastrar(CadastrarClienteCommand command) {
        Cliente novoCliente = new Cliente(
                UUID.randomUUID(),
                command.nome(),
                command.email(),
                command.telefone(),
                Dinheiro.ZERO
        );
        return clienteRepository.salvar(novoCliente);
    }
}
