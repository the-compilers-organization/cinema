package controller;
import enums.*;
import model.*;
import view.*;
import javax.swing.*;
public class CompraController {
    private CompraView view;
    private Sala[] salas;
    private Usuario usuario;
    private MenuController menu;
    private Sala salaSelecionada;
    private Sessao sessaoSelecionada;
    private int linha=-1,coluna=-1,quantidadeSelecionada=0;
    private Compra compra;
    public CompraController(CompraView view,Sala[] salas,Usuario usuario,MenuController menu){
        this.view=view;this.salas=salas;this.usuario=usuario;this.menu=menu;
        this.compra=new Compra(usuario);
        carregarSessoes(); configurarEventos(); atualizarTotal();
        view.addWindowListener(new java.awt.event.WindowAdapter(){
            public void windowClosing(java.awt.event.WindowEvent e){voltar();}
        });
    }
    private void configurarEventos(){
        view.getCbSessao().addActionListener(e->mudarSessao());
        view.getSpQtdBilhetes().addChangeListener(e->limparCadeiras());
        view.getBtnEscolherCadeiras().addActionListener(e->abrirCadeiras());
        view.getBtnAdicionarProduto().addActionListener(e->adicionarProduto());
        view.getCbCupom().addActionListener(e->alterarDesconto());
        view.getBtnFinalizar().addActionListener(e->finalizar());
        view.getBtnVoltar().addActionListener(e->voltar());
    }
    private void carregarSessoes(){
        int i,j;
        for(i=0;i<salas.length;i+=1) if(salas[i]!=null){
            Sessao[] sessoes=salas[i].getSessoes();
            for(j=0;j<sessoes.length;j+=1) if(sessoes[j]!=null)
                view.getCbSessao().addItem("Sala "+salas[i].getNumero()+" | "+sessoes[j].getHorario()+" | "+sessoes[j].getFilme().getNome());
        }
    }
    private boolean buscarSessao(){
        int indice=view.getCbSessao().getSelectedIndex(),contador=0,i,j;
        salaSelecionada=null;sessaoSelecionada=null;
        if(indice<0)return false;
        for(i=0;i<salas.length;i+=1) if(salas[i]!=null){
            Sessao[] sessoes=salas[i].getSessoes();
            for(j=0;j<sessoes.length;j+=1) if(sessoes[j]!=null){
                if(contador==indice){salaSelecionada=salas[i];sessaoSelecionada=sessoes[j];return true;}
                contador+=1;
            }
        }
        return false;
    }
    private void limparCadeiras(){linha=-1;coluna=-1;quantidadeSelecionada=0;view.getLblCadeira().setText("Cadeira: nenhuma");atualizarTotal();}
    private void mudarSessao(){limparCadeiras();}
    private int quantidade(){return ((Number)view.getSpQtdBilhetes().getValue()).intValue();}
    private CupomPromocional cupom(){
        int indice=view.getCbCupom().getSelectedIndex();
        if(indice<=1)return null;
        return CupomPromocional.values()[indice-2];
    }
    private void abrirCadeiras(){
        if(!buscarSessao()){JOptionPane.showMessageDialog(view,"Selecione uma sessao.");return;}
        CadeiraView tela=new CadeiraView();
        new CadeiraController(tela,sessaoSelecionada,this,quantidade());
        tela.setVisible(true);view.setVisible(false);
    }
    public void setCadeirasSelecionadas(int l,int c,int qtd){
        linha=l;coluna=c;quantidadeSelecionada=qtd;
        view.getLblCadeira().setText("Linha "+(l+1)+" | cadeiras "+(c+1)+" a "+(c+qtd));
        atualizarTotal();
    }
    private boolean usarPerfil(){return view.getCbCupom().getSelectedIndex()==0;}
    private int descontoProdutos=-1;
    private void alterarDesconto(){
        if(compra.getQtdProdutos()>0 && descontoProdutos>=0 && view.getCbCupom().getSelectedIndex()!=descontoProdutos){
            JOptionPane.showMessageDialog(view,"O desconto nao pode ser alterado apos adicionar produtos. Remova os produtos antes de trocar.");
            view.getCbCupom().setSelectedIndex(descontoProdutos);
            return;
        }
        atualizarTotal();
    }
    private void adicionarProduto(){
        Produtos produto=(Produtos)view.getCbProduto().getSelectedItem();
        int qtd=((Number)view.getSpQtdProduto().getValue()).intValue();
        boolean ok;
        if(cupom()!=null){
            ok=compra.comprarProduto(produto,qtd,cupom());
        }else{
            int indice=compra.buscarProduto(produto);
            if(indice>=0){
                ok=compra.alterarQuantidadeProduto(produto,compra.getQtdCadaProduto()[indice]+qtd);
            }else{
                ok=compra.comprarProduto(produto,qtd);
            }
        }
        if(!ok){
            JOptionPane.showMessageDialog(view,"Nao foi possivel adicionar o produto.");return;
        }
        descontoProdutos=view.getCbCupom().getSelectedIndex();
        atualizarTotal();
    }
   
    private void atualizarTotal(){
        buscarSessao();
        Compra simulacao=new Compra(usuario);
        if(sessaoSelecionada!=null && salaSelecionada!=null){
            for(int i=0;i<quantidade();i+=1){
                Bilhete bilhete=new Bilhete(usuario,salaSelecionada,sessaoSelecionada,new Cadeira(0,i));
                if(cupom()!=null){simulacao.comprarBilhete(bilhete,cupom());}
                else{simulacao.comprarBilhete(bilhete);}
            }
        }
        double totalBilhetes=usarPerfil() ? usuario.calcularValorDoBilhete(simulacao) : simulacao.calcularTotalBilhetes();
        view.getLblBilhetes().setText(String.format("Bilhetes: R$ %.2f",totalBilhetes));
        view.getLblProdutos().setText(String.format("Produtos: R$ %.2f",compra.calcularTotalProdutos()));
        view.getLblTotal().setText(String.format("TOTAL: R$ %.2f",totalBilhetes+compra.calcularTotalProdutos()));
    }
    private void finalizar(){
        if(!buscarSessao() || linha<0 || quantidadeSelecionada!=quantidade()){
            JOptionPane.showMessageDialog(view,"Selecione a sessao e todas as cadeiras.");return;
        }
        if(!menu.podeRegistrarCompra()){
            JOptionPane.showMessageDialog(view,"Limite de compras atingido.");return;
        }
        int i;
        for(i=0;i<quantidade();i+=1){
            if(!sessaoSelecionada.cadeiraDisponivel(linha,coluna+i)){
                JOptionPane.showMessageDialog(view,"Uma das cadeiras nao esta mais disponivel.");return;
            }
        }
        int ocupadas=0;
        for(i=0;i<quantidade();i+=1){
            if(sessaoSelecionada.ocuparCadeira(linha,coluna+i))ocupadas+=1;
            else break;
        }
        if(ocupadas!=quantidade()){
            int j;for(j=0;j<ocupadas;j+=1)sessaoSelecionada.liberarCadeira(linha,coluna+j);
            JOptionPane.showMessageDialog(view,"Nao foi possivel reservar todas as cadeiras.");return;
        }
        for(i=0;i<quantidade();i+=1){
            Bilhete bilhete=new Bilhete(usuario,salaSelecionada,sessaoSelecionada,sessaoSelecionada.buscarCadeira(linha,coluna+i));
            boolean ok=cupom()==null?compra.comprarBilhete(bilhete):compra.comprarBilhete(bilhete,cupom());
            if(!ok){
                compra.cancelarCompra();
                int j;for(j=0;j<quantidade();j+=1)sessaoSelecionada.liberarCadeira(linha,coluna+j);
                JOptionPane.showMessageDialog(view,"Falha ao registrar bilhetes.");return;
            }
        }
        if(!compra.realizarCompra() || !menu.registrarCompra(compra,usarPerfil(),cupom())){
            compra.cancelarCompra();JOptionPane.showMessageDialog(view,"Falha ao registrar compra.");return;
        }
        double total=(usarPerfil()?usuario.calcularValorDoBilhete(compra):compra.calcularTotalBilhetes())+compra.calcularTotalProdutos();
        JOptionPane.showMessageDialog(view,String.format("Compra registrada! Total: R$ %.2f",total));
        menu.voltarMenu();view.dispose();
    }
    private void voltar(){menu.voltarMenu();view.dispose();}
    public void mostrarView(){view.setVisible(true);}
}



/*
 * ============================================================
 * ORIENTACOES PARA IMPLEMENTAR CompraController
 * ============================================================
 *
 * OBJETIVO:
 *
 * Coordenar todo o processo de realizacao da compra.
 *
 *
 * ATRIBUTOS:
 *
 * Deve manter referencias para:
 *
 * - CompraView;
 * - Sala[];
 * - Usuario atual;
 * - MenuController;
 * - Sala selecionada;
 * - Sessao selecionada;
 * - linha da cadeira selecionada;
 * - coluna da cadeira selecionada;
 * - total dos produtos.
 *
 *
 * ESTADO INICIAL:
 *
 * linhaSelecionada = -1
 * colunaSelecionada = -1
 * totalProdutos = 0.00
 *
 *
 * CONSTRUTOR:
 *
 * Receber:
 *
 * CompraView
 * Sala[]
 * Usuario
 * MenuController
 *
 * Depois:
 *
 * - carregar as sessoes;
 * - configurar eventos;
 * - atualizar o total inicial.
 *
 *
 * EVENTOS:
 *
 * Escolher cadeira -> abrirCadeiras()
 * Adicionar produto -> adicionarProduto()
 * Finalizar         -> finalizar()
 * Voltar            -> voltar()
 *
 *
 * carregarSessoes():
 *
 * Percorrer:
 *
 * Sala[]
 *   |
 *   v
 * Sessao[]
 *
 * Adicionar cada sessao no JComboBox da CompraView.
 *
 * Pode apresentar:
 *
 * Sala 1 | 18:00 | Interestelar
 *
 *
 * buscarSessaoSelecionada():
 *
 * O indice do JComboBox nao deve ser usado diretamente como
 * indice de Sala[] ou Sessao[], pois existem vetores com
 * posicoes null.
 *
 * Percorrer novamente as sessoes validas utilizando um
 * contador.
 *
 * Quando o contador corresponder ao indice selecionado,
 * guardar:
 *
 * salaSelecionada
 * sessaoSelecionada
 *
 *
 * abrirCadeiras():
 *
 * Primeiro localizar a Sessao selecionada.
 *
 * Depois:
 *
 * criar CadeiraView;
 * criar CadeiraController;
 * passar a Sessao selecionada;
 * passar o proprio CompraController;
 * esconder CompraView;
 * mostrar CadeiraView.
 *
 *
 * COMUNICACAO ENTRE CONTROLLERS:
 *
 * CadeiraController
 *        |
 *        | cadeira escolhida
 *        v
 * CompraController
 *        |
 *        v
 * CompraView
 *
 *
 * Criar um metodo publico como:
 *
 * setCadeiraSelecionada(int linha, int coluna)
 *
 * Ele deve guardar a cadeira selecionada e atualizar a
 * informacao apresentada na CompraView.
 *
 *
 * adicionarProduto():
 *
 * Obter:
 *
 * Produtos produto
 * quantidade
 *
 * Calcular:
 *
 * produto.getValor() * quantidade
 *
 * Atualizar o total de produtos.
 *
 *
 * calcularValorBilhete():
 *
 * Localizar a Sessao e Sala selecionadas.
 *
 * Nesta versao inicial, calcular:
 *
 * valor do filme
 *       *
 * multiplicador da sala
 *       *
 * quantidade de bilhetes
 *
 *
 * IMPORTANTE:
 *
 * Conforme a integracao com model.Compra e model.Bilhete
 * avancar, evitar duplicar no Controller regras que ja
 * estiverem implementadas nessas classes.
 *
 *
 * atualizarTotal():
 *
 * Atualizar o JLabel correspondente ao total.
 *
 *
 * finalizar():
 *
 * Verificar se existe cadeira selecionada.
 *
 * Posteriormente deve integrar efetivamente:
 *
 * Usuario
 * Compra
 * Bilhete
 * Produtos
 * CupomPromocional
 *
 * utilizando os metodos existentes no Model.
 *
 *
 * voltar():
 *
 * Caso a cadeira ja tenha sido temporariamente ocupada e a
 * compra seja abandonada, liberar a cadeira.
 *
 * Depois retornar ao Menu.
 *
 *
 * mostrarView():
 *
 * Criar metodo publico para CadeiraController conseguir
 * mostrar novamente a mesma CompraView.
 *
 * ============================================================
 */