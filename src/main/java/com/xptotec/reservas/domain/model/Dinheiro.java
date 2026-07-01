package com.xptotec.reservas.domain.model;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
public final class Dinheiro {
    public static final Dinheiro ZERO = new Dinheiro(BigDecimal.ZERO);
    private final BigDecimal valor;
    public Dinheiro(BigDecimal valor) {
        if (valor == null) {
            throw new IllegalArgumentException("Valor monetário não pode ser nulo.");
        }
        this.valor = valor.setScale(2, RoundingMode.HALF_UP);
    }
    public static Dinheiro of(double valor) {
        return new Dinheiro(BigDecimal.valueOf(valor));
    }
    public static Dinheiro of(BigDecimal valor) {
        return new Dinheiro(valor);
    }
    public Dinheiro somar(Dinheiro outro) {
        return new Dinheiro(this.valor.add(outro.valor));
    }
    public Dinheiro subtrair(Dinheiro outro) {
        return new Dinheiro(this.valor.subtract(outro.valor));
    }
    public Dinheiro percentual(int porcentagem) {
        BigDecimal fator = BigDecimal.valueOf(porcentagem).divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);
        return new Dinheiro(this.valor.multiply(fator));
    }
    public boolean isMaiorQue(Dinheiro outro) {
        return this.valor.compareTo(outro.valor) > 0;
    }
    public boolean isMenorQue(Dinheiro outro) {
        return this.valor.compareTo(outro.valor) < 0;
    }
    public BigDecimal getValor() {
        return valor;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dinheiro dinheiro = (Dinheiro) o;
        return valor.compareTo(dinheiro.valor) == 0;
    }
    @Override
    public int hashCode() {
        return Objects.hash(valor.stripTrailingZeros());
    }
    @Override
    public String toString() {
        return "R$" + valor.toPlainString();
    }
}
