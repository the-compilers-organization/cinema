import javax.swing.SwingUtilities;

import controller.Fachada;
import controller.IFachada;
import exceptions.PersistenciaException;
import exceptions.ValidacaoException;
import view.Estilo;
import view.LoginView;
import view.Mensagens;


// Ponto de entrada do sistema
// Arquitetura em 3 camadas (MVC + Fachada):
// UI (view) ............ telas Swing; tratam os cliques e chamam a IFachada
// NEGÓCIO (controller) . IFachada, Fachada e UsuarioController, FilmeController, SessaoController, CompraController
//  (model) ..... entidades: Usuario, Filme, Sala, Sessao, Compra ...
//  DADOS (repository) ... IRepositorio... (interfaces) e Repositorio...JSON

public class Main {

    public static void main(String[] args) {
        Estilo.aplicarTema();

        SwingUtilities.invokeLater(() -> {
            try {
                IFachada fachada = new Fachada(); // carrega os arquivos JSON
                LoginView login = new LoginView(fachada);
                login.iniciar();

            } catch (PersistenciaException e) {
                Mensagens.erro(null, "Erro ao carregar os dados", e.getMessage());
            } catch (ValidacaoException e) {
                Mensagens.erro(null, "Erro ao iniciar o sistema", e.getMessage());
            }
        });
    }
}
