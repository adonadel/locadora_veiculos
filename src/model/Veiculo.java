package model;

import model.CategoriaVeiculo;

import java.time.LocalDate;

public class Veiculo{

    private long id;
    private float peso;
    private LocalDate dataFabricacao;
    private int numeroSerie;
    private double valorFipe;
    private double valorCompra;
    private int hodometro;
    private String descricao;
    private String tipoCombustivel;
    private String placa;
    private LocalDate ultimaRevisao;
    private double pesoSuportado;
    private float TrocaOleokm; //ultima troca em km (50.000 foi trocado o oleo)
    private CategoriaVeiculo categoria;
    private  TipoVeiculo tipo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(LocalDate dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public int getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(int numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public double getValorFipe() {
        return valorFipe;
    }

    public void setValorFipe(double valorFipe) {
        this.valorFipe = valorFipe;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public int getHodometro() {
        return hodometro;
    }

    public void setHodometro(int hodometro) {
        this.hodometro = hodometro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public LocalDate getUltimaRevisao() {
        return ultimaRevisao;
    }

    public void setUltimaRevisao(LocalDate ultimaRevisao) {
        this.ultimaRevisao = ultimaRevisao;
    }

    public double getPesoSuportado() {
        return pesoSuportado;
    }

    public void setPesoSuportado(double pesoSuportado) {
        this.pesoSuportado = pesoSuportado;
    }

    public float getTrocaOleokm() {
        return TrocaOleokm;
    }

    public void setTrocaOleokm(float trocaOleokm) {
        TrocaOleokm = trocaOleokm;
    }

    public CategoriaVeiculo getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaVeiculo categoria) {
        this.categoria = categoria;
    }

    public TipoVeiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVeiculo tipo) {
        this.tipo = tipo;
    }
}
