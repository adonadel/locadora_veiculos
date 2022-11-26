package model;

public class Caminhao extends Veiculo {

    private TipoVeiculo tipo;

    public Caminhao(){
        this.setTipo(TipoVeiculo.CAMINHAO);
    }

    public TipoVeiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVeiculo tipo) {
        this.tipo = tipo;
    }
}
