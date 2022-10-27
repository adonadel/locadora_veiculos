package model;

import model.CategoriaVeiculo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Veiculo{

    private long id;
    private Double peso;
    private LocalDate dataFabricacao;
    private int numeroSerie;
    private BigDecimal valorFipe;
    private BigDecimal valorCompra;
    private int hodometro;
    private String descricao;
    private TipoCombustivel tipoCombustivel;
    private String placa;
    private LocalDate ultimaRevisao;
    private Double pesoSuportado;
    private Double TrocaOleokm; //ultima troca em km (50.000 foi trocado o oleo)
    private CategoriaVeiculo categoria;
    private TipoVeiculo tipo;
    private Modelo modelo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
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

    public BigDecimal getValorFipe() {
        return valorFipe;
    }

    public void setValorFipe(BigDecimal valorFipe) {
        this.valorFipe = valorFipe;
    }

    public BigDecimal getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(BigDecimal valorCompra) {
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

    public TipoCombustivel getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(TipoCombustivel tipoCombustivel) {
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

    public Double getPesoSuportado() {
        return pesoSuportado;
    }

    public void setPesoSuportado(Double pesoSuportado) {
        this.pesoSuportado = pesoSuportado;
    }

    public Double getTrocaOleokm() {
        return TrocaOleokm;
    }

    public void setTrocaOleokm(Double trocaOleokm) {
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

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
}
