package com.xptotec.reservas.domain.model;
import java.time.Duration;
import java.util.Objects;
import java.util.UUID;
public class Servico {
    private final UUID id;
    private String nome;
    private Especialidade especialidade;
    private Duration duracao;
    private Dinheiro preco;
    public Servico(UUID id, String nome, Especialidade especialidade, Duration duracao, Dinheiro preco) {
        if (id == null) throw new IllegalArgumentException("ID do serviço é obrigatório.");
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome do serviço é obrigatório.");
        if (especialidade == null) throw new IllegalArgumentException("Especialidade do serviço é obrigatória.");
        if (duracao == null || duracao.isZero() || duracao.isNegative()) {
            throw new IllegalArgumentException("Duração do serviço deve ser positiva.");
        }
        if (preco == null) throw new IllegalArgumentException("Preço do serviço é obrigatório.");
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
        this.duracao = duracao;
        this.preco = preco;
    }
    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public Especialidade getEspecialidade() { return especialidade; }
    public Duration getDuracao() { return duracao; }
    public Dinheiro getPreco() { return preco; }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Servico servico = (Servico) o;
        return id.equals(servico.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public String toString() {
        return "Servico{id=" + id + ", nome='" + nome + "', preco=" + preco + ", duracao=" + duracao.toMinutes() + "min}";
    }
}
