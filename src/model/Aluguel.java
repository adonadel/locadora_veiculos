package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Aluguel {
    private long id;
    private BigDecimal valorEstimado;
    private BigDecimal valorFinal;
    private LocalDate dataAluguel;
    private LocalDate dataDevolucao;
    private long hodometroInicial;
    private long hodometroFinal;
    private StatusAluguel status;
    private Pessoa pessoa;
    private Veiculo veiculo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getValorEstimado() {
        return valorEstimado;
    }

    public void setValorEstimado(BigDecimal valorEstimado) {
        this.valorEstimado = valorEstimado;
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(BigDecimal valorFinal) {
        this.valorFinal = valorFinal;
    }

    public LocalDate getDataAluguel() {
        System.out.println(dataAluguel);
        return dataAluguel;
    }

    public void setDataAluguel(LocalDate dataAluguel) {
        System.out.println(this.dataAluguel);
        this.dataAluguel = dataAluguel;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public long getHodometroInicial() {
        return hodometroInicial;
    }

    public void setHodometroInicial(long hodometroInicial) {
        this.hodometroInicial = hodometroInicial;
    }

    public long getHodometroFinal() {
        return hodometroFinal;
    }

    public void setHodometroFinal(long hodometroFinal) {
        this.hodometroFinal = hodometroFinal;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public StatusAluguel getStatus() {
        return status;
    }

    public void setStatus(StatusAluguel status) {
        this.status = status;
    }

    public void alugar() {
        
    }

    public void devolver() {

    }
}

