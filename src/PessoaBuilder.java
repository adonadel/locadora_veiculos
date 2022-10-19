public class PessoaBuilder {
    public static Pessoa criaPessoa(String tipo){
        if (tipo.equals("Funcionario")){
            return new Funcionario();
        }else if(tipo.equals("Cliente")){
            return new Cliente();
        }
        return new Pessoa();
    }
}
