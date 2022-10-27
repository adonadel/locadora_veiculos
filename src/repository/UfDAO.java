package repository;

import model.Uf;

import java.util.ArrayList;
import java.util.List;

public final class UfDAO {

    static List<Uf> ufs = new ArrayList<>();
    static int total = 0;

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
}
