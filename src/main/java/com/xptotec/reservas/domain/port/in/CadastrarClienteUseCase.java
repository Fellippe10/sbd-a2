package com.xptotec.reservas.domain.port.in;

import com.xptotec.reservas.domain.model.Cliente;

public interface CadastrarClienteUseCase {
    Cliente cadastrar(CadastrarClienteCommand command);
}
