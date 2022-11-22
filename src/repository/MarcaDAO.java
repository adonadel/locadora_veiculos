package repository;

import model.Marca;

import java.util.ArrayList;
import java.util.List;

public final class MarcaDAO {

    static List<Marca> marcas = new ArrayList<>();
    static int total = 0;

    public static void salvar(Marca marca) {
        if (marca.getNome() != null) {
            marcas.add(marca);
            total++;
        }
    }

    public static List<Marca> buscarTodos() {
        return marcas;
    }

    public static List<Marca> buscarPorNome(String nome) {
        List<Marca> marcasFiltradas = new ArrayList<>();
        for (Marca marca : marcas) {
            if (marca.getNome().contains(nome)) {
                marcasFiltradas.add(marca);
            }
        }
        return marcasFiltradas;
    }

    public static Object[] findMarcasInArray() {
        List<Marca> marcas = MarcaDAO.buscarTodos();
        List<String> marcasNomes = new ArrayList<>();

        for (Marca marca : marcas) {
            marcasNomes.add(marca.getNome());
        }

        return marcasNomes.toArray();
    }

    public static Marca findMarcasById(int id) {
        List<Marca> marcas = MarcaDAO.buscarTodos();

        for (Marca marca : marcas) {
            if (marca.getId() == id) {
                return marca;
            }
        }
        return null;
    }

    public static Object[] findMarcasInArrayWithId() {
        List<Marca> marcas = MarcaDAO.buscarTodos();
        List<String> marcasNomes = new ArrayList<>();

        for (Marca marca : marcas) {
            marcasNomes.add(marca.getId() + " - " + marca.getNome());
        }

        return marcasNomes.toArray();
    }

    public static int getTotal() {
        return total;
    }
}
