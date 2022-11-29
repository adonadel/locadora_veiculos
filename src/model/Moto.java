package model;

public class Moto extends Veiculo{

    private TipoVeiculo tipo;

    public void Moto(){
        this.setTipo(TipoVeiculo.MOTO);
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
