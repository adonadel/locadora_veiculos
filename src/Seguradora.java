public class Seguradora {
    private Integer id;
    private String nome;
    private String CEO;
    private String sede;
    private Integer numeroEmpregados;
    private Boolean apresentaSite;
    private String site;

    public Seguradora(Integer id, String nome, String CEO, String sede, Integer numeroEmpregados, Boolean apresentaSite) {
        this.id = id;
        this.nome = nome;
        this.CEO = CEO;
        this.sede = sede;
        this.numeroEmpregados = numeroEmpregados;
        this.apresentaSite = apresentaSite;
    }

    public Seguradora(Integer id, String nome, String CEO, String sede, Integer numeroEmpregados, Boolean apresentaSite, String site) {
        this.id = id;
        this.nome = nome;
        this.CEO = CEO;
        this.sede = sede;
        this.numeroEmpregados = numeroEmpregados;
        this.apresentaSite = apresentaSite;
        this.site = site;
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

    public String getCEO() {
        return CEO;
    }

    public void setCEO(String CEO) {
        this.CEO = CEO;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public Integer getNumeroEmpregados() {
        return numeroEmpregados;
    }

    public void setNumeroEmpregados(Integer numeroEmpregados) {
        this.numeroEmpregados = numeroEmpregados;
    }

    public Boolean getApresentaSite() {
        return apresentaSite;
    }

    public void setApresentaSite(Boolean apresentaSite) {
        this.apresentaSite = apresentaSite;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
