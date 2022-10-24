package repository;

import model.Marca;

import java.util.ArrayList;
import java.util.List;

public final class MarcaDAO {

    static List<Marca> marcas = new ArrayList<>();
    static int total = 0;

    public static void salvar(Marca marca) {
        marcas.add(marca);
        total++;
    }

    public static List<Marca> buscarTodos() {
        System.out.println(marcas);
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

    public static int getTotal() {
        return total;
    }
}
