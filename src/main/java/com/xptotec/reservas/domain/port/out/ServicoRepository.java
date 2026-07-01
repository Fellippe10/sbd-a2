package com.xptotec.reservas.domain.port.out;
import com.xptotec.reservas.domain.model.Servico;
import java.util.List;
import java.util.UUID;
public interface ServicoRepository {
    List<Servico> buscarPorIds(List<UUID> ids);
}
