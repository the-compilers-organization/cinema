package controller;
import model.*;
import view.*;
import javax.swing.*;
import java.awt.*;
public class CadeiraController {
    private CadeiraView view;
    private Sessao sessao;
    private CompraController compra;
    private int quantidade;
    private int linha = -1, coluna = -1;
    public CadeiraController(CadeiraView view, Sessao sessao, CompraController compra, int quantidade){
        this.view=view; this.sessao=sessao; this.compra=compra; this.quantidade=quantidade;
        JButton[][] botoes=view.getBotoesCadeiras();
        int i,j;
        for(i=0;i<botoes.length;i+=1){
            for(j=0;j<botoes[i].length;j+=1){
                int l=i,c=j;
                botoes[i][j].addActionListener(e->selecionar(l,c));
            }
        }
        view.getBtnConfirmar().addActionListener(e->confirmar());
        view.getBtnVoltar().addActionListener(e->voltar());
        view.addWindowListener(new java.awt.event.WindowAdapter(){
            public void windowClosing(java.awt.event.WindowEvent e){voltar();}
        });
        atualizar();
    }
    private boolean grupoLivre(int l,int c){
        if(c + quantidade > 15) return false;
        int j;
        for(j=c;j<c+quantidade;j+=1){
            if(!sessao.cadeiraDisponivel(l,j)) return false;
        }
        return true;
    }
    private void selecionar(int l,int c){
        if(!grupoLivre(l,c)){
            JOptionPane.showMessageDialog(view,"Nao ha "+quantidade+" cadeira(s) livre(s) a partir desta posicao, na mesma fileira.");
            return;
        }
        linha=l; coluna=c; atualizar();
    }
    private void atualizar(){
        JButton[][] botoes=view.getBotoesCadeiras();
        int i,j;
        for(i=0;i<botoes.length;i+=1){
            for(j=0;j<botoes[i].length;j+=1){
                boolean livre=sessao.cadeiraDisponivel(i,j);
                botoes[i][j].setEnabled(livre);
                botoes[i][j].setText(livre?String.format("%02d",j+1):"X");
                botoes[i][j].setBackground(null);
                if(i==linha && j>=coluna && j<coluna+quantidade){
                    botoes[i][j].setBackground(Color.GREEN);
                }
            }
        }
        view.setTitle("Escolher "+quantidade+" cadeira(s) juntas - clique na primeira");
    }
    private void confirmar(){
        if(linha<0 || !grupoLivre(linha,coluna)){
            JOptionPane.showMessageDialog(view,"Escolha um grupo de cadeiras livres."); return;
        }
        compra.setCadeirasSelecionadas(linha,coluna,quantidade);
        compra.mostrarView(); view.dispose();
    }
    private void voltar(){compra.mostrarView();view.dispose();}
}


/*
 * ============================================================
 * ORIENTACOES PARA IMPLEMENTAR CadeiraController
 * ============================================================
 *
 * OBJETIVO:
 *
 * Controlar a escolha de cadeira para uma determinada Sessao.
 *
 *
 * ATRIBUTOS:
 *
 * - CadeiraView view;
 * - Sessao sessao;
 * - CompraController compraController;
 * - int linhaSelecionada;
 * - int colunaSelecionada.
 *
 *
 * Inicialmente:
 *
 * linhaSelecionada = -1
 * colunaSelecionada = -1
 *
 *
 * CONSTRUTOR:
 *
 * Receber:
 *
 * CadeiraView
 * Sessao
 * CompraController
 *
 * Depois:
 *
 * - carregar estado das cadeiras;
 * - configurar eventos.
 *
 *
 * configurarEventos():
 *
 * Percorrer a matriz:
 *
 * JButton[][]
 *
 * existente na View.
 *
 * Cada botao deve chamar:
 *
 * selecionarCadeira(linha, coluna)
 *
 *
 * ATENCAO AO LAMBDA:
 *
 * Dentro do for, criar variaveis locais para linha e coluna
 * antes de utiliza-las no ActionListener.
 *
 * Exemplo conceitual:
 *
 * int l = linha;
 * int c = coluna;
 *
 * Isso permite utilizar l e c dentro da expressao lambda.
 *
 *
 * carregarCadeiras():
 *
 * Obter:
 *
 * Cadeira[][]
 *
 * atraves da Sessao.
 *
 * Para cada cadeira:
 *
 * ocupada:
 * - mostrar "X";
 * - desabilitar botao.
 *
 * livre:
 * - mostrar numero;
 * - habilitar botao.
 *
 *
 * selecionarCadeira():
 *
 * Verificar atraves da Sessao se a cadeira esta disponivel.
 *
 * Se estiver:
 *
 * guardar linha e coluna selecionadas.
 *
 *
 * confirmar():
 *
 * Verificar se alguma cadeira foi selecionada.
 *
 * Depois utilizar o metodo do Model:
 *
 * sessao.ocuparCadeira(...)
 *
 * Nao fazer:
 *
 * cadeira.setOcupada(true)
 *
 * diretamente.
 *
 *
 * Se a ocupacao for realizada:
 *
 * informar ao CompraController:
 *
 * setCadeiraSelecionada(...)
 *
 * mostrar novamente CompraView;
 * fechar CadeiraView.
 *
 *
 * voltar():
 *
 * Nao ocupar cadeira.
 *
 * Mostrar novamente a CompraView existente e fechar
 * CadeiraView.
 *
 * ============================================================
 */