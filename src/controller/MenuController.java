package controller;
import model.*;
import enums.*;
import view.*;
import javax.swing.*;
public class MenuController {
    private MenuView view;
    private Filme[] filmes;
    private Sala[] salas;
    private Usuario usuario;
    private Compra[] compras=new Compra[100];
    private int qtdCompras=0;
    private boolean[] perfilPorCompra=new boolean[100];
    private CupomPromocional[] cupomPorCompra=new CupomPromocional[100];
    public MenuController(MenuView view,Filme[] filmes,Sala[] salas){
        this.view=view;this.filmes=filmes;this.salas=salas;configurarEventos();
    }
    private void configurarEventos(){
        view.getBtnCadastrarUsuario().addActionListener(e->abrirUsuario());
        view.getBtnFilmes().addActionListener(e->abrirFilmes());
        view.getBtnSessoes().addActionListener(e->abrirSessoes());
        view.getBtnRealizarCompra().addActionListener(e->abrirCompra());
        view.getBtnAvaliarFilme().addActionListener(e->abrirCritica());
        view.getBtnAlterarCompra().addActionListener(e->alterarCompra());
        view.getBtnCancelarCompra().addActionListener(e->cancelarCompra());
        view.getBtnExibirCompra().addActionListener(e->exibirCompra());
        view.getBtnSair().addActionListener(e->System.exit(0));
    }
    private void abrirUsuario(){UsuarioView tela=new UsuarioView();new UsuarioController(tela,this);tela.setVisible(true);view.setVisible(false);}
    private void abrirFilmes(){FilmeView tela=new FilmeView();new FilmeController(tela,filmes,this);tela.setVisible(true);view.setVisible(false);}
    private void abrirSessoes(){SessaoView tela=new SessaoView();new SessaoController(tela,salas,this);tela.setVisible(true);view.setVisible(false);}
    private void abrirCompra(){
        if(usuario==null){JOptionPane.showMessageDialog(view,"Cadastre um usuario antes de comprar.");return;}
        CompraView tela=new CompraView();new CompraController(tela,salas,usuario,this);tela.setVisible(true);view.setVisible(false);
    }
    private void abrirCritica(){CriticaView tela=new CriticaView();new CriticaController(tela,filmes,usuario,this);tela.setVisible(true);view.setVisible(false);}
    public boolean podeRegistrarCompra(){return qtdCompras<compras.length;}
    public boolean registrarCompra(Compra compra,boolean usarPerfil,CupomPromocional cupom){
        if(compra==null || !podeRegistrarCompra())return false;
        compras[qtdCompras]=compra;
        perfilPorCompra[qtdCompras]=usarPerfil;
        cupomPorCompra[qtdCompras]=cupom;
        qtdCompras+=1;return true;
    }
    private int selecionarCompra(){
        if(usuario==null){JOptionPane.showMessageDialog(view,"Cadastre um usuario.");return -1;}
        StringBuilder opcoes=new StringBuilder("Compras do usuario "+usuario.getUser()+":\n");
        int i,cont=0;
        for(i=0;i<qtdCompras;i+=1)if(compras[i]!=null && compras[i].getUsuario()==usuario){
            opcoes.append(i+1).append(" - ").append(compras[i].getQtdBilhetes()).append(" bilhete(s)\n");cont+=1;
        }
        if(cont==0){JOptionPane.showMessageDialog(view,"Nao existem compras para este usuario.");return -1;}
        String entrada=JOptionPane.showInputDialog(view,opcoes+"Informe o numero da compra:");
        if(entrada==null)return -1;
        try{
            int indice=Integer.parseInt(entrada.trim())-1;
            if(indice>=0 && indice<qtdCompras && compras[indice]!=null && compras[indice].getUsuario()==usuario)return indice;
        }catch(NumberFormatException e){}
        JOptionPane.showMessageDialog(view,"Numero de compra invalido.");return -1;
    }
    private double total(int indice){
        Compra c=compras[indice];
        double bilhetes=perfilPorCompra[indice] ? c.getUsuario().calcularValorDoBilhete(c) : c.calcularTotalBilhetes();
        return bilhetes+c.calcularTotalProdutos();
    }
    private void exibirCompra(){
        int indice=selecionarCompra();if(indice<0)return;
        Compra c=compras[indice];StringBuilder sb=new StringBuilder("Compra "+(indice+1)+"\nUsuario: "+usuario.getUser()+"\n");
        int i;
        for(i=0;i<c.getQtdBilhetes();i+=1){
            Bilhete b=c.getBilhetes()[i];
            sb.append("Bilhete ").append(i+1).append(": Sala ").append(b.getSala().getNumero())
              .append(" | ").append(b.getSessao().getHorario()).append(" | ").append(b.getFilme().getNome())
              .append(" | L").append(b.getCadeira().getLinha()+1).append(" C").append(b.getCadeira().getColuna()+1)
              .append(String.format(" | R$ %.2f\n",b.getValor()));
        }
        for(i=0;i<c.getQtdProdutos();i+=1){
            sb.append(c.getProdutos()[i]).append(" x ").append(c.getQtdCadaProduto()[i])
              .append(String.format(" = R$ %.2f\n",c.getSubtotaisProdutos()[i]));
        }
        sb.append(String.format("Total dos bilhetes: R$ %.2f\n",perfilPorCompra[indice]?usuario.calcularValorDoBilhete(c):c.calcularTotalBilhetes()));
        sb.append("Desconto escolhido: ").append(perfilPorCompra[indice]?"Perfil":(cupomPorCompra[indice]==null?"Nenhum":cupomPorCompra[indice])).append("\n");
        sb.append(String.format("Total dos produtos: R$ %.2f\nTOTAL: R$ %.2f",c.calcularTotalProdutos(),total(indice)));
        JOptionPane.showMessageDialog(view,sb.toString());
    }
    private void cancelarCompra(){
        int indice=selecionarCompra();if(indice<0)return;
        if(JOptionPane.showConfirmDialog(view,"Cancelar compra e liberar as cadeiras?","Confirmar",JOptionPane.YES_NO_OPTION)!=JOptionPane.YES_OPTION)return;
        if(compras[indice].cancelarCompra()){
            int i;for(i=indice;i<qtdCompras-1;i+=1){
                compras[i]=compras[i+1];perfilPorCompra[i]=perfilPorCompra[i+1];cupomPorCompra[i]=cupomPorCompra[i+1];
            }
            compras[qtdCompras-1]=null;cupomPorCompra[qtdCompras-1]=null;qtdCompras-=1;
            JOptionPane.showMessageDialog(view,"Compra cancelada.");
        }
    }
    private void alterarCompra(){
        int indice=selecionarCompra();if(indice<0)return;
        Compra c=compras[indice];
        String[] acoes={"Alterar quantidade de produto","Remover produto","Remover bilhete","Fechar"};
        int escolha=JOptionPane.showOptionDialog(view,"O que deseja alterar?","Alterar compra",0,JOptionPane.QUESTION_MESSAGE,null,acoes,acoes[0]);
        if(escolha==0 || escolha==1){
            Produtos produto=(Produtos)JOptionPane.showInputDialog(view,"Produto:","Produto",JOptionPane.QUESTION_MESSAGE,null,Produtos.values(),Produtos.values()[0]);
            if(produto==null)return;
            if(escolha==1){
                JOptionPane.showMessageDialog(view,c.removerProduto(produto)?"Produto removido.":"Produto nao encontrado.");
            }else{
                String qtd=JOptionPane.showInputDialog(view,"Nova quantidade:");if(qtd==null)return;
                try{int valor=Integer.parseInt(qtd.trim());
                    JOptionPane.showMessageDialog(view,(cupomPorCompra[indice]==null?c.alterarQuantidadeProduto(produto,valor):c.alterarQuantidadeProduto(produto,valor,cupomPorCompra[indice]))?"Quantidade alterada.":"Produto inexistente ou quantidade invalida.");
                }catch(NumberFormatException e){JOptionPane.showMessageDialog(view,"Informe um numero inteiro.");}
            }
        }else if(escolha==2){
            String numero=JOptionPane.showInputDialog(view,"Numero do bilhete (1 a "+c.getQtdBilhetes()+"):");if(numero==null)return;
            try{
                int n=Integer.parseInt(numero.trim());
                if(c.getQtdBilhetes()<=1){JOptionPane.showMessageDialog(view,"Para remover o ultimo bilhete, cancele a compra.");return;}
                JOptionPane.showMessageDialog(view,c.removerBilhete(n)?"Bilhete removido e cadeira liberada.":"Bilhete invalido.");
            }catch(NumberFormatException e){JOptionPane.showMessageDialog(view,"Numero invalido.");}
        }
    }
    public void voltarMenu(){view.setVisible(true);}
    public void setUsuario(Usuario usuario){this.usuario=usuario;}
    public Usuario getUsuario(){return usuario;}
}


/*
 * ============================================================
 * ORIENTACOES PARA IMPLEMENTAR MenuController
 * ============================================================
 *
 * OBJETIVO:
 *
 * Controlar a tela principal do sistema e a navegacao para
 * as demais funcionalidades.
 *
 * O MenuController deve fazer a ligacao entre:
 *
 * MenuView
 *    |
 *    +---- UsuarioView
 *    +---- FilmeView
 *    +---- SessaoView
 *    +---- CompraView
 *    +---- CriticaView
 *
 *
 * ATRIBUTOS:
 *
 * Deve possuir referencias para:
 *
 * - MenuView;
 * - Filme[];
 * - Sala[];
 * - Usuario atual.
 *
 * O Usuario atual inicialmente pode ser null, pois sera
 * definido depois do cadastro realizado em UsuarioView.
 *
 *
 * CONSTRUTOR:
 *
 * Deve receber:
 *
 * MenuView
 * Filme[]
 * Sala[]
 *
 * Guardar essas referencias e chamar um metodo responsavel
 * pela configuracao dos eventos.
 *
 *
 * EVENTOS:
 *
 * Associar os botoes da MenuView aos metodos correspondentes:
 *
 * Cadastrar Usuario -> abrirUsuario()
 * Listar Filmes     -> abrirFilmes()
 * Listar Sessoes    -> abrirSessoes()
 * Realizar Compra   -> abrirCompra()
 * Avaliar Filme     -> abrirCritica()
 * Sair              -> encerrar aplicacao
 *
 *
 * NAVEGACAO:
 *
 * Quando uma nova tela for aberta:
 *
 * 1. criar a View;
 * 2. criar o Controller correspondente;
 * 3. passar os objetos necessarios;
 * 4. tornar a nova View visivel;
 * 5. esconder MenuView.
 *
 *
 * Exemplo conceitual:
 *
 * MenuController
 *      |
 *      | abrir filmes
 *      v
 * cria FilmeView
 *      |
 *      v
 * cria FilmeController
 *      |
 *      v
 * FilmeView visivel
 *
 *
 * USUARIO ATUAL:
 *
 * Criar:
 *
 * setUsuario(...)
 * getUsuario()
 *
 * O UsuarioController deve informar ao MenuController qual
 * usuario foi cadastrado.
 *
 * Isso permite posteriormente passar o mesmo Usuario para:
 *
 * CompraController
 * CriticaController
 *
 *
 * VOLTAR AO MENU:
 *
 * Criar um metodo publico como:
 *
 * voltarMenu()
 *
 * Esse metodo deve tornar MenuView novamente visivel.
 *
 *
 * NAO FAZER:
 *
 * - construir componentes Swing;
 * - implementar regras de Filme;
 * - ocupar cadeiras diretamente sem utilizar o Model;
 * - calcular regras de desconto que ja pertencem ao Model;
 * - criar usuarios ficticios para outras telas.
 *
 * ============================================================
 */