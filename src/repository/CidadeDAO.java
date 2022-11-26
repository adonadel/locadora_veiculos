package repository;

import model.Cidade;
import model.Uf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class CidadeDAO {

    static List<Cidade> cidades = new ArrayList<>();
    static int total = 1;

    public static void initCidades() {

        List<String> namesSC = Arrays.asList(
            "Criciúma",
            "Blumenau",
            "Forquilinha",
            "Florianópolis",
            "Içara",
            "Itajaí",
            "Joinville"
        );

        List<String> namesRS = Arrays.asList(
            "Porto Alegre",
            "Caxias do sul",
            "Canoas",
            "Pelotas",
            "Gravataí",
            "Santa Maria",
            "Viamão"
        );

        List<String> namesPR = Arrays.asList(
            "Curitiba",
            "Londrina",
            "Maringá",
            "Ponta Grossa",
            "Cascavel",
            "São José dos Pinhais",
            "Foz do iguaçu"
        );

        Uf sc = UfDAO.findUfBySigla("SC");
        Uf pr = UfDAO.findUfBySigla("PR");
        Uf rs = UfDAO.findUfBySigla("RS");

        for (String nameSC: namesSC) {
            Cidade cidade = new Cidade();
            cidade.setId(total);
            cidade.setNome(nameSC);
            cidade.setUf(sc);
            salvar(cidade);
        }

        for (String namePR: namesPR) {
            Cidade cidade = new Cidade();
            cidade.setId(total);
            cidade.setNome(namePR);
            cidade.setUf(pr);
            salvar(cidade);
        }

        for (String nameRS: namesRS) {
            Cidade cidade = new Cidade();
            cidade.setId(total);
            cidade.setNome(nameRS);
            cidade.setUf(rs);
            salvar(cidade);
        }
    }

    public static void salvar(Cidade cidade) {
        cidades.add(cidade);
        total++;
    }

    public static List<Cidade> buscarTodos() {
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

    public static Cidade findCidadeById(int id) {
        List<Cidade> cidades = CidadeDAO.buscarTodos();

        for (Cidade cidade : cidades) {
            if (cidade.getId() == id) {
                return cidade;
            }
        }
        return null;
    }

    public static Object[] findCidadesInArrayWithId() {
        List<Cidade> cidades = CidadeDAO.buscarTodos();
        List<String> cidadesNomes = new ArrayList<>();

        for (Cidade cidade : cidades) {
            cidadesNomes.add(cidade.getId() + " - " + cidade.getNome());
        }

        return cidadesNomes.toArray();
    }

    public static Object[] findCidadesInArrayWithIdBySigla(String sigla) {
        List<Cidade> cidades = CidadeDAO.buscarTodos();
        List<String> cidadesNomes = new ArrayList<>();

        for (Cidade cidade : cidades) {
            if (cidade.getUf().getSigla().equals(sigla))
                cidadesNomes.add(cidade.getId() + " - " + cidade.getNome());
        }

        return cidadesNomes.toArray();
    }

    public static int getTotal() {
        return total;
    }
}
