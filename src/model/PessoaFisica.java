package model;

public class PessoaFisica extends Pessoa{
    private String CPF, CNH;

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
}
