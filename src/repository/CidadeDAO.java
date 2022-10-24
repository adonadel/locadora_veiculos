package repository;

import model.Cidade;

import java.util.ArrayList;
import java.util.List;

public final class CidadeDAO {

    static List<Cidade> cidades = new ArrayList<>();
    static int total = 0;

    public static void salvar(Cidade cidade) {
        cidades.add(cidade);
        total++;
    }

    public static List<Cidade> buscarTodos() {
        System.out.println(cidades);
        return cidades;
    }

    public static List<Cidade> buscarPorNome(String nome) {
        List<Cidade> cidadesFiltradas = new ArrayList<>();
        for (Cidade cidade : cidades) {
            if (cidade.getNome().contains(nome)) {
                cidadesFiltradas.add(cidade);
            }
        }
        return cidadesFiltradas;
    }

    public static Object[] findCidadesInArray() {
        List<Cidade> cidades = CidadeDAO.buscarTodos();
        List<String> cidadesNomes = new ArrayList<>();

        for (Cidade cidade : cidades) {
            cidadesNomes.add(cidade.getNome());
        }

        return cidadesNomes.toArray();
    }

    public static int getTotal() {
        return total;
    }
}
