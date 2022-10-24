package model;

import java.time.LocalDate;

public class PessoaFisica extends Pessoa{
    private String CPF, CNH;
    private LocalDate dataNasc;

    public PessoaFisica() {
        setTipo(TipoPessoa.FISICA);
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getCNH() {
        return CNH;
    }

    public void setCNH(String CNH) {
        this.CNH = CNH;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    @Override
    public String getDocumento() {
        return this.CPF;
    }
}
