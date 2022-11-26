package repository;

import model.Marca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class MarcaDAO {

    static List<Marca> marcas = new ArrayList<>();
    static int total = 1;

    public static void initMarcas() {
        List<String> names = Arrays.asList(
              "Audi",
              "BMW",
              "Chevrolet",
              "Fiat",
              "Ford",
              "Honda",
              "Mercedes",
              "Mercedes-Benz"
        );

        for (String name: names) {
            Marca marca = new Marca();
            marca.setId(total);
            marca.setNome(name);
            salvar(marca);
        }
    }

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

    public static Marca findMarcasByName(String name) {

        List<Marca> marcas = MarcaDAO.buscarTodos();

        for (Marca marca : marcas) {
            if (marca.getNome().equals(name)) {
                return marca;
            }
        }
        return null;
    }

    public static int getTotal() {
        return total;
    }
}
