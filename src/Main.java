import model.*;
import repository.*;
import javax.swing.*;
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
        String endereco = JOptionPane.showInputDialog(null, "Informe o endereço da pessoa: ");
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
            pessoaFisica.setEndereco(endereco);
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
            return pessoaJuridica;
        }
    }

    private static Funcionario chamaCadastroFuncionario(Pessoa pessoa) {
        Funcionario funcionario = new Funcionario();
        funcionario.setPessoa(pessoa);
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
                ClienteDAO.salvar(clienteFisico);

                chamaMenuCadastros();
                break;
            case 1:
                Pessoa pessoaJuridica = chamaCadastroPessoa(1);
                PessoaDAO.salvar(pessoaJuridica);

                Cliente clienteJuridico = new Cliente();
                clienteJuridico.setPessoa(pessoaJuridica);
                ClienteDAO.salvar(clienteJuridico);

                chamaMenuCadastros();
                break;
            case 2: //Voltar
                chamaMenuCadastros();
                break;
        }
    }

    private static void chamaRelatorioPessoa(int tipoPessoa, int categoria) { // tipoPessoa = Física/Jurídica | categoria = Cliente/Funcionario
        String tipoPessoaStr = "";
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
        String[] opcoesMenuCadastro = {"Cadastrar veículo", "Marca", "Modelo", "Voltar"};
        int menu = JOptionPane.showOptionDialog(null, "Escolha uma opção: ",
                "Menu veículos e relacionados",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastro, opcoesMenuCadastro[0]);

        switch (menu) {
            case 0:
//                chamaMenuVeiculos();
                chamaMenuCadastros();
                break;
            case 1:
                Marca marca = chamaCadastroMarca();
                MarcaDAO.salvar(marca);
                chamaMenuCadastros();
                break;
            case 2:
                Modelo modelo = chamaCadastroModelo();
                ModeloDAO.salvar(modelo);
                chamaMenuCadastros();
                break;
            case 3: //Voltar
                chamaMenuCadastros();
                break;
        }
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
        System.out.println(ufId);
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