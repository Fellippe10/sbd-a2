package com.xptotec.reservas.domain.model;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;
public class Cliente {
    private final UUID id;
    private String nome;
    private String email;
    private String telefone;
    private Dinheiro creditoDisponivel;
    public Cliente(UUID id, String nome, String email, String telefone, Dinheiro creditoDisponivel) {
        if (id == null) throw new IllegalArgumentException("ID do cliente é obrigatório.");
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome do cliente é obrigatório.");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("Email do cliente é obrigatório.");
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.creditoDisponivel = creditoDisponivel != null ? creditoDisponivel : Dinheiro.ZERO;
    }
    public void adicionarCredito(Dinheiro valor) {
        if (valor == null || valor.isMenorQue(Dinheiro.ZERO)) {
            throw new IllegalArgumentException("Valor de crédito deve ser positivo.");
        }
        this.creditoDisponivel = this.creditoDisponivel.somar(valor);
    }
    public void debitarCredito(Dinheiro valor) {
        if (valor.isMaiorQue(this.creditoDisponivel)) {
            throw new IllegalArgumentException("Saldo de crédito insuficiente.");
        }
        this.creditoDisponivel = this.creditoDisponivel.subtrair(valor);
    }
    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public Dinheiro getCreditoDisponivel() { return creditoDisponivel; }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id.equals(cliente.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public String toString() {
        return "Cliente{id=" + id + ", nome='" + nome + "', credito=" + creditoDisponivel + '}';
    }
}
