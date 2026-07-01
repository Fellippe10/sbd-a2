package com.xptotec.reservas.adapter.out.persistence.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;
@Entity
@Table(name = "servicos")
public class ServicoJpaEntity {
    @Id
    private UUID id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String especialidade;
    @Column(name = "duracao_minutos", nullable = false)
    private int duracaoMinutos;
    @Column(nullable = false)
    private BigDecimal preco;
    public ServicoJpaEntity() {}
    public ServicoJpaEntity(UUID id, String nome, String especialidade, int duracaoMinutos, BigDecimal preco) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
        this.duracaoMinutos = duracaoMinutos;
        this.preco = preco;
    }
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
    public int getDuracaoMinutos() { return duracaoMinutos; }
    public void setDuracaoMinutos(int duracaoMinutos) { this.duracaoMinutos = duracaoMinutos; }
    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
}
