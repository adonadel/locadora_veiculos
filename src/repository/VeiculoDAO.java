package repository;

import model.Veiculo;

import java.util.ArrayList;
import java.util.List;

public final class VeiculoDAO {

    static List<Veiculo> veiculos = new ArrayList<>();
    static int total = 0;

    public static void salvar(Veiculo veiculo) {
        veiculos.add(veiculo);
        total++;
    }

    public static List<Veiculo> buscarTodos() {
        return veiculos;
    }

//    public static List<Veiculo> buscarPorNome(String nome) {
//        List<Veiculo> veiculosFiltradas = new ArrayList<>();
//        for (Veiculo veiculo : veiculos) {
//            if (veiculo.getNome().contains(nome)) {
//                veiculosFiltradas.add(veiculo);
//            }
//        }
//        return veiculosFiltradas;
//    }

//    public static Object[] findVeiculosInArray() {
//        List<Veiculo> veiculos = VeiculoDAO.buscarTodos();
//        List<String> veiculosNomes = new ArrayList<>();
//
//        for (Veiculo veiculo : veiculos) {
//            veiculosNomes.add(veiculo.getNome());
//        }
//
//        return veiculosNomes.toArray();
//    }

//    public static Object[] findVeiculosInArrayWithId() {
//        List<Veiculo> veiculos = VeiculoDAO.buscarTodos();
//        List<String> veiculosNomes = new ArrayList<>();
//
//        for (Veiculo veiculo : veiculos) {
//            veiculosNomes.add(veiculo.getId() + " - " + veiculo.getNome());
//        }
//
//        return veiculosNomes.toArray();
//    }

    public static int getTotal() {
        return total;
    }
}
