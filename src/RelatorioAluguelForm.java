import relatorios.TableAluguel;
import model.Aluguel;
import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Vector;

public class RelatorioAluguelForm extends JPanel {
        private static final long serialVersionUID = 1L;
        public static final String[] nomeColunas =
                {"ID", "Valor Estimado", "Valor Final", "Data Aluguel", "Data Devolução", "Hodometro Inicial",
                 "Hodometro Final", "Status", "Pessoa", "Veiculo"};

        protected JTable table;
        protected JScrollPane scroller;
        protected TableAluguel tabela;

        public RelatorioAluguelForm(Vector<Aluguel> vetorDados) {
            iniciarComponentes(vetorDados);
        }

        public void iniciarComponentes(Vector<Aluguel> vetorDados) {
            tabela = new TableAluguel(nomeColunas, vetorDados);
            table = new JTable();
            table.setModel(tabela);
            table.setSurrendersFocusOnKeystroke(true);
            scroller = new javax.swing.JScrollPane(table);
            table.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 300));
        }

        public static void emitirRelatorio(List<Aluguel> alugueis) {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                JFrame frame = new JFrame("Relatório");

                frame.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent evt) {
                        frame.setVisible(false);
                        Main.chamaMenuRelatorios();
                    }
                });
                Vector<Aluguel> vetorDados = new Vector<Aluguel>();
                for (Aluguel aluguel : alugueis) {
                    vetorDados.add(aluguel);
                }

                frame.getContentPane().add(new RelatorioAluguelForm(vetorDados));
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}

