import javax.swing.SwingUtilities;

import controller.Fachada;
import controller.IFachada;
import exceptions.PersistenciaException;
import exceptions.ValidacaoException;
import view.Estilo;
import view.LoginView;
import view.Mensagens;


// Ponto de entrada do sistema
// Arquitetura em camadas:

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
