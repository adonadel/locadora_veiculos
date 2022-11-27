import model.Pais;
import relatorios.TablePais;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Vector;

public class RelatorioPaisForm extends JPanel{
        private static final long serialVersionUID = 1L;
        public static final String[] nomeColunas = {"ID", "Nome"};

        protected JTable table;
        protected JScrollPane scroller;
        protected TablePais tabela;

        public RelatorioPaisForm(Vector<Pais> vetorDados) {
            iniciarComponentes(vetorDados);
        }

        public void iniciarComponentes(Vector<Pais> vetorDados) {
            tabela = new TablePais(nomeColunas, vetorDados);
            table = new JTable();
            table.setModel(tabela);
            table.setSurrendersFocusOnKeystroke(true);
            scroller = new javax.swing.JScrollPane(table);
            table.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 300));
        }

        public static void emitirRelatorio(List<Pais> paises) {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                JFrame frame = new JFrame("Relatório");

                frame.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent evt) {
                        frame.setVisible(false);
                        Main.chamaMenuRelatorios();
                    }
                });

                Vector<Pais> vetorDados = new Vector<Pais>();
                for (Pais pais : paises) {
                    vetorDados.add(pais);
                }

                frame.getContentPane().add(new RelatorioPaisForm(vetorDados));
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
