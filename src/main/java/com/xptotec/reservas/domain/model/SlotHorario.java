package com.xptotec.reservas.domain.model;
import java.time.LocalDateTime;
import java.util.Objects;
public final class SlotHorario {
    private final LocalDateTime inicio;
    private final LocalDateTime fim;
    public SlotHorario(LocalDateTime inicio, LocalDateTime fim) {
        if (inicio == null || fim == null) {
            throw new IllegalArgumentException("Início e fim do slot são obrigatórios.");
        }
        if (!fim.isAfter(inicio)) {
            throw new IllegalArgumentException("O fim do slot deve ser posterior ao início.");
        }
        this.inicio = inicio;
        this.fim = fim;
    }
    public boolean conflitaCom(SlotHorario outro) {
        return this.inicio.isBefore(outro.fim) && this.fim.isAfter(outro.inicio);
    }
    public LocalDateTime getInicio() {
        return inicio;
    }
    public LocalDateTime getFim() {
        return fim;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SlotHorario that = (SlotHorario) o;
        return inicio.equals(that.inicio) && fim.equals(that.fim);
    }
    @Override
    public int hashCode() {
        return Objects.hash(inicio, fim);
    }
    @Override
    public String toString() {
        return inicio + " → " + fim;
    }
}
