package com.xptotec.reservas.adapter.in.rest.dto;

import com.xptotec.reservas.domain.model.Especialidade;
import com.xptotec.reservas.domain.model.Profissional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public record ProfissionalResponse(
        UUID id,
        String nome,
        Set<String> especialidades
) {
    public static ProfissionalResponse fromDomain(Profissional profissional) {
        return new ProfissionalResponse(
                profissional.getId(),
                profissional.getNome(),
                profissional.getEspecialidades().stream().map(Especialidade::name).collect(Collectors.toSet())
        );
    }
}
