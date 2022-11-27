package repository;

import model.Adicional;
import model.Sinistro;
import model.TipoVeiculo;
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

    public static Object[] findVeiculosInArray() {
        List<Veiculo> veiculos = VeiculoDAO.buscarTodos();
        List<String> veiculosNomes = new ArrayList<>();

        for (Veiculo veiculo : veiculos) {
            veiculosNomes.add(veiculo.getModelo().getNome() + " (" + veiculo.getPlaca() + ")");
        }

        return veiculosNomes.toArray();
    }

    public static Veiculo findVeiculoById(int id) {
        List<Veiculo> veiculos = VeiculoDAO.buscarTodos();

        for (Veiculo veiculo : veiculos) {
            if (veiculo.getId() == id) {
                return veiculo;
            }
        }
        return null;
    }

    public static Object[] findVeiculosInArrayWithId() {
        List<Veiculo> veiculos = VeiculoDAO.buscarTodos();
        List<String> veiculosNomes = new ArrayList<>();

        for (Veiculo veiculo : veiculos) {
            veiculosNomes.add(veiculo.getId() + " - " + veiculo.getModelo().getNome() + " (" + veiculo.getPlaca() + ")");
        }

        return veiculosNomes.toArray();
    }

    public static Object[] findVeiculosInArrayByTipoVeiculoWithId(TipoVeiculo tipoVeiculo) {
        List<Veiculo> veiculos = VeiculoDAO.buscarTodos();
        List<String> veiculosNomes = new ArrayList<>();

        for (Veiculo veiculo : veiculos) {
            if (veiculo.getTipo() == tipoVeiculo && !veiculo.isAlugado()) {
                veiculosNomes.add(veiculo.getId() + " - " + veiculo.getModelo().getNome() + " (" + veiculo.getPlaca() + ")");
            }
        }

        return veiculosNomes.toArray();
    }

    public static void incluiAdicional(Adicional adicional, Veiculo veiculo) {
        veiculo.setAdicionais(adicional);
    }

    public static void removeAdicional(Adicional adicional, Veiculo veiculo) {
        veiculo.removeAdicional(adicional);
    }

    public static void incluiSinistro(Sinistro sinistro, Veiculo veiculo){
        veiculo.setSinistros(sinistro);
    }

    public static void removeSinistro(Sinistro sinistro, Veiculo veiculo){ veiculo.removeSinistro(sinistro);}

    public static int getTotal() {
        return total;
    }
}
