public class VeiculoBuilder{
    public static Veiculo criaVeiculo(String tipo){

        if (tipo.equals("Moto")){
            return new Moto();
        }else if(tipo.equals("Caminhao")){
            return new Caminhao();
        }else if(tipo.equals("Carro")) {
            return new Carro();
        }
            return new Veiculo();
    }
}
