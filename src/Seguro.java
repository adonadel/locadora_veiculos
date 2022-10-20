public class Seguro {
    private Seguradora seguradora;
    private Veiculo veiculo;

    public Seguro(Seguradora seguradora, Veiculo veiculo) {
        this.seguradora = seguradora;
        this.veiculo = veiculo;
    }

    public Seguradora getSeguradora() {
        return seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}
