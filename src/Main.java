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

    private static void chamaRelatorioPessoa() {
        List<Pessoa> pessoas = PessoaDAO.buscarTodos();
        String listaPessoas = "Lista de Pessoas";
        for (Pessoa pessoa : pessoas) {
            listaPessoas += "\n" + pessoa.getNome() + "  tipo: " + pessoa.getTipo() + "   documento: " + pessoa.getDocumento();
        }
        JOptionPane.showMessageDialog(null, listaPessoas);
    }

    private static void chamaMenuPrincipal() {
        String[] opcoesMenu = {"Cadastros", "Processos", "Relatorios", "Sair"};
        int opcao = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Principal",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenu, opcoesMenu[0]);
        switch (opcao) {
            case 0: //Cadastros
                chamaMenuCadastros();
                break;
            case 1: //Processos
                chamaMenuProcessos();
                break;
            case 2: //Relatorios
                chamaMenuRelatorios();
                break;
            case 3: //SAIR

                break;
        }
    }

    private static void chamaMenuCadastros() {
        String[] opcoesMenuCadastro = {"Cliente", "Funcionário", "Veículo", "Endereço", "Voltar"};
        int menuCadastro = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Cadastros",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastro, opcoesMenuCadastro[0]);

        switch (menuCadastro) {
            case 0: //cliente
                chamaMenuCliente();
                chamaMenuCadastros();
                break;
            case 1: //funcionário
                Pessoa pessoaFisica = chamaCadastroPessoa(0);
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

    private static void chamaMenuCliente() {
        String[] opcoesMenuCadastro = {"Física", "Jurídica", "Voltar"};
        int menuCadastro = JOptionPane.showOptionDialog(null, "Cadastrar cliente como pessoa:",
                "Menu Cadastros",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastro, opcoesMenuCadastro[0]);

        switch (menuCadastro) {
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

    private static void chamaMenuVeiculosERelacionados() {
        String[] opcoesMenuCadastro = {"Cadastrar veículo", "Marca", "Modelo", "Voltar"};
        int menuCadastro = JOptionPane.showOptionDialog(null, "Escolha uma opção: ",
                "Menu veículos e relacionados",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastro, opcoesMenuCadastro[0]);

        switch (menuCadastro) {
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

    private static void chamaMenuEnderecosERelacionados() {
        String[] opcoesMenuCadastro = {"Pais", "Estado", "Cidade", "Voltar"};
        int menuCadastro = JOptionPane.showOptionDialog(null, "Escolha uma opção: ",
                "Menu Endereços e relacionados",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastro, opcoesMenuCadastro[0]);

        switch (menuCadastro) {
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

    private static Marca chamaCadastroMarca() {
        Marca marca = new Marca();
        String nome = JOptionPane.showInputDialog(null, "Informe a marca: ");
        marca.setId(MarcaDAO.getTotal() + 1);
        marca.setNome(nome);
        return marca;
    }

    private static Pais chamaCadastroPais() {
        Pais pais = new Pais();
        String nome = JOptionPane.showInputDialog(null, "Informe o nome do país: ");
        String ibge = JOptionPane.showInputDialog(null, "Informe a inscrição IBGE do país: ");
        pais.setId(PaisDAO.getTotal() + 1);
        pais.setNome(nome);
        pais.setIbge(ibge);
        return pais;
    }

    private static Uf chamaCadastroEstado() {
        Uf estado = new Uf();
        String nome = JOptionPane.showInputDialog(null, "Informe o nome do estado: ");
        String sigla = JOptionPane.showInputDialog(null, "Informe a sigla do estado: ");
        Object[] nomesPaises = PaisDAO.findPaisesInArrayWithId();
        String nomePais = JOptionPane.showOptionDialog(null, "Selecione o país: ", "Cadastro de estado", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, nomesPaises, nomesPaises);
        String[] split = nomePais.split("-");
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
        Object[] nomesCidades = UfDAO.findUfesInArrayWithId();
        String nomeUf = JOptionPane.showOptionDialog(null, "Selecione a cidade: ", "Cadastro de cidade", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, nomesCidades, nomesCidades);
        String[] split = nomeUf.split("-");
        int ufId = parseInt(split[0]);
        Uf Uf = UfDAO.findUfById(ufId);
        cidade.setId(UfDAO.getTotal() + 1);
        cidade.setNome(nome);
        cidade.setUf(Uf);
        return cidade;
    }

//    private static Cidade chamaCadastroCidade() {
//        Cidade cidade = new Cidade();
//        String nome = JOptionPane.showInputDialog(null, "Informe o nome da cidade: ");
//        cidade.setId(CidadeDAO.getTotal() + 1);
//        cidade.setNome(nome);
//        return cidade;
//    }

    private static Modelo chamaCadastroModelo() {
        Modelo modelo = new Modelo();
        String nome = JOptionPane.showInputDialog(null, "Informe o modelo: ");
        modelo.setId(ModeloDAO.getTotal() + 1);
        modelo.setNome(nome);
        return modelo;
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

    private static void chamaMenuProcessos() {
        String[] opcoesMenuProcesso = {"Gerar Sinistro", "Baixar Seguro", "Voltar"};
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
        String[] opcoesMenuProcesso = {"Pessoas", "Seguradoras", "Seguros", "Voltar"};
        int menu_processos = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Relatórios",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuProcesso, opcoesMenuProcesso[0]);

        switch (menu_processos) {
            case 0:
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
                "SeguradoraAPP", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);
        return selection;
    }

}