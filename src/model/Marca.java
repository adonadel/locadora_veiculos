package model;

import java.time.LocalDate;

public class Marca {
    private Integer id;
    private String razaoSocial;
    private String logo;
    private String slogan;
    private LocalDate fundacao;
    private String fundador;
    private String sede;
    private Integer empregados;
    private Boolean fabricaBrasil;
    private Boolean capitalAberto;

    public Marca() {
    }

    public Marca(Integer id, String razaoSocial, String logo, String slogan, LocalDate fundacao, String fundador, String sede, Integer empregados, Boolean fabricaBrasil, Boolean capitalAberto) {
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.logo = logo;
        this.slogan = slogan;
        this.fundacao = fundacao;
        this.fundador = fundador;
        this.sede = sede;
        this.empregados = empregados;
        this.fabricaBrasil = fabricaBrasil;
        this.capitalAberto = capitalAberto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public LocalDate getFundacao() {
        return fundacao;
    }

    public void setFundacao(LocalDate fundacao) {
        this.fundacao = fundacao;
    }

    public String getFundador() {
        return fundador;
    }

    public void setFundador(String fundador) {
        this.fundador = fundador;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public Integer getEmpregados() {
        return empregados;
    }

    public void setEmpregados(Integer empregados) {
        this.empregados = empregados;
    }

    public Boolean getFabricaBrasil() {
        return fabricaBrasil;
    }

    public void setFabricaBrasil(Boolean fabricaBrasil) {
        this.fabricaBrasil = fabricaBrasil;
    }

    public Boolean getCapitalAberto() {
        return capitalAberto;
    }

    public void setCapitalAberto(Boolean capitalAberto) {
        this.capitalAberto = capitalAberto;
    }
}

