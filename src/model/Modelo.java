package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Modelo {
    private Integer id;
    private String nome;
    private Marca marca;

    public Modelo() {
    }

    public Modelo(Integer id, String nome, Marca marca) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
}
