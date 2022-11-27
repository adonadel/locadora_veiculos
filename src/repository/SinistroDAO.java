package repository;

import model.Adicional;
import model.Sinistro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SinistroDAO {
    static List<Sinistro> sinistros = new ArrayList<>();

    static int total = 0;



    public static void initSinistros() {
        List<String> names = Arrays.asList(
                "Batida lateral",
                "Risco superficial porta trasseira",
                "Pneu furado"
        );

        for (String name: names) {
            Sinistro sinistro = new Sinistro();

            sinistro.setId(total);
            sinistro.setNome(name);
            sinistro.setDescricao("");

            salvar(sinistro);
        }
    }


    public static void salvar(Sinistro sinistro){
        sinistros.add(sinistro);
        total++;
    }

    public static List<Sinistro> buscarTodos(){
        return sinistros;
    }

    public static List<Sinistro> bucarPorNome(String nome){
        List<Sinistro> sinistrosBuscados = new ArrayList<>();

        for (Sinistro sinistro : sinistros){
            if (sinistro.getNome().contains(nome)){
                sinistrosBuscados.add(sinistro);
            }
        }
        return sinistrosBuscados;
    }

    public static Object[] findSinistrosInArray(){
        List<Sinistro> sinistros = SinistroDAO.buscarTodos();
        List<String> sinistrosNomes = new ArrayList<>();

        for (Sinistro sinistro : sinistros){
            sinistrosNomes.add(sinistro.getNome());
        }
        return  sinistrosNomes.toArray();
    }

    public static Sinistro findSinistroById(int id){
        List<Sinistro> sinistros = SinistroDAO.buscarTodos();

        for(Sinistro sinistro : sinistros){
            if(sinistro.getId() == id){
                return sinistro;
            }
        }
        return null;
    }

    public static Object[] findSinistrosInArrayWithId(){
        List<Sinistro> sinistros = SinistroDAO.buscarTodos();
        List<String> sinistrosNomes = new ArrayList<>();

        for(Sinistro sinistro : sinistros){
            sinistrosNomes.add(sinistro.getId() + " - " + sinistro.getNome());
        }

        return sinistrosNomes.toArray();
    }


    public static int getTotal(){
        return total;
    }


}
