package model;

public class PessoaJuridica extends Pessoa{
    private String CNPJ;

    public PessoaJuridica() {
        setTipo(TipoPessoa.JURIDICA);
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }
}
