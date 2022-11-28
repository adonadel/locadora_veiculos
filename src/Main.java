import model.*;
import repository.*;
import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Main {
     public static void main(String[] args) {
        List<TipoVeiculo> tiposVeiculo = new ArrayList<>();
        tiposVeiculo.add(TipoVeiculo.CAMINHAO);
        tiposVeiculo.add(TipoVeiculo.CARRO);
        System.out.println(tiposVeiculo.get(0));
        System.exit(0);

        initAll(); //Inicializar as models bases para cadastro
        Object usuarioLogado = chamaSelecaoUsuario();
        checaSenhaUsuario(usuarioLogado);
    }
    private static void initAll() {
         PaisDAO.initPaises();
         UfDAO.initUfs();
         CidadeDAO.initCidades();
         MarcaDAO.initMarcas();
         ModeloDAO.initModelos();
    }
    
    /*Pessoas e relacionados*/
    private static Pessoa chamaCadastroPessoa(int tipoPessoa) {
        String nome = JOptionPane.showInputDialog(null, "Informe o nome da pessoa: ");

        Object[] nomesPaises = PaisDAO.findPaisesInArrayWithId();
        Object nomePais = JOptionPane.showInputDialog(null, "Selecione o país: ", "Cadastro de pessoas", JOptionPane.QUESTION_MESSAGE, null, nomesPaises, nomesPaises[0]);
        String[] splitPais = nomePais.toString().split(" - ");
        int paisId = parseInt(splitPais[0]);
        Pais pais = PaisDAO.findPaisById(paisId);

        Object[] nomesUfs = UfDAO.findUfesInArrayWithId();
        Object nomeUf = JOptionPane.showInputDialog(null, "Selecione o estado: ", "Cadastro de pessoas", JOptionPane.QUESTION_MESSAGE, null, nomesUfs, nomesUfs[0]);
        String[] splitUf = nomeUf.toString().split(" - ");
        int ufId = parseInt(splitUf[0]);
        Uf uf = UfDAO.findUfById(ufId);

        assert uf != null;
        Object[] nomesCidades = CidadeDAO.findCidadesInArrayWithIdBySigla(uf.getSigla());
        Object nomeCidade = JOptionPane.showInputDialog(null, "Selecione a cidade: ", "Cadastro de pessoas", JOptionPane.QUESTION_MESSAGE, null, nomesCidades, nomesCidades[0]);
        String[] splitCidade = nomeCidade.toString().split(" - ");
        int cidadeId = parseInt(splitCidade[0]);
        Cidade cidade = CidadeDAO.findCidadeById(cidadeId);

        String telefone = JOptionPane.showInputDialog(null, "Informe o telefone da pessoa: ");
        String celular = JOptionPane.showInputDialog(null, "Informe o celular da pessoa: ");


        String tipoDocumento = "CPF";
        String CNH = "";
        LocalDate dataNasc = null;
        if (tipoPessoa == 1) {
            tipoDocumento = "CNPJ";
        }else{
            CNH = JOptionPane.showInputDialog(null, "Informe o número da CNH da pessoa: ");
            String auxDataNasc = JOptionPane.showInputDialog(null, "Informe a data de nascimento da pessoa: (DD/MM/AAAA)");
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dataNasc = LocalDate.parse(auxDataNasc, pattern);
        }

        String documento = JOptionPane.showInputDialog(null, "Informe o " + tipoDocumento + " da pessoa: ");
        long id = PessoaDAO.getTotal() + 1;
        if (tipoDocumento.equals("CPF")) {
            PessoaFisica pessoaFisica = new PessoaFisica();
            pessoaFisica.setTipo(TipoPessoa.FISICA);
            pessoaFisica.setId(id);
            pessoaFisica.setNome(nome);
            pessoaFisica.setCPF(documento);
            pessoaFisica.setCNH(CNH);
            pessoaFisica.setDataNasc(dataNasc);
            pessoaFisica.setPais(pais);
            pessoaFisica.setUf(uf);
            pessoaFisica.setCidade(cidade);
            pessoaFisica.setCelular(celular);
            pessoaFisica.setTelefone(telefone);
            pessoaFisica.setCPF(documento);
            return pessoaFisica;
        } else {
            PessoaJuridica pessoaJuridica = new PessoaJuridica();
            pessoaJuridica.setId(id);
            pessoaJuridica.setTipo(TipoPessoa.JURIDICA);
            pessoaJuridica.setNome(nome);
            pessoaJuridica.setCNPJ(documento);
            pessoaJuridica.setPais(pais);
            pessoaJuridica.setUf(uf);
            pessoaJuridica.setCidade(cidade);
            return pessoaJuridica;
        }
    }

    private static Funcionario chamaCadastroFuncionario(Pessoa pessoa) {
        Funcionario funcionario = new Funcionario();
        funcionario.setPessoa(pessoa);
        funcionario.setId(FuncionarioDAO.getTotal() + 1);
        FuncionarioDAO.salvar(funcionario);
        Double salBruto = Double.parseDouble(JOptionPane.showInputDialog(null, "Informe o salário bruto do funcionário: "));
        Double salLiquido = Double.parseDouble(JOptionPane.showInputDialog(null, "Informe o salário líquido do funcionário: "));
        funcionario.setSalarioBruto(salBruto);
        funcionario.setSalarioBruto(salLiquido);
        return funcionario;
    }

    private static void chamaMenuCliente() {
        String[] opcoesMenu = {"Física", "Jurídica", "Voltar"};
        int menu = JOptionPane.showOptionDialog(null, "Cadastrar cliente como pessoa:",
                "Menu Cadastros",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenu, opcoesMenu[0]);
        TipoCarteira tipoCarteira;

        TipoCarteira[] tiposCarteira = {
                TipoCarteira.A,
                TipoCarteira.B,
                TipoCarteira.C,
                TipoCarteira.AB,
                TipoCarteira.ABC
        };

        switch (menu) {
            case 0:
                Pessoa pessoaFisica = chamaCadastroPessoa(0);

                tipoCarteira = (TipoCarteira) JOptionPane.showInputDialog(null, "Informe o tipo da sua CNH:","Cadastrar Pessoa", JOptionPane.DEFAULT_OPTION, null, tiposCarteira, tiposCarteira[0]);

                PessoaDAO.salvar(pessoaFisica);

                Cliente clienteFisico = new Cliente();
                clienteFisico.setPessoa(pessoaFisica);
                clienteFisico.setId(ClienteDAO.getTotal() + 1);
                clienteFisico.setTipoCarteira(tipoCarteira);
                ClienteDAO.salvar(clienteFisico);

                chamaMenuCadastros();
                break;
            case 1:
                Pessoa pessoaJuridica = chamaCadastroPessoa(1);

                tipoCarteira = (TipoCarteira) JOptionPane.showInputDialog(null, "Informe o tipo da sua CNH:","Cadastrar Pessoa", JOptionPane.DEFAULT_OPTION, null, tiposCarteira, tiposCarteira[0]);

                PessoaDAO.salvar(pessoaJuridica);

                Cliente clienteJuridico = new Cliente();
                clienteJuridico.setPessoa(pessoaJuridica);
                clienteJuridico.setId(ClienteDAO.getTotal() + 1);
                clienteJuridico.setTipoCarteira(tipoCarteira);
                ClienteDAO.salvar(clienteJuridico);

                chamaMenuCadastros();
                break;
            case 2: //Voltar
                chamaMenuCadastros();
                break;
        }
    }

    private static void chamaRelatorioPessoa(int tipoPessoa) { // tipoPessoa = Funcionario / cliente

        String listaPessoas = "";
        if (tipoPessoa == 1) {
            List<Funcionario> funcionarios = FuncionarioDAO.buscarTodos();

            listaPessoas += "Lista de funcionários";

            for (Funcionario funcionario : funcionarios) {
                listaPessoas += "\n" + funcionario.getId() + " - " + funcionario.getPessoa().getNome() + "  tipo: " + funcionario.getPessoa().getTipo() + "   documento: " + funcionario.getPessoa().getDocumento();
            }
        }else {
            List<Cliente> clientes = ClienteDAO.buscarTodos();

            listaPessoas += "Lista de clientes";

            for (Cliente cliente : clientes) {
                listaPessoas += "\n" + cliente.getId() + " - " + cliente.getPessoa().getNome() + "  tipo: " + cliente.getPessoa().getTipo() + "   documento: " + cliente.getPessoa().getDocumento();
            }
        }

        JOptionPane.showMessageDialog(null, listaPessoas);
    }
    /*Pessoas e relacionados*/

    /*Veículos e relacionados*/
    private static void chamaMenuVeiculosERelacionados() {
        String[] opcoesMenuCadastro = {"Cadastrar veículo", "Adicionais", "Sinistro",  "Marca", "Modelo", "Voltar"};
        int menu = JOptionPane.showOptionDialog(null, "Escolha uma opção: ",
                "Menu veículos e relacionados",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastro, opcoesMenuCadastro[0]);

        switch (menu) {
            case 0:
                try {
                    chamaMenuVeiculos();
                    chamaMenuVeiculosERelacionados();
                } catch (NumberFormatException letraNoInt) {
                    JOptionPane.showMessageDialog(null, "Apenas números são permitidos!!", "Alerta", JOptionPane.ERROR_MESSAGE);
                } catch (DateTimeParseException formatoData) {
                    JOptionPane.showMessageDialog(null, "Dígite a data no formato correto.", "Alerta", JOptionPane.ERROR_MESSAGE);
                } catch (ArrayIndexOutOfBoundsException semCadastros) {
                    JOptionPane.showMessageDialog(null, "Cadastre marca e modelo.", "Alerta", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case 1:
                try {
                    chamaMenuAdicionais();
                    chamaMenuVeiculosERelacionados();
                } catch (ArrayIndexOutOfBoundsException teste){
                    JOptionPane.showMessageDialog(null, "Cadastre um veículo antes.", "Alerta", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case 2: //eric to aqui
                try {
                    chamaMenuSinistro();
                } catch (ArrayIndexOutOfBoundsException teste){
                    JOptionPane.showMessageDialog(null, "Cadastre um veículo antes.", "Alerta", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case 3:
                Marca marca = chamaCadastroMarca();
                MarcaDAO.salvar(marca);
                chamaMenuVeiculosERelacionados();
                break;
            case 4:
                try {
                    Modelo modelo = chamaCadastroModelo();
                    ModeloDAO.salvar(modelo);
                    chamaMenuVeiculosERelacionados();
                } catch (ArrayIndexOutOfBoundsException semMarca){
                    JOptionPane.showMessageDialog(null, "Cadastre uma marca antes.", "Alerta", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case 5: //Voltar
                chamaMenuCadastros();
                break;
        }
    }

//    aqui2
    public static void chamaMenuSinistro(){
        String[] opcoesMenuCadastro = {"Cadastrar sinistro", "Incluir em um veículo", "Remover de um veículo", "Voltar"};
        int menu = JOptionPane.showOptionDialog(null, "Escolha uma opção: ",
                "Menu cadastro de sinistros",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastro, opcoesMenuCadastro[0]);
        switch (menu) {
            case 0: //cadastro
                Sinistro sinistro = chamaCadastroSinistro();
                SinistroDAO.salvar(sinistro);
                chamaMenuSinistro();
                break;
            case 1: //incluir em um veículo
                chamaIncluiSinistros();
                chamaMenuSinistro();
                break;
            case 2: //remover de um veículo
                chamaRemoveSinistros();
                chamaMenuSinistro();
                break;
            case 3:
                chamaMenuVeiculosERelacionados();
                break;
            case 4: //voltar
                chamaMenuRelatorios();
                break;
        }

    }

    private static Sinistro chamaCadastroSinistro() {
        Sinistro sinistro = new Sinistro();
        String nome = JOptionPane.showInputDialog(null, "Informe o sinistro: ");
        String descricao = JOptionPane.showInputDialog(null, "Informe a descrição do sinistro: ");
        sinistro.setId(SinistroDAO.getTotal() + 1);
        sinistro.setNome(nome);
        sinistro.setDescricao(descricao);
        return sinistro;
    }

    private static void chamaIncluiSinistros() {

        Object[] veiculos = VeiculoDAO.findVeiculosInArrayWithId();
        Object nomeVeiculo = JOptionPane.showInputDialog(null, "Selecione o veículo: ", "Inclusão de sinistros", JOptionPane.QUESTION_MESSAGE, null, veiculos, veiculos[0]);
        String[] splitVeiculo = nomeVeiculo.toString().split(" - ");
        int veiculoId = parseInt(splitVeiculo[0]);
        Veiculo veiculo = VeiculoDAO.findVeiculoById(veiculoId);

        int continuar;
        Sinistro sinistro;
        do {
            Object[] listSinistros = SinistroDAO.findSinistrosInArrayWithId();
            Object nomeSinistro = JOptionPane.showInputDialog(null, "Selecione um sinistros: ", "Inclusão de sinistros", JOptionPane.QUESTION_MESSAGE, null, listSinistros, listSinistros[0]);
            String[] splitSinistro = nomeSinistro.toString().split(" - ");
            int sinistroId = parseInt(splitSinistro[0]);
            sinistro = SinistroDAO.findSinistroById(sinistroId);
            VeiculoDAO.incluiSinistro(sinistro, veiculo);
            continuar = JOptionPane.showConfirmDialog(null, "Deseja selecionar mais um sinistro?", "Inclusão de sinistros", JOptionPane.DEFAULT_OPTION);
        }while(continuar == JOptionPane.YES_OPTION);
    }

    private static void chamaRemoveSinistros() {

        Object[] veiculos = VeiculoDAO.findVeiculosInArrayWithId();
        Object nomeVeiculo = JOptionPane.showInputDialog(null, "Selecione o veículo: ", "Remoção de sinistros", JOptionPane.QUESTION_MESSAGE, null, veiculos, veiculos[0]);
        String[] splitVeiculo = nomeVeiculo.toString().split(" - ");
        int veiculoId = parseInt(splitVeiculo[0]);
        Veiculo veiculo = VeiculoDAO.findVeiculoById(veiculoId);

        int continuar = 1;
        Sinistro sinistro;
        do {
            Object[] listSinistros = SinistroDAO.findSinistrosInArray();
            Object nomeSinistro = JOptionPane.showInputDialog(null, "Selecione um sinistro: ", "Remoção de sinistros", JOptionPane.QUESTION_MESSAGE, null, listSinistros, listSinistros[0]);
            String[] splitSinistro = nomeSinistro.toString().split(" - ");
            int sinistroId = parseInt(splitSinistro[0]);

            sinistro = SinistroDAO.findSinistroById(sinistroId);
            VeiculoDAO.removeSinistro(sinistro, veiculo);
            continuar = parseInt(JOptionPane.showInputDialog(null, "Deseja selecionar mais um sinistro?", "Inclusão de sinistros", JOptionPane.YES_NO_OPTION));
        }while(continuar == 0);
    }
//-------------- kinho --------

    private static void chamaMenuVeiculos() {
        String[] opcoesMenuCadastro = {"Caminhão", "Carro", "Moto", "Voltar"};
        int menu = JOptionPane.showOptionDialog(null, "Escolha uma opção: ",
                "Menu cadastro veículos",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastro, opcoesMenuCadastro[0]);
        Veiculo veiculo;
        switch (menu) {
            case 0: //caminhão
                veiculo = chamaCadastroVeiculo(0);
                VeiculoDAO.salvar(veiculo);

                chamaMenuVeiculos();
                break;
            case 1: //carro
                veiculo = chamaCadastroVeiculo(1);
                VeiculoDAO.salvar(veiculo);

                chamaMenuVeiculos();
                break;
            case 2: //moto
                veiculo = chamaCadastroVeiculo(2);
                VeiculoDAO.salvar(veiculo);

                chamaMenuVeiculos();
                break;
            case 3:
                chamaMenuVeiculosERelacionados();
                break;
        }
    }

    private static Veiculo chamaCadastroVeiculo(int tipoVeiculo) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        int anoFabricacao = parseInt(JOptionPane.showInputDialog(null, "Informe o ano de fabricação (AAAA) do veículo: "));
        long numeroSerie = parseInt(JOptionPane.showInputDialog(null, "Informe o número de série do veículo: "));
        BigDecimal valorFipe = BigDecimal.valueOf(Double.parseDouble(JOptionPane.showInputDialog(null, "Informe o valor da fipe do veículo: ")));
        BigDecimal valorCompra = BigDecimal.valueOf(Double.parseDouble(JOptionPane.showInputDialog(null, "Informe o valor de compra do veículo: ")));
        int hodometro = parseInt(JOptionPane.showInputDialog(null, "Informe o hodômetro do veículo: "));
        String descricao = JOptionPane.showInputDialog(null, "Informe uma descrição sobre o veículo: ");
        TipoCombustivel[] tipoCombustiveis = {
            TipoCombustivel.DIESEL_COMUM,
            TipoCombustivel.DIESEL_ADITIVADO,
            TipoCombustivel.DIESEL_PREMIUM,
            TipoCombustivel.ELETRICO,
            TipoCombustivel.ETANOL_COMUM,
            TipoCombustivel.ETANOL_ADITIVADO,
            TipoCombustivel.GASOLINA_COMUM,
            TipoCombustivel.GASOLINA_ADITIVADA,
            TipoCombustivel.GASOLINA_PREMIUM,
            TipoCombustivel.GNV
        };

        TipoCombustivel tipoCombustivel = (TipoCombustivel) JOptionPane.showInputDialog(null, "Seleciona o tipo de combustível do veículo: ", "Cadastro de veículo", JOptionPane.DEFAULT_OPTION, null, tipoCombustiveis, tipoCombustiveis[0]);
        String placa = JOptionPane.showInputDialog(null, "Informe a placa do veículo: ");
        String auxUltimaRevisao = JOptionPane.showInputDialog(null, "Informe a data da última revisão (DD/MM/AAAA) do veículo: ");
        LocalDate ultimaRevisao = LocalDate.parse(auxUltimaRevisao, pattern);
        CategoriaVeiculo[] categoriasVeiculo = {
            CategoriaVeiculo.LUXO,
            CategoriaVeiculo.COMUM,
            CategoriaVeiculo.UTILITARIO
        };
        CategoriaVeiculo categoria = (CategoriaVeiculo) JOptionPane.showInputDialog(null, "Seleciona a categoria do veículo: ", "Cadastro de veículo", JOptionPane.DEFAULT_OPTION, null, categoriasVeiculo, categoriasVeiculo[0]);

        Object[] nomesModelo = ModeloDAO.findModelosInArrayWithId();
        Object nomeModelo = JOptionPane.showInputDialog(null, "Selecione o modelo: ", "Cadastro de veículo", JOptionPane.QUESTION_MESSAGE, null, nomesModelo, nomesModelo[0]);
        String[] split = nomeModelo.toString().split(" - ");
        int modeloId = parseInt(split[0]);
        Modelo modelo = ModeloDAO.findModeloById(modeloId);

        long id = VeiculoDAO.getTotal() + 1;
        Veiculo veiculo = new Veiculo();
        switch (tipoVeiculo){
            case 0:
                Caminhao caminhao = new Caminhao();
                caminhao.setId(id);
                caminhao.setAnoFabricacao(anoFabricacao);
                caminhao.setNumeroSerie(numeroSerie);
                caminhao.setValorFipe(valorFipe);
                caminhao.setValorCompra(valorCompra);
                caminhao.setHodometro(hodometro);
                caminhao.setDescricao(descricao);
                caminhao.setTipoCombustivel(tipoCombustivel);
                caminhao.setPlaca(placa);
                caminhao.setUltimaRevisao(ultimaRevisao);
                caminhao.setCategoria(categoria);
                caminhao.setTipo(TipoVeiculo.CAMINHAO);
                caminhao.setModelo(modelo);

                veiculo = caminhao;
                break;
            case 1:
                Carro carro = new Carro();
                carro.setId(id);
                carro.setAnoFabricacao(anoFabricacao);
                carro.setNumeroSerie(numeroSerie);
                carro.setValorFipe(valorFipe);
                carro.setValorCompra(valorCompra);
                carro.setHodometro(hodometro);
                carro.setDescricao(descricao);
                carro.setTipoCombustivel(tipoCombustivel);
                carro.setPlaca(placa);
                carro.setUltimaRevisao(ultimaRevisao);
                carro.setCategoria(categoria);
                carro.setTipo(TipoVeiculo.CARRO);
                carro.setModelo(modelo);

                veiculo = carro;
                break;
            case 2:
                Moto moto = new Moto();
                moto.setId(id);
                moto.setAnoFabricacao(anoFabricacao);
                moto.setNumeroSerie(numeroSerie);
                moto.setValorFipe(valorFipe);
                moto.setValorCompra(valorCompra);
                moto.setHodometro(hodometro);
                moto.setDescricao(descricao);
                moto.setTipoCombustivel(tipoCombustivel);
                moto.setPlaca(placa);
                moto.setUltimaRevisao(ultimaRevisao);
                moto.setCategoria(categoria);
                moto.setTipo(TipoVeiculo.MOTO);
                moto.setModelo(modelo);

                veiculo = moto;
                break;
        }
        return veiculo;
    }

    private static void chamaMenuAdicionais() {
        String[] opcoesMenuCadastro = {"Cadastrar adicional", "Incluir em um veículo", "Remover de um veículo", "Voltar"};
        int menu = JOptionPane.showOptionDialog(null, "Escolha uma opção: ",
                "Menu cadastro de adicionais",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastro, opcoesMenuCadastro[0]);
        switch (menu) {
            case 0: //cadastro
                Adicional adicional = chamaCadastroAdicional();
                AdicionalDAO.salvar(adicional);
                chamaMenuAdicionais();
                break;
            case 1: //incluir em um veículo
                chamaIncluiAdicionais();
                chamaMenuAdicionais();
                break;
            case 2: //remover de um veículo
                chamaRemoveAdicionais();
                chamaMenuAdicionais();
                break;
            case 3:
                chamaMenuVeiculosERelacionados();
                break;
            case 4: //voltar
                chamaMenuRelatorios();
                break;
        }
    }

    private static Adicional chamaCadastroAdicional() {
        Adicional adicional = new Adicional();
        String nome = JOptionPane.showInputDialog(null, "Informe o adicional: ");
        String descricao = JOptionPane.showInputDialog(null, "Informe a descrição do adicional: ");
        adicional.setId(AdicionalDAO.getTotal() + 1);
        adicional.setNome(nome);
        adicional.setDescricao(descricao);
        return adicional;
    }

    private static void chamaIncluiAdicionais() {

        Object[] veiculos = VeiculoDAO.findVeiculosInArrayWithId();
        Object nomeVeiculo = JOptionPane.showInputDialog(null, "Selecione o veículo: ", "Inclusão de adicionais", JOptionPane.QUESTION_MESSAGE, null, veiculos, veiculos[0]);
        String[] splitVeiculo = nomeVeiculo.toString().split(" - ");
        int veiculoId = parseInt(splitVeiculo[0]);
        Veiculo veiculo = VeiculoDAO.findVeiculoById(veiculoId);

        int continuar;
        Adicional adicional;
        do {
            Object[] listAdicionais = AdicionalDAO.findAdicionaisInArrayWithId();
            Object nomeAdicional = JOptionPane.showInputDialog(null, "Selecione um adicional: ", "Inclusão de adicionais", JOptionPane.QUESTION_MESSAGE, null, listAdicionais, listAdicionais[0]);
            String[] splitAdicional = nomeAdicional.toString().split(" - ");
            int adicionalId = parseInt(splitAdicional[0]);
            adicional = AdicionalDAO.findAdicionalById(adicionalId);
            VeiculoDAO.incluiAdicional(adicional, veiculo);
            continuar = JOptionPane.showConfirmDialog(null, "Deseja selecionar mais um adicional?", "Inclusão de adicionais", JOptionPane.DEFAULT_OPTION);
        }while(continuar == JOptionPane.YES_OPTION);
    }

    private static void chamaRemoveAdicionais() {

        Object[] veiculos = VeiculoDAO.findVeiculosInArrayWithId();
        Object nomeVeiculo = JOptionPane.showInputDialog(null, "Selecione o veículo: ", "Remoção de adicionais", JOptionPane.QUESTION_MESSAGE, null, veiculos, veiculos[0]);
        String[] splitVeiculo = nomeVeiculo.toString().split(" - ");
        int veiculoId = parseInt(splitVeiculo[0]);
        Veiculo veiculo = VeiculoDAO.findVeiculoById(veiculoId);

        int continuar = 1;
        Adicional adicional;
        do {
            Object[] listAdicionais = AdicionalDAO.findAdicionaisInArrayWithId();
            Object nomeAdicional = JOptionPane.showInputDialog(null, "Selecione um adicional: ", "Remoção de adicionais", JOptionPane.QUESTION_MESSAGE, null, listAdicionais, listAdicionais[0]);
            String[] splitAdicional = nomeAdicional.toString().split(" - ");
            int adicionalId = parseInt(splitAdicional[0]);
            adicional = AdicionalDAO.findAdicionalById(adicionalId);
            VeiculoDAO.removeAdicional(adicional, veiculo);
            continuar = parseInt(JOptionPane.showInputDialog(null, "Deseja selecionar mais um adicional?", "Inclusão de adicionais", JOptionPane.YES_NO_OPTION));
        }while(continuar == 0);
    }

    private static Marca chamaCadastroMarca() {
        Marca marca = new Marca();
        String nome = JOptionPane.showInputDialog(null, "Informe a marca: ");
        marca.setId(MarcaDAO.getTotal() + 1);
        marca.setNome(nome);
        return marca;
    }

    private static Modelo chamaCadastroModelo() {
        Modelo modelo = new Modelo();
        String nome = JOptionPane.showInputDialog(null, "Informe o modelo: ");

        Object[] nomesMarcas = MarcaDAO.findMarcasInArrayWithId();
        Object nomeMarca = JOptionPane.showInputDialog(null, "Selecione a marca: ", "Cadastro de modelo", JOptionPane.QUESTION_MESSAGE, null, nomesMarcas, nomesMarcas[0]);
        String[] split = nomeMarca.toString().split(" - ");
        int marcaId = parseInt(split[0]);
        Marca marca = MarcaDAO.findMarcasById(marcaId);

        modelo.setId(ModeloDAO.getTotal() + 1);
        modelo.setNome(nome);
        modelo.setMarca(marca);
        return modelo;
    }

    private static void chamaRelatorioVeiculos() {

        String listaVeiculos = "";

        List<Veiculo> veiculos = VeiculoDAO.buscarTodos();

        listaVeiculos += "Lista de veículos";

        for (Veiculo veiculo : veiculos) {
            listaVeiculos += "\n" + veiculo.getId() + " - " + veiculo.getModelo().getNome() + " (" + veiculo.getPlaca() + ")";
        }

        JOptionPane.showMessageDialog(null, listaVeiculos);
    }

    private static void chamaMenuRelatorioVeiculosERelacionados() {
        String[] opcoesMenu = {"Cadastrar veículo", "Adicionais", "Marca", "Modelo", "Voltar"};
        int menu = JOptionPane.showOptionDialog(null, "Escolha uma opção: ",
                "Menu veículos e relacionados",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenu, opcoesMenu[0]);

        switch (menu) {
            case 0:
                int totalModelos = ModeloDAO.getTotal();
                if (totalModelos > 0){
                    chamaRelatorioVeiculos();
                }else{
                    JOptionPane.showMessageDialog(null, "Cadastre um modelo para poder cadastrar um veículo", "Menu veículos e relacionados", JOptionPane.INFORMATION_MESSAGE);
                }

                chamaMenuRelatorioVeiculosERelacionados();
                break;
            case 1:
                int totalAdicionais = AdicionalDAO.getTotal();
                if (totalAdicionais > 0){
                    chamaRelatorioAdicionais();
                }else{
                    JOptionPane.showMessageDialog(null, "Cadastre um veículo para poder cadastrar um adicional", "Menu veículos e relacionados", JOptionPane.INFORMATION_MESSAGE);
                }
                chamaMenuRelatorioVeiculosERelacionados();
                break;
            case 2:
                chamaRelatorioMarcas();
                chamaMenuRelatorioVeiculosERelacionados();
                break;
            case 3:
                int totalMarcas = MarcaDAO.getTotal();
                if (totalMarcas > 0){
                    chamaRelatorioModelos();
                }else{
                    JOptionPane.showMessageDialog(null, "Cadastre uma marca para poder cadastrar um modelo", "Menu veículos e relacionados", JOptionPane.INFORMATION_MESSAGE);
                }
                chamaMenuRelatorioVeiculosERelacionados();
                break;
            case 4: //Voltar
                chamaMenuCadastros();
                break;
        }
    }

    private static void chamaRelatorioAdicionais() {

        String listaAdicionais = "";

        List<Adicional> adicionais = AdicionalDAO.buscarTodos();

        listaAdicionais += "Lista de adicionais";

        for (Adicional adicional : adicionais) {
            listaAdicionais += "\n" + adicional.getId() + " - " + adicional.getNome() + " - " + adicional.getDescricao();
        }

        JOptionPane.showMessageDialog(null, listaAdicionais);
    }

    private static void chamaRelatorioModelos() {

        String listaModelos = "";

        List<Modelo> modelos = ModeloDAO.buscarTodos();

        listaModelos += "Lista de modelos";

        for (Modelo adicional : modelos) {
            listaModelos += "\n" + adicional.getId() + " - " + adicional.getNome() + " - marca: " + adicional.getMarca().getNome();
        }

        JOptionPane.showMessageDialog(null, listaModelos);
    }

    private static void chamaRelatorioMarcas() {

        String listaMarcas = "";

        List<Marca> marcas = MarcaDAO.buscarTodos();

        listaMarcas += "Lista de marcas";

        for (Marca marca : marcas) {
            listaMarcas += "\n" + marca.getId() + " - " + marca.getNome();
        }

        JOptionPane.showMessageDialog(null, listaMarcas);
    }
    /*Veículos e relacionados*/

    /* Enderecos e relacionados */
    private static void chamaMenuEnderecosERelacionados() {

        String[] opcoesMenuCadastro = {"Pais", "Estado", "Cidade", "Voltar"};
        int menu = JOptionPane.showOptionDialog(null, "Escolha uma opção: ",
                "Menu Endereços e relacionados",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastro, opcoesMenuCadastro[0]);

        switch (menu) {
            case 0:
                Pais pais = chamaCadastroPais();
                PaisDAO.salvar(pais);

                chamaMenuEnderecosERelacionados();
                break;
            case 1:
                int totalPaises = PaisDAO.getTotal();
                if (totalPaises > 0) {
                    Uf estado = chamaCadastroEstado();
                    UfDAO.salvar(estado);
                }else{
                    JOptionPane.showMessageDialog(null, "Cadastre um país para poder cadastrar um estado", "Menu Endereços e relacionados", JOptionPane.INFORMATION_MESSAGE);
                }

                chamaMenuEnderecosERelacionados();
                break;
            case 2:
                int totalUfs = UfDAO.getTotal();
                if (totalUfs > 0){
                    Cidade cidade = chamaCadastroCidade();
                    CidadeDAO.salvar(cidade);
                }else{
                    JOptionPane.showMessageDialog(null, "Cadastre um estado para poder cadastrar uma cidade", "Menu cadastros", JOptionPane.INFORMATION_MESSAGE);
                }

                chamaMenuEnderecosERelacionados();
                break;
            case 3: //Voltar
                chamaMenuCadastros();
                break;
        }
    }

    private static Pais chamaCadastroPais() {
        Pais pais = new Pais();
        String nome = JOptionPane.showInputDialog(null, "Informe o nome do país: ");
        pais.setId(PaisDAO.getTotal() + 1);
        pais.setNome(nome);
        return pais;
    }

    private static Uf chamaCadastroEstado() {
        Uf estado = new Uf();

        String nome = JOptionPane.showInputDialog(null, "Informe o nome do estado: ");
        String sigla = JOptionPane.showInputDialog(null, "Informe a sigla do estado: ");
        Object[] nomesPaises = PaisDAO.findPaisesInArrayWithId();
        Object nomePais = JOptionPane.showInputDialog(null, "Selecione o país: ", "Cadastro de estado", JOptionPane.QUESTION_MESSAGE, null, nomesPaises, nomesPaises[0]);
        String[] split = nomePais.toString().split(" - ");
        int paisId = parseInt(split[0]);
        Pais pais = PaisDAO.findPaisById(paisId);
        estado.setId(UfDAO.getTotal() + 1);
        estado.setNome(nome);
        estado.setPais(pais);
        estado.setSigla(sigla);
        return estado;
    }

    private static Cidade chamaCadastroCidade() {
        Cidade cidade = new Cidade();
        String nome = JOptionPane.showInputDialog(null, "Informe o nome da cidade: ");
        Object[] nomesUfs = UfDAO.findUfesInArrayWithId();
        Object nomeUf = JOptionPane.showInputDialog(null, "Selecione o estado: ", "Cadastro de cidade", JOptionPane.QUESTION_MESSAGE, null, nomesUfs, nomesUfs[0]);
        String[] split = nomeUf.toString().split(" - ");
        int ufId = parseInt(split[0]);
        Uf Uf = UfDAO.findUfById(ufId);
        cidade.setId(CidadeDAO.getTotal() + 1);
        cidade.setNome(nome);
        cidade.setUf(Uf);
        return cidade;
    }

    private static void chamaMenuRelatorioEnderecos() {
        String[] opcoesMenuCadastro = {"Pais", "Estado", "Cidade", "Voltar"};
        int menu = JOptionPane.showOptionDialog(null, "Escolha uma opção: ",
                "Menu Endereços e relacionados",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastro, opcoesMenuCadastro[0]);

        switch (menu) {
            case 0:
                chamaRelatorioPais();
                //chamaMenuRelatorioEnderecos();
                break;
            case 1:
                chamaRelatorioUf();
                chamaMenuRelatorioEnderecos();
                break;
            case 2:
                chamaRelatorioCidade();
                chamaMenuRelatorioEnderecos();
                break;
            case 3: //Voltar
                chamaMenuRelatorios();
                break;
        }
    }

    private static void chamaRelatorioPais() {
        List<Pais> pais = getPaisDAO().buscarTodos();
        RelatorioPaisForm.emitirRelatorio(pais);
    }

    private static void chamaRelatorioUf() {

        String listaUf = "";

        List<Uf> ufs = UfDAO.buscarTodos();

        listaUf += "Lista de estados";

        for (Uf uf : ufs) {
            listaUf += "\n" + uf.getId() + " - " + uf.getNome() + "sigla: " + uf.getSigla() + " pais: " + uf.getPais().getNome();
        }

        JOptionPane.showMessageDialog(null, listaUf);
    }

    private static void chamaRelatorioCidade() {

        String listaCidade = "";

        List<Cidade> cidades = CidadeDAO.buscarTodos();

        listaCidade += "Lista de cidades";

        for (Cidade cidade : cidades) {
            listaCidade += "\n" + cidade.getId() + " - " + cidade.getNome() + " estado: " + cidade.getUf().getNome() + " pais: " + cidade.getUf().getPais().getNome();
        }

        JOptionPane.showMessageDialog(null, listaCidade);
    }
    /* EnderecosERelacionados */

    /* Aluguel*/
        private static Aluguel chamaCadastroAluguel(int type) {
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            Aluguel aluguel;
            LocalDate dataAluguel = null;

            if (type == 1) { //alugar
                Object[] nomesPessoas = PessoaDAO.findPessoasInArrayWithId();
                Object nomePessoa = JOptionPane.showInputDialog(null, "Informe o locador: ", "Alugar veículo", JOptionPane.QUESTION_MESSAGE, null, nomesPessoas, nomesPessoas[0]);
                String[] split = nomePessoa.toString().split(" - ");
                int pessoaId = parseInt(split[0]);
                Pessoa pessoa = PessoaDAO.findPessoaById(pessoaId);

                Cliente cliente = ClienteDAO.findClienteByPessoa(pessoa);

                List<TipoVeiculo> tiposVeiculo = new ArrayList<>();

                if(cliente.getTipoCarteira() == TipoCarteira.A){
                  tiposVeiculo.add(TipoVeiculo.MOTO);
                }else if(cliente.getTipoCarteira() == TipoCarteira.B){
                   tiposVeiculo.add(TipoVeiculo.CARRO);
                }else if(cliente.getTipoCarteira() == TipoCarteira.C){
                    tiposVeiculo.add(TipoVeiculo.CAMINHAO);
                }else if(cliente.getTipoCarteira() == TipoCarteira.AB){
                    tiposVeiculo.add(TipoVeiculo.CARRO);
                    tiposVeiculo.add(TipoVeiculo.MOTO);
                } else {
                    tiposVeiculo.add(TipoVeiculo.CAMINHAO);
                    tiposVeiculo.add(TipoVeiculo.CARRO);
                    tiposVeiculo.add(TipoVeiculo.MOTO);
                }


                TipoVeiculo tipoVeiculo = (TipoVeiculo) JOptionPane.showInputDialog(null, "Seleciona o tipo do veículo: ", "Alugar veículo", JOptionPane.DEFAULT_OPTION, null, new List[]{tiposVeiculo}, tiposVeiculo.get(0));

                Object[] veiculos = VeiculoDAO.findVeiculosInArrayByTipoVeiculoWithId(tipoVeiculo);
                Object nomeVeiculo = JOptionPane.showInputDialog(null, "Selecione o veículo: ", "Alugar veículo", JOptionPane.QUESTION_MESSAGE, null, veiculos, veiculos[0]);
                String[] splitVeiculo = nomeVeiculo.toString().split(" - ");
                int veiculoId = parseInt(splitVeiculo[0]);
                Veiculo veiculo = VeiculoDAO.findVeiculoById(veiculoId);

                BigDecimal divisor = new BigDecimal(5);
                BigDecimal fivePercent = BigDecimal.valueOf(0.05);
                BigDecimal oneHundred = BigDecimal.valueOf(100);
                BigDecimal fifty = BigDecimal.valueOf(50);
                BigDecimal auxFipe = veiculo.getValorFipe().divide(divisor);

                BigDecimal auxCalc = auxFipe.multiply(fivePercent);
                BigDecimal auxValor = auxCalc.divide(oneHundred);
                BigDecimal valor = auxValor.add(fifty);
                JOptionPane.showMessageDialog(null, "O valor diario a ser pago no aluguel será de: " + valor, "Alugar veículo", JOptionPane.INFORMATION_MESSAGE);

                String auxDataAluguel = JOptionPane.showInputDialog(null, "Informe a data do aluguel: (DD/MM/AAAA)");
                dataAluguel = LocalDate.parse(auxDataAluguel, pattern);

                aluguel = new Aluguel();

                aluguel.setPessoa(pessoa);
                aluguel.setVeiculo(veiculo);
                aluguel.setDataAluguel(dataAluguel);
                aluguel.setHodometroInicial(veiculo.getHodometro());
                aluguel.setValorEstimado(valor);
                aluguel.setStatus(StatusAluguel.ALUGADO);

                veiculo.setAlugado(true);
            }else { //devolver
                Object[] nomesPessoas = PessoaDAO.findPessoasInArrayWithId();
                Object nomePessoa = JOptionPane.showInputDialog(null, "Informe o locador: ", "Devolver veículo", JOptionPane.QUESTION_MESSAGE, null, nomesPessoas, nomesPessoas[0]);
                String[] split = nomePessoa.toString().split(" - ");
                int pessoaId = parseInt(split[0]);
                Pessoa pessoa = PessoaDAO.findPessoaById(pessoaId);

                Object[] veiculos = VeiculoDAO.findVeiculosInArrayWithId();
                Object nomeVeiculo = JOptionPane.showInputDialog(null, "Selecione o veículo: ", "Devolver veículo", JOptionPane.QUESTION_MESSAGE, null, veiculos, veiculos[0]);
                String[] splitVeiculo = nomeVeiculo.toString().split(" - ");
                int veiculoId = parseInt(splitVeiculo[0]);
                Veiculo veiculo = VeiculoDAO.findVeiculoById(veiculoId);

                aluguel = AluguelDAO.getAluguelByPessoaAndVeiculo(pessoa, veiculo);

                String[] opcoesDevolver = {"Não", "Sim"};
                int opt = JOptionPane.showOptionDialog(null, "O condutor teve problemas durante o uso do veículo: ",
                "Devolver veículo",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesDevolver, opcoesDevolver[0]);
                if (opt == 0) {
                    aluguel.setStatus(StatusAluguel.DESALUGADO);
                }

                veiculo.setAlugado(false);
            }

            return aluguel;
        }

        private static void chamaMenuRelatorioAluguel() {
            List<Aluguel> aluguel = AluguelDAO.buscarTodos();
            RelatorioAluguelForm.emitirRelatorio(aluguel);
        }
    /* Aluguel*/

    /*Menus principais e relacionados*/
    private static void chamaMenuPrincipal() {
        String[] opcoesMenu = {"Cadastros", "Processos", "Relatorios", "Sair"};

        int opcao = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Principal",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenu, opcoesMenu[0]);
        switch (opcao) {
            case 0: //Cadastros
                chamaMenuCadastros();
                chamaMenuPrincipal();
                break;
            case 1: //Processos
                chamaMenuProcessos();
                chamaMenuPrincipal();
                break;
            case 2: //Relatorios
                chamaMenuRelatorios();
                chamaMenuPrincipal();
                break;
            case 3: //SAIR

                break;
        }
    }

    private static void chamaMenuCadastros() {
        String[] opcoesMenuCadastro = {"Cliente", "Funcionário", "Veículo", "Endereço", "Voltar"};
        int menu = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Cadastros",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastro, opcoesMenuCadastro[0]);
        int totalPaises = PaisDAO.getTotal();
        switch (menu) {
            case 0: //cliente
                if (totalPaises > 0){
                    chamaMenuCliente();
                }else{
                    JOptionPane.showMessageDialog(null, "Cadastre um país para poder cadastrar uma pessoa", "Menu cadastros", JOptionPane.INFORMATION_MESSAGE);

                    Pais pais = chamaCadastroPais();
                    PaisDAO.salvar(pais);

                    chamaMenuEnderecosERelacionados();
                }
                chamaMenuCadastros();
                break;
            case 1: //funcionário
                if (totalPaises > 0){
                    Pessoa pessoaFisica = chamaCadastroPessoa(1);
                    PessoaDAO.salvar(pessoaFisica);
                    Funcionario funcionario = chamaCadastroFuncionario(pessoaFisica);
                    FuncionarioDAO.salvar(funcionario);
                }else{
                    JOptionPane.showMessageDialog(null, "Cadastre um país para poder cadastrar um funcinário", "Menu cadastros", JOptionPane.INFORMATION_MESSAGE);

                    Pais pais = chamaCadastroPais();
                    PaisDAO.salvar(pais);

                    chamaMenuEnderecosERelacionados();
                }
                chamaMenuCadastros();
                break;
            case 2://veículos e relacionados
                chamaMenuVeiculosERelacionados();
                chamaMenuCadastros();
                break;
            case 3: //endereço e relacionados
                chamaMenuEnderecosERelacionados();
                chamaMenuPrincipal();
                break;
            case 4: //Voltar
                chamaMenuPrincipal();
                break;
        }
    }

    private static void chamaMenuProcessos() {
        String[] opcoesMenuProcesso = {"Alugar carro", "Devolver carro", "Voltar"};
        int menu_processos = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Processos",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuProcesso, opcoesMenuProcesso[0]);
        Aluguel aluguel;
        switch (menu_processos) {
            case 0:
                aluguel = chamaCadastroAluguel(1); // alugar
                chamaMenuProcessos();
                break;
            case 1:
                aluguel = chamaCadastroAluguel(0); // devolver
                chamaMenuProcessos();
                break;
            case 2: //Voltar
                chamaMenuPrincipal();
                break;
        }
    }

    public static void chamaMenuRelatorios() {
        String[] opcoesMenuProcesso = {"Cliente", "Funcionário", "Veículo", "Endereço", "Aluguel", "Voltar"};
        int menu = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Relatórios",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuProcesso, opcoesMenuProcesso[0]);

        switch (menu) {
            case 0:
                chamaRelatorioPessoa(0);
                chamaMenuRelatorios();
                break;
            case 1:
                chamaRelatorioPessoa(1);
                chamaMenuRelatorios();
                break;
            case 2:
                chamaMenuRelatorioVeiculosERelacionados();
                chamaMenuRelatorios();
                break;
            case 3: //Endereços
                chamaMenuRelatorioEnderecos();
                //chamaMenuRelatorios();
                break;
            case 4: //Aluguel
                chamaMenuRelatorioAluguel();
                chamaMenuRelatorios();
                break;
            case 5: //Voltar
                chamaMenuPrincipal();
                break;
        }
    }

    private static void checaSenhaUsuario(Object usuarioLogado) {
        String senhaDigitada = JOptionPane.showInputDialog(null, "Informe a senha do usuario (" + usuarioLogado + ")");
        Usuario usuarioByLogin = UsuarioDAO.findUsuarioByLogin((String) usuarioLogado);

        if (usuarioByLogin.getSenha().equals(senhaDigitada)) {
            chamaMenuPrincipal();
        } else {
            JOptionPane.showMessageDialog(null, "Senha incorreta!");
            checaSenhaUsuario(usuarioLogado);
        }

    }

    private static Object chamaSelecaoUsuario() {
        Object[] selectionValues = UsuarioDAO.findUsuariosSistemaInArray();
        String initialSelection = (String) selectionValues[0];
        Object selection = JOptionPane.showInputDialog(null, "Selecione o usuario?",
                "Aluguel de veículos", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
        return selection;
    }
    /*Menus principais e relacionados*/

    public static PaisDAO getPaisDAO() {
        PaisDAO paisDAO = new PaisDAO();
        return paisDAO;
    }
}