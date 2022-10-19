public class MainTest {
    public static void main(String[] args) {
        /*
        *
        * main dedicado para a realização de testes no sistema
        *
         */
        Uf uf = new Uf();

        uf.setId(1);
        uf.setNome("Santa Catarina");
        uf.setSigla("SC");

        Cidade cidade = new Cidade();

        cidade.setId(1);
        cidade.setNome("Criciúma");
        cidade.setUf(uf);

        Bairro bairro = new Bairro();

        bairro.setId(1);
        bairro.setNome("Centro");
        bairro.setCidade(cidade);

        Pessoa cliente = PessoaBuilder.criaPessoa("Cliente");
    }
}
