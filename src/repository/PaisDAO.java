package repository;

import model.Pais;

import java.util.ArrayList;
import java.util.List;

public final class PaisDAO {

    static List<Pais> paises = new ArrayList<>();
    static int total = 0;

    public static void salvar(Pais pais) {
        paises.add(pais);
        total++;
    }

    public static List<Pais> buscarTodos() {
        return paises;
    }

    public static List<Pais> buscarPorNome(String nome) {
        List<Pais> paisesFiltrados = new ArrayList<>();
        for (Pais pais : paises) {
            if (pais.getNome().contains(nome)) {
                paisesFiltrados.add(pais);
            }
        }
        return paisesFiltrados;
    }

    public static Object[] findPaisesInArray() {
        List<Pais> paises = PaisDAO.buscarTodos();
        List<String> paisesNomes = new ArrayList<>();

        for (Pais pais : paises) {
            paisesNomes.add(pais.getNome());
        }

        return paisesNomes.toArray();
    }

    public static Pais findPaisById(int id) {
        List<Pais> paises = PaisDAO.buscarTodos();

        for (Pais pais : paises) {
            if (pais.getId() == id) {
                return pais;
            }
        }
        return null;
    }

    public static Object[] findPaisesInArrayWithId() {
        List<Pais> paises = PaisDAO.buscarTodos();
        List<String> paisesNomes = new ArrayList<>();

        for (Pais pais : paises) {
            paisesNomes.add(pais.getId() + " - " + pais.getNome());
        }

        return paisesNomes.toArray();
    }

    public static int getTotal() {
        return total;
    }
}
