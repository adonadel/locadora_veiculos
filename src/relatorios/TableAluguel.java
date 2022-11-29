package relatorios;

import model.Aluguel;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class TableAluguel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    public static final int INDEX_ID = 0;
    public static final int INDEX_VALOR_ESTIMADO = 1;
    public static final int INDEX_VALOR_FINAL = 2;
    public static final int INDEX_DATA_ALUGUEL = 3;
    public static final int INDEX_DATA_DEVOLUCAO = 4;
    public static final int INDEX_HODOMETRO_INICIAL = 5;
    public static final int INDEX_HODOMETRO_FINAL = 6;
    public static final int INDEX_STATUS = 7;
    public static final int INDEX_PESSOA = 8;
    public static final int INDEX_VEICULO = 9;
    public static final int INDEX_ESCONDIDO = 10;

    protected String[] nomeColunas;
    protected Vector<Aluguel> vetorDados;

    public TableAluguel(String[] columnNames, Vector<Aluguel> vetorDados) {
        this.nomeColunas = columnNames;
        this.vetorDados = vetorDados;
    }

    @Override
    public String getColumnName(int column) {
        return nomeColunas[column];
    }

    @Override
    public boolean isCellEditable(int linha, int coluna) {
        if (coluna == INDEX_ESCONDIDO) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Aluguel registroAluguel = (Aluguel) vetorDados.get(linha);
        switch (coluna) {
            case INDEX_ID:
                return registroAluguel.getId();
            case INDEX_VALOR_ESTIMADO:
                return registroAluguel.getValorEstimado();
            case INDEX_VALOR_FINAL:
                return registroAluguel.getValorFinal();
            case INDEX_DATA_ALUGUEL:
                return registroAluguel.getDataAluguel();
            case INDEX_DATA_DEVOLUCAO:
                return registroAluguel.getDataDevolucao();
            case INDEX_HODOMETRO_INICIAL:
                return registroAluguel.getHodometroInicial();
            case INDEX_HODOMETRO_FINAL:
                return registroAluguel.getHodometroFinal();
            case INDEX_STATUS:
                return registroAluguel.getStatus();
            case INDEX_PESSOA:
                return registroAluguel.getPessoa();
            case INDEX_VEICULO:
                return registroAluguel.getVeiculo();
            default:
                return new Object();
        }
    }

    @Override
    public int getRowCount() {
        return vetorDados.size();
    }

    @Override
    public int getColumnCount() {
        return nomeColunas.length;
    }
}