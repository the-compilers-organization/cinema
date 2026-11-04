package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Sala;
import model.Sessao;
import controller.IFachada;


// Tela "Programação": sessões de cada sala (grade de horários)

public class ProgramacaoView extends JPanel implements Atualizavel {

    private JComboBox<Object> cbSala = new JComboBox<Object>();
    private DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"Sala", "Tipo", "Horário", "Filme", "Ingresso", "Situação"}, 0) {
        @Override
        public boolean isCellEditable(int linha, int coluna) {
            return false;
        }
    };
    private JTable tabela = new JTable(modelo);

    public ProgramacaoView(IFachada fachada) {
        setLayout(new BorderLayout(0, 12));

        JPanel topo = new JPanel(new BorderLayout());
        topo.setOpaque(false);
        topo.add(Estilo.cabecalho("Programação de hoje",
                "Escolha uma sala para ver as sessões. Horários sem sessão não aparecem."), BorderLayout.CENTER);

        JPanel filtro = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        filtro.setOpaque(false);
        filtro.add(Estilo.campo("Sala", cbSala));
        topo.add(filtro, BorderLayout.SOUTH);
        add(topo, BorderLayout.NORTH);

        Estilo.estilizarTabela(tabela);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(300);
        add(Estilo.rolagem(tabela), BorderLayout.CENTER);

        //  eventos da tela 
        this.fachada = fachada;

        getCbSala().addItem(TODAS);
        for (Sala sala : fachada.getSalas()) {
            getCbSala().addItem(sala);
        }
        getCbSala().addActionListener(e -> atualizar());
    }

    public JComboBox<Object> getCbSala() {
        return cbSala;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

  
    //  EVENTOS DA TELA - chamam a IFachada
  
    private static final String TODAS = "Todas as salas";
    private IFachada fachada;

    @Override
    public void atualizar() {
        getModelo().setRowCount(0);
        Object escolhida = getCbSala().getSelectedItem();

        for (Sala sala : fachada.getSalas()) {
            if (escolhida instanceof Sala && escolhida != sala) {
                continue; // filtro por sala
            }
            // getSessoesDisponiveis() ignora os horários vazios (null)
            for (Sessao sessao : sala.getSessoesDisponiveis()) {
                String situacao;
                if (!sessao.getFilme().isEmCartaz()) {
                    situacao = "Fora de cartaz";
                } else if (sessao.jaComecou()) {
                    situacao = "Já começou";
                } else {
                    situacao = "Vendas abertas";
                }
                getModelo().addRow(new Object[]{
                        "Sala " + sala.getNumero(),
                        sala.getTipoSala().getTipo() + "  (x" + sala.getTipoSala().getMultiplicador() + ")",
                        sessao.getHorario(),
                        sessao.getFilme().getNome(),
                        Estilo.dinheiro(sala.calcularValorIngresso(sessao.getFilme())),
                        situacao
                });
            }
        }
    }
}
