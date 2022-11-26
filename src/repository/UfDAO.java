package repository;

import model.Pais;
import model.Uf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class UfDAO {

    static List<Uf> ufs = new ArrayList<>();
    static int total = 1;

    public static void initUfs() {
        List<String> names = Arrays.asList(
              "Paraná",
              "Rio Grande do Sul",
              "Santa Catarina"
        );

        String[] siglas = new String[]{
            "PR",
            "RS",
            "SC",
        };

        Pais pais = PaisDAO.findPaisById(1);

        for (String name: names) {
            Uf uf = new Uf();
            uf.setId(total);
            uf.setSigla(siglas[total-1]);
            uf.setNome(name);
            uf.setPais(pais);
            salvar(uf);
        }
    }

    public static void salvar(Uf uf) {
        ufs.add(uf);
        total++;
    }

    public static List<Uf> buscarTodos() {
        return ufs;
    }

    public static List<Uf> buscarPorNome(String nome) {
        List<Uf> ufsFiltradas = new ArrayList<>();
        for (Uf uf : ufs) {
            if (uf.getNome().contains(nome)) {
                ufsFiltradas.add(uf);
            }
        }
        return ufsFiltradas;
    }

    public static Object[] findUfsInArray() {
        List<Uf> ufs = UfDAO.buscarTodos();
        List<String> ufsNomes = new ArrayList<>();

        for (Uf uf : ufs) {
            ufsNomes.add(uf.getNome());
        }

        return ufsNomes.toArray();
    }

    public static Uf findUfById(int id) {
        List<Uf> ufs = UfDAO.buscarTodos();

        for (Uf uf : ufs) {
            if (uf.getId() == id) {
                return uf;
            }
        }
        return null;
    }

    public static Object[] findUfesInArrayWithId() {
        List<Uf> ufs = UfDAO.buscarTodos();
        List<String> ufsNomes = new ArrayList<>();

        for (Uf uf : ufs) {
            ufsNomes.add(uf.getId() + " - " + uf.getNome());
        }

        return ufsNomes.toArray();
    }

    public static int getTotal() {
        return total;
    }

    public static Uf findUfBySigla(String sigla) {
        List<Uf> ufs = UfDAO.buscarTodos();

        for (Uf uf : ufs) {
            if (uf.getSigla().equals(sigla)) {
                return uf;
            }
        }
        return null;
    }
}
