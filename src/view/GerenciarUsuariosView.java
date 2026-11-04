package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import exceptions.PersistenciaException;
import exceptions.UsuarioException;
import model.Base;
import model.Usuario;
import controller.IFachada;


// Tela da equipe: lista, cadastra, altera e exclui usuários

public class GerenciarUsuariosView extends JPanel implements Atualizavel {

    private DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"Usuário", "CPF", "Tipo", "Idade", "E-mail", "Compras"}, 0) {
        @Override
        public boolean isCellEditable(int linha, int coluna) {
            return false;
        }
    };
    private JTable tabela = new JTable(modelo);

    private JButton btnNovo = Estilo.botao("Novo usuário", Estilo.VERDE);
    private JButton btnEditar = Estilo.botaoSecundario("Editar selecionado");
    private JButton btnExcluir = Estilo.botao("Excluir selecionado", Estilo.PRIMARIA);

    public GerenciarUsuariosView(IFachada fachada, Base equipe) {
        setLayout(new BorderLayout(0, 12));

        JPanel topo = new JPanel(new BorderLayout());
        topo.setOpaque(false);
        topo.add(Estilo.cabecalho("Usuários",
                "O funcionário pode cadastrar e alterar. Somente o administrador pode excluir."),
                BorderLayout.CENTER);
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        botoes.setOpaque(false);
        botoes.add(btnNovo);
        botoes.add(btnEditar);
        botoes.add(btnExcluir);
        topo.add(botoes, BorderLayout.SOUTH);
        add(topo, BorderLayout.NORTH);

        Estilo.estilizarTabela(tabela);
        add(Estilo.rolagem(tabela), BorderLayout.CENTER);

        //  eventos da tela 
        this.fachada = fachada;
        this.equipe = equipe;

        getBtnNovo().addActionListener(e -> abrirFormulario(null));
        getBtnEditar().addActionListener(e -> editarSelecionado());
        getBtnExcluir().addActionListener(e -> excluirSelecionado());
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public JTable getTabela() {
        return tabela;
    }

    public JButton getBtnNovo() {
        return btnNovo;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }

    public JButton getBtnExcluir() {
        return btnExcluir;
    }


    //  EVENTOS DA TELA - chamam a IFachada
    private IFachada fachada;
    private Base equipe;
    private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

    @Override
    public void atualizar() {
        usuarios = fachada.listarUsuarios();
        getModelo().setRowCount(0);
        for (Usuario u : usuarios) {
            getModelo().addRow(new Object[]{
                    u.getUser(),
                    u.getCpf(),
                    u.getTipo(),
                    u.getIdade(),
                    u.getEmail(),
                    fachada.comprasDoUsuario(u.getCpf()).size()
            });
        }
    }

    private Usuario usuarioSelecionado() {
        int linha = getTabela().getSelectedRow();
        if (linha < 0 || linha >= usuarios.size()) {
            Mensagens.aviso(this, "Selecione um usuário na tabela.");
            return null;
        }
        return usuarios.get(linha);
    }

    private void abrirFormulario(Usuario usuario) {
        CadastroUsuarioView formulario = new CadastroUsuarioView(null, fachada, usuario, equipe);
        formulario.setLocationRelativeTo(this);
        if (formulario.abrir()) {
            atualizar();
        }
    }

    private void editarSelecionado() {
        Usuario usuario = usuarioSelecionado();
        if (usuario != null) {
            abrirFormulario(usuario);
        }
    }

    private void excluirSelecionado() {
        Usuario usuario = usuarioSelecionado();
        if (usuario == null) {
            return;
        }
        if (!Mensagens.confirmar(this, "Excluir o usuário " + usuario.getUser() + "?")) {
            return;
        }
        try {
            fachada.excluirUsuario(equipe, usuario.getCpf());
            Mensagens.sucesso(this, "Usuário excluído.");
            atualizar();
        } catch (UsuarioException e) {
            Mensagens.erro(this, "Operação não permitida", e.getMessage());
        } catch (PersistenciaException e) {
            Mensagens.erro(this, "Erro ao gravar o arquivo", e.getMessage());
        }
    }
}
