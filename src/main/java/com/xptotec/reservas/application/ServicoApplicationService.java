package com.xptotec.reservas.application;

import com.xptotec.reservas.domain.exception.RecursoNaoEncontradoException;
import com.xptotec.reservas.domain.model.Profissional;
import com.xptotec.reservas.domain.model.Servico;
import com.xptotec.reservas.domain.port.in.ListarProfissionaisPorServicoUseCase;
import com.xptotec.reservas.domain.port.out.ProfissionalRepository;
import com.xptotec.reservas.domain.port.out.ServicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ServicoApplicationService implements ListarProfissionaisPorServicoUseCase {

    private final ServicoRepository servicoRepository;
    private final ProfissionalRepository profissionalRepository;

    public ServicoApplicationService(ServicoRepository servicoRepository, ProfissionalRepository profissionalRepository) {
        this.servicoRepository = servicoRepository;
        this.profissionalRepository = profissionalRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Profissional> listarProfissionaisPorServico(UUID servicoId) {
        Servico servico = servicoRepository.buscarPorId(servicoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Serviço não encontrado"));

        return profissionalRepository.buscarPorEspecialidade(servico.getEspecialidade());
    }
}
