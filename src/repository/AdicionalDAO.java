package repository;

import model.Adicional;

import java.util.ArrayList;
import java.util.List;

public final class AdicionalDAO {

    static List<Adicional> adicionais = new ArrayList<>();
    static int total = 0;

    public static void salvar(Adicional adicional) {
        adicionais.add(adicional);
        total++;
    }

    public static List<Adicional> buscarTodos() {
        return adicionais;
    }

    public static List<Adicional> buscarPorNome(String nome) {
        List<Adicional> adicionaisFiltrados = new ArrayList<>();
        for (Adicional adicional : adicionais) {
            if (adicional.getNome().contains(nome)) {
                adicionaisFiltrados.add(adicional);
            }
        }
        return adicionaisFiltrados;
    }

    public static Object[] findAdicionaisInArray() {
        List<Adicional> adicionais = AdicionalDAO.buscarTodos();
        List<String> adicionaisNomes = new ArrayList<>();

        for (Adicional adicional : adicionais) {
            adicionaisNomes.add(adicional.getNome());
        }

        return adicionaisNomes.toArray();
    }

    public static Adicional findAdicionalById(int id) {
        List<Adicional> adicionais = AdicionalDAO.buscarTodos();

        for (Adicional adicional : adicionais) {
            if (adicional.getId() == id) {
                return adicional;
            }
        }
        return null;
    }

    public static Object[] findAdicionaisInArrayWithId() {
        List<Adicional> adicionais = AdicionalDAO.buscarTodos();
        List<String> adicionaisNomes = new ArrayList<>();

        for (Adicional adicional : adicionais) {
            adicionaisNomes.add(adicional.getId() + " - " + adicional.getNome());
        }

        return adicionaisNomes.toArray();
    }

    public static int getTotal() {
        return total;
    }
}
