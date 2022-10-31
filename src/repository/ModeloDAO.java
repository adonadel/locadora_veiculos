package repository;

import model.Modelo;

import java.util.ArrayList;
import java.util.List;

public final class ModeloDAO {

    static List<Modelo> modelos = new ArrayList<>();
    static int total = 0;

    public static void salvar(Modelo modelo) {
        modelos.add(modelo);
        total++;
    }

    public static List<Modelo> buscarTodos() {
        return modelos;
    }

    public static List<Modelo> buscarPorNome(String nome) {
        List<Modelo> modelosFiltrados = new ArrayList<>();
        for (Modelo modelo : modelos) {
            if (modelo.getNome().contains(nome)) {
                modelosFiltrados.add(modelo);
            }
        }
        return modelosFiltrados;
    }

    public static Object[] findModelosInArray() {
        List<Modelo> modelos = ModeloDAO.buscarTodos();
        List<String> modelosNomes = new ArrayList<>();

        for (Modelo modelo : modelos) {
            modelosNomes.add(modelo.getNome());
        }

        return modelosNomes.toArray();
    }

    public static Modelo findModeloById(int id) {
        List<Modelo> modelos = ModeloDAO.buscarTodos();

        for (Modelo modelo : modelos) {
            if (modelo.getId() == id) {
                return modelo;
            }
        }
        return null;
    }

    public static Object[] findModeloesInArrayWithId() {
        List<Modelo> modeloes = ModeloDAO.buscarTodos();
        List<String> modelosNomes = new ArrayList<>();

        for (Modelo modelo : modelos) {
            modelosNomes.add(modelo.getId() + " - " + modelo.getNome());
        }

        return modelosNomes.toArray();
    }

    public static int getTotal() {
        return total;
    }
}
