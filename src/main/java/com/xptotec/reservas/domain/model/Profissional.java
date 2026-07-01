package com.xptotec.reservas.domain.model;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
public class Profissional {
    private final UUID id;
    private String nome;
    private Set<Especialidade> especialidades;
    public Profissional(UUID id, String nome, Set<Especialidade> especialidades) {
        if (id == null) throw new IllegalArgumentException("ID do profissional é obrigatório.");
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome do profissional é obrigatório.");
        this.id = id;
        this.nome = nome;
        this.especialidades = especialidades != null ? new HashSet<>(especialidades) : new HashSet<>();
    }
    public boolean possuiEspecialidade(Especialidade especialidade) {
        return this.especialidades.contains(especialidade);
    }
    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public Set<Especialidade> getEspecialidades() { return Collections.unmodifiableSet(especialidades); }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profissional that = (Profissional) o;
        return id.equals(that.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public String toString() {
        return "Profissional{id=" + id + ", nome='" + nome + "', especialidades=" + especialidades + '}';
    }
}
