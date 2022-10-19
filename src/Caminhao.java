public class Caminhao extends Veiculo{

    private double metroCubicoBau;
    private boolean arCondicionado;
    private double potenciaArCondicionado;

    public void Caminhao(){
        this.setTipoVeiculo("Caminhao");
    }

    public double getMetroCubicoBau() {
        return metroCubicoBau;
    }

    public void setMetroCubicoBau(double metroCubicoBau) {
        this.metroCubicoBau = metroCubicoBau;
    }

    public boolean isArCondicionado() {
        return arCondicionado;
    }

    public void setArCondicionado(boolean arCondicionado) {
        this.arCondicionado = arCondicionado;
    }

    public double getPotenciaArCondicionado() {
        return potenciaArCondicionado;
    }

    public void setPotenciaArCondicionado(double potenciaArCondicionado) {
        this.potenciaArCondicionado = potenciaArCondicionado;
    }
}
