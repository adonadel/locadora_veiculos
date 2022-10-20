public class MainTest {
    public static void main(String[] args) {
        /*
        *
        * main dedicado para a realização de testes no sistema
        *
         */
        Pais pais = new Pais();

        pais.setId(1);
        pais.setNome("Brasil");
        pais.setIbge("465871223");

        Uf uf = new Uf();

        uf.setId(1);
        uf.setNome("Santa Catarina");
        uf.setSigla("SC");
        uf.setPais(pais);

        Cidade cidade = new Cidade();

        cidade.setId(1);
        cidade.setNome("Criciúma");
        cidade.setUf(uf);

        Bairro bairro = new Bairro();

        bairro.setId(1);
        bairro.setNome("Centro");
        bairro.setCidade(cidade);

        //pessoa

        Marca marca = new Marca();
        marca.setRazaoSocial("Ferrari");

        Veiculo veiculo = VeiculoBuilder.criaVeiculo("Carro");
    }
}
