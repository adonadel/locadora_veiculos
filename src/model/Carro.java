package model;

public class Carro extends Veiculo {

    private TipoVeiculo tipo;

    public void Carro(){
        this.setTipo(TipoVeiculo.CARRO);
    }

    @Override
    public TipoVeiculo getTipo() {
        return tipo;
    }

    @Override
    public void setTipo(TipoVeiculo tipo) {
        this.tipo = tipo;
    }
}
