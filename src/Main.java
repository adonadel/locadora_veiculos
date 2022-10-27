import model.*;
import repository.*;
import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

        Object[] nomesPaises = PaisDAO.findPaisesInArray();
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

    private static void chamaRelatorioPessoa(int tipoPessoa, int categoria) { // tipoPessoa = Física/Jurídica | categoria = Cliente/Funcionario
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

    private static void chamaMenuRelatoriosClientes() {
        String[] opcoesMenu = {"Física", "Jurídica", "Voltar"};
        int menu = JOptionPane.showOptionDialog(null, "Buscar relatório de clientes como pessoa:",
                "Menu Relatórios - Clientes",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenu, opcoesMenu[0]);

        switch (menu) {
            case 0:
                chamaRelatorioPessoa(0, 1);
                chamaMenuRelatoriosClientes();
                break;
            case 1:
                chamaRelatorioPessoa(1, 1);
                chamaMenuRelatoriosClientes();
                break;
            case 2:
                chamaMenuRelatorios();
                break;
            case 3: //Voltar
                chamaMenuPrincipal();
                break;
        }
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
                chamaMenuCadastros();
                break;
            case 1:
                Adicional adicional = chamaCadastroAdicional();
                AdicionalDAO.salvar(adicional);
                chamaMenuCadastros();
                break;
            case 2:
                Marca marca = chamaCadastroMarca();
                MarcaDAO.salvar(marca);
                chamaMenuCadastros();
                break;
            case 3:
                Modelo modelo = chamaCadastroModelo();
                ModeloDAO.salvar(modelo);
                chamaMenuCadastros();
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

        switch (menu) {
            case 0: //caminhão
                Veiculo veiculo = chamaCadastroVeiculo(1);
                VeiculoDAO.salvar(veiculo);

                chamaMenuVeiculos();
                break;
            case 1: //carro
                chamaCadastroVeiculo(2);
                chamaMenuVeiculos();
                break;
            case 2: //moto
                chamaCadastroVeiculo(3);
                chamaMenuVeiculos();
                break;
            case 3:
                chamaMenuVeiculosERelacionados();
                break;
        }
    }

    private static Veiculo chamaCadastroVeiculo(int tipoVeiculo) {
        String nome = JOptionPane.showInputDialog(null, "Informe o nome da pessoa: ");

//        LocalDate dataNasc = null;
//        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        dataNasc = LocalDate.parse(auxDataNasc, pattern);

        long id = VeiculoDAO.getTotal() + 1;
        Veiculo veiculo = new Veiculo();
        switch (tipoVeiculo){
            case 1:
                Caminhao caminhao = new Caminhao();
                caminhao.setId(id);
                caminhao.setPeso(peso);
                caminhao.setDataFabricacao(dataFabricacao);
                caminhao.setNumeroSerie(numeroSerie);
                caminhao.setValorFipe(valorFipe);
                caminhao.setValorCompra(valorCompra);
                caminhao.setHodometro(hodometro);
                caminhao.setDescricao(descricao);
                caminhao.setTipoCombustivel(tipoCombustivel);
                caminhao.setPlaca(placa);
                caminhao.setUltimaRevisao(ultimaRevisao);
                caminhao.setPesoSuportado(pesoSuportado);
                caminhao.setTrocaOleokm(trocaOleoKm);
                caminhao.setCategoria(categoria);
                caminhao.setTipo(tipoVeiculo);
                caminhao.setModelo(modelo);

                veiculo = caminhao;
                break;
            case 2:
                Carro carro = new Carro();
                carro.setId(id);
                carro.setPeso(peso);
                carro.setDataFabricacao(dataFabricacao);
                carro.setNumeroSerie(numeroSerie);
                carro.setValorFipe(valorFipe);
                carro.setValorCompra(valorCompra);
                carro.setHodometro(hodometro);
                carro.setDescricao(descricao);
                carro.setTipoCombustivel(tipoCombustivel);
                carro.setPlaca(placa);
                carro.setUltimaRevisao(ultimaRevisao);
                carro.setPesoSuportado(pesoSuportado);
                carro.setTrocaOleokm(trocaOleoKm);
                carro.setCategoria(categoria);
                carro.setTipo(tipo);
                carro.setModelo(modelo);

                veiculo = carro;
                break;
            case 3:
                Moto moto = new Moto();
                moto.setId(id);
                moto.setPeso(peso);
                moto.setDataFabricacao(dataFabricacao);
                moto.setNumeroSerie(numeroSerie);
                moto.setValorFipe(valorFipe);
                moto.setValorCompra(valorCompra);
                moto.setHodometro(hodometro);
                moto.setDescricao(descricao);
                moto.setTipoCombustivel(tipoCombustivel);
                moto.setPlaca(placa);
                moto.setUltimaRevisao(ultimaRevisao);
                moto.setPesoSuportado(pesoSuportado);
                moto.setTrocaOleokm(trocaOleoKm);
                moto.setCategoria(categoria);
                moto.setTipo(tipo);
                moto.setModelo(modelo);

                veiculo = moto;
                break;
        }
        return veiculo;
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
        modelo.setId(ModeloDAO.getTotal() + 1);
        modelo.setNome(nome);
        return modelo;
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
        Object[] nomesPaises = PaisDAO.findPaisesInArray();
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
                chamaMenuRelatoriosClientes();
                chamaMenuRelatorios();
                break;
            case 1:
                chamaMenuRelatorios();
                break;
            case 2:
                chamaMenuRelatorios();
                break;
            case 3: //Voltar
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