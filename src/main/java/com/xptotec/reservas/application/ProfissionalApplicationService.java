package com.xptotec.reservas.application;

import com.xptotec.reservas.domain.exception.RecursoNaoEncontradoException;
import com.xptotec.reservas.domain.model.Profissional;
import com.xptotec.reservas.domain.model.Servico;
import com.xptotec.reservas.domain.port.in.ListarProfissionaisUseCase;
import com.xptotec.reservas.domain.port.in.ListarServicosProfissionalUseCase;
import com.xptotec.reservas.domain.port.out.ProfissionalRepository;
import com.xptotec.reservas.domain.port.out.ServicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class ProfissionalApplicationService implements ListarProfissionaisUseCase, ListarServicosProfissionalUseCase {

    private final ProfissionalRepository profissionalRepository;
    private final ServicoRepository servicoRepository;

    public ProfissionalApplicationService(ProfissionalRepository profissionalRepository, ServicoRepository servicoRepository) {
        this.profissionalRepository = profissionalRepository;
        this.servicoRepository = servicoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Profissional> listarTodos() {
        return profissionalRepository.buscarTodos();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Servico> listarServicosPorProfissional(UUID profissionalId) {
        Profissional profissional = profissionalRepository.buscarPorId(profissionalId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Profissional não encontrado"));

        if (profissional.getEspecialidades().isEmpty()) {
            return Collections.emptyList();
        }

        return servicoRepository.buscarPorEspecialidades(profissional.getEspecialidades());
    }
}
