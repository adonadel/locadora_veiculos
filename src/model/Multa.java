package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Multa {
    private long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDate dataEmissaoMulta;
    private LocalDate dataVencimentoMulta;
    private String infracao;
    private Veiculo veiculo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDataEmissaoMulta() {
        return dataEmissaoMulta;
    }

    public void setDataEmissaoMulta(LocalDate dataEmissaoMulta) {
        this.dataEmissaoMulta = dataEmissaoMulta;
    }

    public LocalDate getDataVencimentoMulta() {
        return dataVencimentoMulta;
    }

    public void setDataVencimentoMulta(LocalDate dataVencimentoMulta) {
        this.dataVencimentoMulta = dataVencimentoMulta;
    }

    public String getInfracao() {
        return infracao;
    }

    public void setInfracao(String infracao) {
        this.infracao = infracao;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}
