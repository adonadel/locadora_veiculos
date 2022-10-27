package repository;

import model.Cliente;

import java.util.ArrayList;
import java.util.List;

public final class ClienteDAO {

    static List<Cliente> clientes = new ArrayList<>();
    static int total = 0;

    public static void salvar(Cliente cliente) {
        clientes.add(cliente);
        total++;
    }

    public static List<Cliente> buscarTodos() {
        return clientes;
    }

    public static List<Cliente> buscarPorNome(String nome) {
        List<Cliente> clientesFiltrados = new ArrayList<>();
        for (Cliente cliente : clientes) {
            if (cliente.getPessoa().getNome().contains(nome)) {
                clientesFiltrados.add(cliente);
            }
        }
        return clientesFiltrados;
    }

    public static Object[] findClientesInArray() {
        List<Cliente> clientes = ClienteDAO.buscarTodos();
        List<String> clientesNomes = new ArrayList<>();

        for (Cliente cliente : clientes) {
            clientesNomes.add(cliente.getPessoa().getNome());
        }

        return clientesNomes.toArray();
    }

    public static int getTotal() {
        return total;
    }
}
