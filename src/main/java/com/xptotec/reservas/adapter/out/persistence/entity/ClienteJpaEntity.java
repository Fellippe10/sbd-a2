package com.xptotec.reservas.adapter.out.persistence.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;
@Entity
@Table(name = "clientes")
public class ClienteJpaEntity {
    @Id
    private UUID id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String email;
    private String telefone;
    @Column(name = "credito_disponivel", nullable = false)
    private BigDecimal creditoDisponivel;
    public ClienteJpaEntity() {}
    public ClienteJpaEntity(UUID id, String nome, String email, String telefone, BigDecimal creditoDisponivel) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.creditoDisponivel = creditoDisponivel;
    }
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public BigDecimal getCreditoDisponivel() { return creditoDisponivel; }
    public void setCreditoDisponivel(BigDecimal creditoDisponivel) { this.creditoDisponivel = creditoDisponivel; }
}
