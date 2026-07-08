package com.xptotec.reservas.domain.port.in;

public record CadastrarClienteCommand(
        String nome,
        String email,
        String telefone
) {
    public CadastrarClienteCommand {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email é obrigatório.");
        }
    }
}
