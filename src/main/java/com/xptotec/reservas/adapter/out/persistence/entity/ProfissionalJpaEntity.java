package com.xptotec.reservas.adapter.out.persistence.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
@Entity
@Table(name = "profissionais")
public class ProfissionalJpaEntity {
    @Id
    private UUID id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String especialidades; 
    public ProfissionalJpaEntity() {}
    public ProfissionalJpaEntity(UUID id, String nome, String especialidades) {
        this.id = id;
        this.nome = nome;
        this.especialidades = especialidades;
    }
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEspecialidades() { return especialidades; }
    public void setEspecialidades(String especialidades) { this.especialidades = especialidades; }
}
