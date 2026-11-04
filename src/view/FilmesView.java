package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import model.Critica;
import model.Filme;
import controller.IFachada;


// Tela "Filmes": lista o catálogo e mostra sinopse e críticas do filme selecionado

public class FilmesView extends JPanel implements Atualizavel {

    private DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"Filme", "Duração", "Preço base", "Nota", "Críticos", "Situação"}, 0) {
        @Override
        public boolean isCellEditable(int linha, int coluna) {
            return false;
        }
    };
    private JTable tabela = new JTable(modelo);

    private JLabel lblNome = Estilo.subtitulo("Selecione um filme");
    private JLabel lblInfo = Estilo.textoSuave(" ");
    private JTextArea txtSinopse = criarAreaTexto();
    private JTextArea txtCriticas = criarAreaTexto();

    public FilmesView(IFachada fachada) {
        setLayout(new BorderLayout(16, 0));
        add(Estilo.cabecalho("Filmes", "Catálogo do cinema com notas e críticas."), BorderLayout.NORTH);

        Estilo.estilizarTabela(tabela);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(260);
        add(Estilo.rolagem(tabela), BorderLayout.CENTER);

        JPanel detalhes = Estilo.cartao();
        detalhes.setLayout(new BorderLayout(0, 10));
        detalhes.setPreferredSize(new Dimension(340, 10));

        JPanel topo = new JPanel(new BorderLayout(0, 4));
        topo.setOpaque(false);
        topo.add(lblNome, BorderLayout.NORTH);
        topo.add(lblInfo, BorderLayout.CENTER);
        detalhes.add(topo, BorderLayout.NORTH);

        JPanel textos = new JPanel(new BorderLayout(0, 12));
        textos.setOpaque(false);
        textos.add(Estilo.campo("Sinopse", txtSinopse), BorderLayout.NORTH);
        JPanel painelCriticas = Estilo.campo("Críticas", Estilo.rolagem(txtCriticas));
        textos.add(painelCriticas, BorderLayout.CENTER);
        detalhes.add(textos, BorderLayout.CENTER);

        add(detalhes, BorderLayout.EAST);

        //  eventos da tela 
        this.fachada = fachada;

        getTabela().getSelectionModel().addListSelectionListener(e -> mostrarSelecionado());
    }

    private static JTextArea criarAreaTexto() {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setOpaque(false);
        area.setBorder(BorderFactory.createEmptyBorder(4, 2, 4, 2));
        return area;
    }

    public void mostrarDetalhes(String nome, String info, String sinopse, String criticas) {
        lblNome.setText("<html>" + nome + "</html>");
        lblInfo.setText(info);
        txtSinopse.setText(sinopse);
        txtCriticas.setText(criticas);
        txtCriticas.setCaretPosition(0);
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public JTable getTabela() {
        return tabela;
    }


    //  EVENTOS DA TELA - chamam a IFachada

    private IFachada fachada;
    private ArrayList<Filme> filmes;

    @Override
    public void atualizar() {
        filmes = fachada.listarFilmes();
        getModelo().setRowCount(0);

        for (Filme filme : filmes) {
            String nota = filme.getQuantidadeCriticos() == 0 ? "-" : String.format("%.1f", filme.getNota());
            getModelo().addRow(new Object[]{
                    filme.getNome(),
                    filme.getDuracao() + " min",
                    Estilo.dinheiro(filme.getValor()),
                    nota,
                    filme.getQuantidadeCriticos(),
                    filme.isEmCartaz() ? "Em cartaz" : "Fora de cartaz"
            });
        }
        if (!filmes.isEmpty()) {
            getTabela().setRowSelectionInterval(0, 0);
        }
    }

    private void mostrarSelecionado() {
        int linha = getTabela().getSelectedRow();
        if (linha < 0 || linha >= filmes.size()) {
            return;
        }
        Filme filme = filmes.get(linha);

        String info = filme.getDuracao() + " min  |  " + Estilo.dinheiro(filme.getValor());
        if (filme.getQuantidadeCriticos() > 0) {
            info += "  |  Nota " + String.format("%.1f", filme.getNota())
                    + " (" + filme.getQuantidadeCriticos() + " crítico(s))";
        }
        mostrarDetalhes(filme.getNome(), info, filme.getSinopse(), textoDasCriticas(filme));
    }

    // Monta o texto com todas as críticas do vetor do filme
    public static String textoDasCriticas(Filme filme) {
        Critica[] criticas = filme.getCriticasPreenchidas();
        if (criticas.length == 0) {
            return "Ainda não há críticas para este filme.";
        }
        String texto = "";
        for (Critica critica : criticas) {
            texto += "\"" + critica.getMensagem() + "\"\n"
                    + "   - " + critica.getNome() + " (" + critica.getOrigem() + ")\n\n";
        }
        return texto;
    }
}
