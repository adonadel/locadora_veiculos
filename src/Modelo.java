import java.math.BigDecimal;
import java.time.LocalDate;

public class Modelo {
    private Integer id;
    private String nome;
    private String classe;
    private String carroceria;
    private LocalDate ProducaoInicio;
    private LocalDate ProducaoFim;
    private Marca marca;
    private BigDecimal precoMinimo;
    private BigDecimal precoMaximo;
    private String motor;

    public Modelo(Integer id, String nome, String classe, String carroceria, LocalDate producaoInicio, LocalDate producaoFim, Marca marca, BigDecimal precoMinimo, BigDecimal precoMaximo, String motor) {
        this.id = id;
        this.nome = nome;
        this.classe = classe;
        this.carroceria = carroceria;
        ProducaoInicio = producaoInicio;
        ProducaoFim = producaoFim;
        this.marca = marca;
        this.precoMinimo = precoMinimo;
        this.precoMaximo = precoMaximo;
        this.motor = motor;
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

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getCarroceria() {
        return carroceria;
    }

    public void setCarroceria(String carroceria) {
        this.carroceria = carroceria;
    }

    public LocalDate getProducaoInicio() {
        return ProducaoInicio;
    }

    public void setProducaoInicio(LocalDate producaoInicio) {
        ProducaoInicio = producaoInicio;
    }

    public LocalDate getProducaoFim() {
        return ProducaoFim;
    }

    public void setProducaoFim(LocalDate producaoFim) {
        ProducaoFim = producaoFim;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public BigDecimal getPrecoMinimo() {
        return precoMinimo;
    }

    public void setPrecoMinimo(BigDecimal precoMinimo) {
        this.precoMinimo = precoMinimo;
    }

    public BigDecimal getPrecoMaximo() {
        return precoMaximo;
    }

    public void setPrecoMaximo(BigDecimal precoMaximo) {
        this.precoMaximo = precoMaximo;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }
}
