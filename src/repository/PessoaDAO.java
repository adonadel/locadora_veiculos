package repository;

import model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public final class PessoaDAO {

    static List<Pessoa> pessoas = new ArrayList<>();
    static int total = 0;

    public static void salvar(Pessoa pessoa) {
        pessoas.add(pessoa);
        total++;
    }

    public static List<Pessoa> buscarTodos() {
        System.out.println(pessoas);
        return pessoas;
    }

    public static List<Pessoa> buscarPorNome(String nome) {
        List<Pessoa> pessoasFiltradas = new ArrayList<>();
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getNome().contains(nome)) {
                pessoasFiltradas.add(pessoa);
            }
        }
        return pessoasFiltradas;
    }

    public static Object[] findPessoasInArray() {
        List<Pessoa> pessoas = PessoaDAO.buscarTodos();
        List<String> pessoasNomes = new ArrayList<>();

        for (Pessoa pessoa : pessoas) {
            pessoasNomes.add(pessoa.getNome());
        }

        return pessoasNomes.toArray();
    }

    public static int getTotal() {
        return total;
    }
}
