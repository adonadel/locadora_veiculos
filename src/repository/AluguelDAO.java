package repository;

import model.*;

import java.util.ArrayList;
import java.util.List;

public final class AluguelDAO {
    static List<Aluguel> alugueis = new ArrayList<>();
    static int total = 0;

    public static void salvar(Aluguel aluguel) {
        alugueis.add(aluguel);
        total++;
    }

    public static int getTotal() {
        return total;
    }

    public static Aluguel getAluguelByPessoaAndVeiculo(Pessoa pessoa, Veiculo veiculo) {
        for (Aluguel aluguel : alugueis) {
            if (aluguel.getPessoa() == pessoa && aluguel.getVeiculo() == veiculo) {
                return aluguel;
            }
        }
        /*******exception*******/
        Aluguel aluguel = new Aluguel();
        return aluguel;
        /*******exception*******/
    }

    public static List<Aluguel> buscarTodos() {
        return alugueis;
    }
}
