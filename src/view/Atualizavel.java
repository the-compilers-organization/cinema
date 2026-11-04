package view;

// Toda tela do menu lateral implementa esta interface
// Quando o usuário abre uma tela pelo menu, a PrincipalView chama atualizar() para recarregar os dados mais recentes
 
public interface Atualizavel {

    void atualizar();
}
