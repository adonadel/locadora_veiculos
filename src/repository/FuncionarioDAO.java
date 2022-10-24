package repository;

import model.Funcionario;

import java.util.ArrayList;
import java.util.List;

public final class FuncionarioDAO {

    static List<Funcionario> funcionarios = new ArrayList<>();
    static int total = 0;

    public static void salvar(Funcionario funcionario) {
        funcionarios.add(funcionario);
        total++;
    }

    public static List<Funcionario> buscarTodos() {
        System.out.println(funcionarios);
        return funcionarios;
    }

    public static List<Funcionario> buscarPorNome(String nome) {
        List<Funcionario> funcionariosFiltrados = new ArrayList<>();
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getPessoa().getNome().contains(nome)) {
                funcionariosFiltrados.add(funcionario);
            }
        }
        return funcionariosFiltrados;
    }

    public static Object[] findFuncionariosInArray() {
        List<Funcionario> funcionarios = FuncionarioDAO.buscarTodos();
        List<String> funcionariosNomes = new ArrayList<>();

        for (Funcionario funcionario : funcionarios) {
            funcionariosNomes.add(funcionario.getPessoa().getNome());
        }

        return funcionariosNomes.toArray();
    }

    public static int getTotal() {
        return total;
    }
}
