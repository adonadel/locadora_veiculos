package repository;

import model.Aluguel;

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
}
