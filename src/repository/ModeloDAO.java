package repository;

import model.Marca;
import model.Modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ModeloDAO {

    static List<Modelo> modelos = new ArrayList<>();
    static int total = 0;

    public static void initModelos() {
        List<String> namesAudi = Arrays.asList(
              "a3",
              "a4"
        );
        List<String> namesBMW = Arrays.asList(
              "x3",
              "x5"
        );
        List<String> namesChevrolet = Arrays.asList(
              "Camaro",
              "Corsa"
        );
        List<String> namesFiat = Arrays.asList(
              "Uno mille",
              "Punto"
        );
        List<String> namesFord = Arrays.asList(
              "Ka",
              "Mustang"
        );
        List<String> namesHonda = Arrays.asList(
              "150 titan",
              "160"
        );
        List<String> namesMercedes = Arrays.asList(
              "Atron",
              "Axor"
        );
        List<String> namesMercedesBenz = Arrays.asList(
              "c200",
              "c300"
        );
        Marca audi = MarcaDAO.findMarcasByName("Audi");
        Marca bmw = MarcaDAO.findMarcasByName("BMW");
        Marca chevrolet = MarcaDAO.findMarcasByName("Chevrolet");
        Marca fiat = MarcaDAO.findMarcasByName("Fiat");
        Marca ford = MarcaDAO.findMarcasByName("Ford");
        Marca honda = MarcaDAO.findMarcasByName("Honda");
        Marca mercedes = MarcaDAO.findMarcasByName("Mercedes");
        Marca mercedesBenz = MarcaDAO.findMarcasByName("Mercedes-Benz");

        for (String name: namesAudi) {
            Modelo modelo = new Modelo();
            modelo.setId(total);
            modelo.setNome(name);
            modelo.setMarca(audi);
            salvar(modelo);
        }

        for (String name: namesBMW) {
            Modelo modelo = new Modelo();
            modelo.setId(total);
            modelo.setNome(name);
            modelo.setMarca(bmw);
            salvar(modelo);
        }

        for (String name: namesChevrolet) {
            Modelo modelo = new Modelo();
            modelo.setId(total);
            modelo.setNome(name);
            modelo.setMarca(chevrolet);
            salvar(modelo);
        }

        for (String name: namesFiat) {
            Modelo modelo = new Modelo();
            modelo.setId(total);
            modelo.setNome(name);
            modelo.setMarca(fiat);
            salvar(modelo);
        }

        for (String name: namesFord) {
            Modelo modelo = new Modelo();
            modelo.setId(total);
            modelo.setNome(name);
            modelo.setMarca(ford);
            salvar(modelo);
        }

        for (String name: namesHonda) {
            Modelo modelo = new Modelo();
            modelo.setId(total);
            modelo.setNome(name);
            modelo.setMarca(honda);
            salvar(modelo);
        }

        for (String name: namesMercedes) {
            Modelo modelo = new Modelo();
            modelo.setId(total);
            modelo.setNome(name);
            modelo.setMarca(mercedes);
            salvar(modelo);
        }

        for (String name: namesMercedesBenz) {
            Modelo modelo = new Modelo();
            modelo.setId(total);
            modelo.setNome(name);
            modelo.setMarca(mercedesBenz);
            salvar(modelo);
        }
    }

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

    public static Object[] findModelosInArrayWithId() {
        List<Modelo> modelos = ModeloDAO.buscarTodos();
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
