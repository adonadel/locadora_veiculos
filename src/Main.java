import model.*;
import repository.*;
import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) {
        Object usuarioLogado = chamaSelecaoUsuario();
        checaSenhaUsuario(usuarioLogado);
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

        Object[] nomesCidades = CidadeDAO.findCidadesInArrayWithId();
        Object nomeCidade = JOptionPane.showInputDialog(null, "Selecione o estado: ", "Cadastro de pessoas", JOptionPane.QUESTION_MESSAGE, null, nomesCidades, nomesCidades[0]);
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
            CNH = JOptionPane.showInputDialog(null, "Informe a CNH da pessoa: ");
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

        switch (menu) {
            case 0:
                Pessoa pessoaFisica = chamaCadastroPessoa(0);
                PessoaDAO.salvar(pessoaFisica);

                Cliente clienteFisico = new Cliente();
                clienteFisico.setPessoa(pessoaFisica);
                clienteFisico.setId(ClienteDAO.getTotal() + 1);
                ClienteDAO.salvar(clienteFisico);

                chamaMenuCadastros();
                break;
            case 1:
                Pessoa pessoaJuridica = chamaCadastroPessoa(1);
                PessoaDAO.salvar(pessoaJuridica);

                Cliente clienteJuridico = new Cliente();
                clienteJuridico.setPessoa(pessoaJuridica);
                clienteJuridico.setId(ClienteDAO.getTotal() + 1);
                ClienteDAO.salvar(clienteJuridico);
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
        String[] opcoesMenuCadastro = {"Cadastrar veículo", "Adicionais", "Marca", "Modelo", "Voltar"};
        int menu = JOptionPane.showOptionDialog(null, "Escolha uma opção: ",
                "Menu veículos e relacionados",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastro, opcoesMenuCadastro[0]);

        switch (menu) {
            case 0:
                chamaMenuVeiculos();
                chamaMenuVeiculosERelacionados();
                break;
            case 1:
                chamaMenuAdicionais();
                chamaMenuVeiculosERelacionados();
                break;
            case 2:
                Marca marca = chamaCadastroMarca();
                MarcaDAO.salvar(marca);
                chamaMenuVeiculosERelacionados();
                break;
            case 3:
                Modelo modelo = chamaCadastroModelo();
                ModeloDAO.salvar(modelo);
                chamaMenuVeiculosERelacionados();
                break;
            case 4: //Voltar
                chamaMenuCadastros();
                break;
        }
    }

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
                break;
            case 3:
                chamaMenuVeiculosERelacionados();
                break;
        }
    }

    private static Veiculo chamaCadastroVeiculo(int tipoVeiculo) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        int anoFabricacao = parseInt(JOptionPane.showInputDialog(null, "Informe o ano de fabricação (DD/MM/AAAA) do veículo: "));
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
        CategoriaVeiculo categoria = (CategoriaVeiculo) JOptionPane.showInputDialog(null, "Seleciona o tipo de combustível do veículo: ", "Cadastro de veículo", JOptionPane.DEFAULT_OPTION, null, categoriasVeiculo, categoriasVeiculo[0]);

        Object[] nomesModelo = ModeloDAO.findModelosInArray();
        Object nomeModelo = JOptionPane.showInputDialog(null, "Selecione o modelo: ", "Cadastro de veículo", JOptionPane.QUESTION_MESSAGE, null, nomesModelo, nomesModelo[0]);
        String[] split = nomeModelo.toString().split(" - ");
        int modeloId = parseInt(split[0]);
        Modelo modelo = ModeloDAO.findModeloById(modeloId);

        long id = VeiculoDAO.getTotal() + 1;
        Veiculo veiculo = new Veiculo();
        switch (tipoVeiculo){
            case 1:
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
            case 2:
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
            case 3:
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
        String[] opcoesMenuCadastro = {"Cadastrar adicional", "Incluir em um veículo"};
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
            case 3:
                chamaMenuVeiculosERelacionados();
                break;
        }
    }

    private static Adicional chamaCadastroAdicional() {
        Adicional adicional = new Adicional();
        String nome = JOptionPane.showInputDialog(null, "Informe o adicional: ");
        String descricao = JOptionPane.showInputDialog(null, "Informe a descrição do adicional: ");
        BigDecimal valor = BigDecimal.valueOf(Double.parseDouble(JOptionPane.showInputDialog(null, "Informe o valor do adicional: ")));
        adicional.setId(AdicionalDAO.getTotal() + 1);
        adicional.setNome(nome);
        adicional.setDescricao(descricao);
        adicional.setValor(valor);
        return adicional;
    }

    private static void chamaIncluiAdicionais() {

        Object[] veiculos = VeiculoDAO.findVeiculosInArrayWithId();
        Object nomeVeiculo = JOptionPane.showInputDialog(null, "Selecione o veículo: ", "Inclusão de adicionais", JOptionPane.QUESTION_MESSAGE, null, veiculos, veiculos[0]);
        String[] splitVeiculo = nomeVeiculo.toString().split(" - ");
        int veiculoId = parseInt(splitVeiculo[0]);
        Veiculo veiculo = VeiculoDAO.findVeiculoById(veiculoId);

        int continuar = 1;
        Adicional adicional;
        do {
            Object[] listAdicionais = AdicionalDAO.findAdicionaisInArrayWithId();
            Object nomeAdicional = JOptionPane.showInputDialog(null, "Selecione um adicional: ", "Inclusão de adicionais", JOptionPane.QUESTION_MESSAGE, null, listAdicionais, listAdicionais[0]);
            String[] splitAdicional = nomeAdicional.toString().split(" - ");
            int adicionalId = parseInt(splitAdicional[0]);
            adicional = AdicionalDAO.findAdicionalById(adicionalId);
            VeiculoDAO.incluiAdicional(adicional, veiculo);
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
        String[] opcoesMenuCadastro = {"Cadastrar veículo", "Adicionais", "Marca", "Modelo", "Voltar"};
        int menu = JOptionPane.showOptionDialog(null, "Escolha uma opção: ",
                "Menu veículos e relacionados",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastro, opcoesMenuCadastro[0]);

        switch (menu) {
            case 0:
                chamaRelatorioVeiculos();
                chamaMenuRelatorioVeiculosERelacionados();
                break;
            case 1:
                chamaRelatorioAdicionais();
                chamaMenuRelatorioVeiculosERelacionados();
                break;
            case 2:
                chamaRelatorioMarcas();
                chamaMenuRelatorioVeiculosERelacionados();
                break;
            case 3:
                chamaRelatorioModelos();
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
                Uf estado = chamaCadastroEstado();
                UfDAO.salvar(estado);

                chamaMenuEnderecosERelacionados();
                break;
            case 2:
                Cidade cidade = chamaCadastroCidade();
                CidadeDAO.salvar(cidade);

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
                chamaMenuRelatorioEnderecos();
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

        String listaPais = "";

        List<Pais> paises = PaisDAO.buscarTodos();

        listaPais += "Lista de paises";

        for (Pais pais : paises) {
            listaPais += "\n" + pais.getId() + " - " + pais.getNome();
        }

        JOptionPane.showMessageDialog(null, listaPais);
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

        switch (menu) {
            case 0: //cliente
                chamaMenuCliente();
                chamaMenuCadastros();
                break;
            case 1: //funcionário
                Pessoa pessoaFisica = chamaCadastroPessoa(1);
                PessoaDAO.salvar(pessoaFisica);
                Funcionario funcionario = chamaCadastroFuncionario(pessoaFisica);
                FuncionarioDAO.salvar(funcionario);
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

        switch (menu_processos) {
            case 0:
                chamaMenuProcessos();
                break;
            case 1:
                chamaMenuProcessos();
                break;
            case 2: //Voltar
                chamaMenuPrincipal();
                break;
        }
    }

    private static void chamaMenuRelatorios() {
        String[] opcoesMenuProcesso = {"Cliente", "Funcionário", "Veículo", "Endereço", "Voltar"};
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
            case 3: //Voltar
                chamaMenuRelatorioEnderecos();
                chamaMenuRelatorios();
                break;
            case 4: //Voltar
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
}