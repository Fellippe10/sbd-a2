package com.xptotec.reservas.domain.port.out;
import com.xptotec.reservas.domain.model.Cliente;
import java.util.Optional;
import java.util.UUID;
public interface ClienteRepository {
    Optional<Cliente> buscarPorId(UUID id);
    Cliente salvar(Cliente cliente);
}
