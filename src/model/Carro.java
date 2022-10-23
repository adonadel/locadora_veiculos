package model;

public class Carro extends Veiculo {

    private int numeroPortas;
    private float litragemPortaMalas;
    private float capacidadeTanque;
    private float kmPorLitro; //média de km por litro
    private int numeroEixos;
    private int numeroRodas;
    private boolean arCondicionado;
    private double potenciaArCondicionado;


    public void Carro(){
        this.setTipo(TipoVeiculo.CARRO);
    }

    public int getNumeroPortas() {
        return numeroPortas;
    }

    public void setNumeroPortas(int numeroPortas) {
        this.numeroPortas = numeroPortas;
    }

    public float getLitragemPortaMalas() {
        return litragemPortaMalas;
    }

    public void setLitragemPortaMalas(float litragemPortaMalas) {
        this.litragemPortaMalas = litragemPortaMalas;
    }

    public float getCapacidadeTanque() {
        return capacidadeTanque;
    }

    public void setCapacidadeTanque(float capacidadeTanque) {
        this.capacidadeTanque = capacidadeTanque;
    }

    public float getKmPorLitro() {
        return kmPorLitro;
    }

    public void setKmPorLitro(float kmPorLitro) {
        this.kmPorLitro = kmPorLitro;
    }

    public int getNumeroEixos() {
        return numeroEixos;
    }

    public void setNumeroEixos(int numeroEixos) {
        this.numeroEixos = numeroEixos;
    }

    public int getNumeroRodas() {
        return numeroRodas;
    }

    public void setNumeroRodas(int numeroRodas) {
        this.numeroRodas = numeroRodas;
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
