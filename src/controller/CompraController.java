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

    private Compra rascunho;

    private int indiceEdicao = -1;

    private final int[] linhasIngressos = new int[100];

    private final int[] colunasIngressos = new int[100];

    private int qtdIngressos = 0;

    private boolean atualizando = false;





    public CompraController(CompraView view, Sala[] salas, Usuario usuario, MenuController menu) {

        this.view = view;

        this.salas = salas;

        this.usuario = usuario;

        this.menu = menu;

        carregarSessoes();

        configurarEventos();

        novaCompra();

        view.addWindowListener(new java.awt.event.WindowAdapter() {

            @Override public void windowClosing(java.awt.event.WindowEvent e) { voltar(); }

        });



    }





    private void configurarEventos() {

        view.getCbSessao().addActionListener(e -> { if (!atualizando) limparCadeiras(); });

        view.getSpQtdBilhetes().addChangeListener(e -> { if (!atualizando) limparCadeiras(); });

        view.getCbCupom().addActionListener(e -> { if (!atualizando) atualizarTotal(); });

        view.getBtnEscolherCadeiras().addActionListener(e -> abrirCadeiras());

        view.getBtnExcluirCadeiras().addActionListener(e -> excluirSelecaoCadeiras());

        view.getBtnAdicionarProduto().addActionListener(e -> adicionarProduto());

        view.configurarAcoesProdutos(this::editarProduto, this::removerProduto);

        view.getBtnNovaCompra().addActionListener(e -> {

            if (JOptionPane.showConfirmDialog(view, "Limpar os dados nao salvos?",

                "Limpar formulario", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) novaCompra();



        });



        view.getBtnCarregarCompra().addActionListener(e -> carregarCompra());

        view.getBtnFinalizar().addActionListener(e -> confirmarCompra());

        view.getBtnCancelar().addActionListener(e -> cancelarCompra());

        view.getCbCompras().addActionListener(e -> atualizarDetalhesCompra());

        view.getAbas().addChangeListener(e -> { if (view.getAbas().getSelectedIndex() == 1) { atualizarListaCompras(); atualizarDetalhesCompra(); } });

        view.getBtnVoltar().addActionListener(e -> voltar());



    }







    private void carregarSessoes() {

        for (Sala sala : salas) {

            if (sala == null) continue;



            for (Sessao sessao : sala.getSessoes()) {



                if (sessao != null) {

                    view.getCbSessao().addItem("Sala " + sala.getNumero() + " | " + sessao.getHorario() + " | " + sessao.getFilme().getNome());

                }

            }

        }

    }







    private boolean buscarSessao() {

        int indice = view.getCbSessao().getSelectedIndex();

        int contador = 0;

        salaSelecionada = null;

        sessaoSelecionada = null;



        if (indice < 0) return false;



        for (Sala sala : salas) {



            if (sala == null) continue;



            for (Sessao sessao : sala.getSessoes()) {



                if (sessao == null) continue;



                if (contador == indice) {

                    salaSelecionada = sala;

                    sessaoSelecionada = sessao;

                    return true;



                }



                contador++;

            }

        }



        return false;

    }







    private int indiceSessao(Sessao procurada) {



        int contador = 0;



        for (Sala sala : salas) {



            if (sala == null) continue;



            for (Sessao sessao : sala.getSessoes()) {



                if (sessao == null) continue;



                if (sessao == procurada) return contador;



                contador++;



            }



        }



        return -1;



    }







    private int quantidade() { return ((Number) view.getSpQtdBilhetes().getValue()).intValue(); }

    private boolean usarPerfil() { return view.getCbCupom().getSelectedIndex() == 0; }



    private CupomPromocional cupom() {



        int indice = view.getCbCupom().getSelectedIndex();

        return indice <= 1 ? null : CupomPromocional.values()[indice - 2];



    }







    private void atualizarListaCompras() {

        int anterior = view.getCbCompras().getSelectedIndex();

        view.getCbCompras().removeAllItems();

        for (int i = 0; i < menu.getQtdCompras(); i++) {

            Compra c = menu.getCompra(i);

            if (c != null && c.getUsuario() == usuario) {

                view.getCbCompras().addItem("Compra " + (i + 1) + " | " + c.getBilhetes().size() + " ingresso(s)");

            }

        }



        if (anterior >= 0 && anterior < view.getCbCompras().getItemCount()) view.getCbCompras().setSelectedIndex(anterior);



        atualizarDetalhesCompra();



    }







    private void atualizarDetalhesCompra() {

        int indice = indiceSelecionado();

        if (indice < 0) {

            view.getTxtDetalhesCompra().setText("Nenhuma compra registrada para este usuario.");

            return;



        }



        Compra c = menu.getCompra(indice);

        StringBuilder sb = new StringBuilder("COMPRA " + (indice + 1) + " - CONFIRMADA\n\n");



        for (int i = 0; i < c.getBilhetes().size(); i++) {

            Bilhete b = c.getBilhetes().get(i);

            sb.append("Ingresso ").append(i + 1).append(": ")

              .append(b.getFilme().getNome()).append(" | Sala ").append(b.getSala().getNumero())

              .append(" | ").append(b.getSessao().getHorario())

              .append(" | L").append(b.getCadeira().getLinha() + 1)

              .append(" C").append(b.getCadeira().getColuna() + 1).append("\n");



        }



        sb.append("\nPRODUTOS\n");



        for (int i = 0; i < c.getProdutos().size(); i++) {

            sb.append(c.getProdutos().get(i)).append(" x ").append(c.getQtdCadaProduto().get(i))

              .append(String.format(" = R$ %.2f\n", c.getSubtotaisProdutos().get(i)));



        }



        sb.append("\nDesconto: ").append(menu.getPerfilCompra(indice) ? "Perfil" :

            (menu.getCupomCompra(indice) == null ? "Nenhum" : menu.getCupomCompra(indice)));



        double bilhetes = menu.getPerfilCompra(indice) ? usuario.calcularValorDoBilhete(c) : c.calcularTotalBilhetes();

        sb.append(String.format("\nTOTAL: R$ %.2f", bilhetes + c.calcularTotalProdutos()));

        view.getTxtDetalhesCompra().setText(sb.toString());



    }







    private int indiceSelecionado() {

        int escolhido = view.getCbCompras().getSelectedIndex();



        int contador = 0;



        for (int i = 0; i < menu.getQtdCompras(); i++) {

            Compra c = menu.getCompra(i);

            if (c != null && c.getUsuario() == usuario) {



                if (contador == escolhido) return i;



                contador++;



            }



        }



        return -1;



    }







    private void novaCompra() {

        atualizando = true;



        indiceEdicao = -1;

        rascunho = new Compra(usuario);

        view.getSpQtdProduto().setValue(1);



        view.getTabelaProdutos().clearSelection();

        qtdIngressos = 0;



        if (view.getCbSessao().getItemCount() > 0) view.getCbSessao().setSelectedIndex(0);

        view.getSpQtdBilhetes().setValue(0);

        view.getCbCupom().setSelectedIndex(0);

        view.getLblEstado().setText("NOVA COMPRA - ainda nao confirmada");

        view.getAbas().setTitleAt(0, "Nova compra");

        view.getBtnFinalizar().setText("Confirmar compra");



        atualizando = false;

        atualizarListaCompras();

        atualizarTotal();

        view.getAbas().setSelectedIndex(0);



    }







    private void carregarCompra() {



        int indice = indiceSelecionado();

        if (indice < 0) {

            JOptionPane.showMessageDialog(view, "Selecione uma compra registrada.");

            return;



        }



        Compra original = menu.getCompra(indice);



        if (original.getBilhetes().size() == 0) return;

        Bilhete primeiro = original.getBilhetes().get(0);

        int sessaoIndice = indiceSessao(primeiro.getSessao());



        if (sessaoIndice < 0) {

            JOptionPane.showMessageDialog(view, "A sessao da compra nao foi encontrada.");

            return;



        }



        atualizando = true;



        indiceEdicao = indice;



        rascunho = new Compra(usuario);



        for (int i = 0; i < original.getProdutos().size(); i++) {

            rascunho.comprarProduto(original.getProdutos().get(i), original.getQtdCadaProduto().get(i));



        }



        view.getCbSessao().setSelectedIndex(sessaoIndice);

        view.getSpQtdBilhetes().setValue(original.getBilhetes().size());

        int desconto = menu.getPerfilCompra(indice) ? 0 :

                (menu.getCupomCompra(indice) == null ? 1 : 2 + menu.getCupomCompra(indice).ordinal());



        view.getCbCupom().setSelectedIndex(desconto);

        qtdIngressos = original.getBilhetes().size();



        for (int i = 0; i < qtdIngressos; i++) {

            Bilhete b = original.getBilhetes().get(i);

            linhasIngressos[i] = b.getCadeira().getLinha();

            colunasIngressos[i] = b.getCadeira().getColuna();



        }



        view.getLblEstado().setText("EDITANDO COMPRA " + (indice + 1) + " - salve para aplicar");

        view.getAbas().setTitleAt(0, "Editar compra");

        view.getBtnFinalizar().setText("Salvar alteracoes");

        atualizando = false;

        atualizarTotal();

        view.getAbas().setSelectedIndex(0);



    }







    private void limparCadeiras() {

        // Mudancas na sessao ou quantidade invalidam a selecao anterior,

        // mas preservam a quantidade que o usuario acabou de informar.

        qtdIngressos = 0;

        atualizarTotal();

    }







    private void excluirSelecaoCadeiras() {

        if (qtdIngressos == 0) return;



        int resposta = JOptionPane.showConfirmDialog(

                view,

                "Excluir todas as cadeiras selecionadas deste rascunho?",

                "Excluir cadeiras",

                JOptionPane.YES_NO_OPTION,

                JOptionPane.QUESTION_MESSAGE

        );



        if (resposta != JOptionPane.YES_OPTION) return;



        atualizando = true;

        qtdIngressos = 0;

        view.getSpQtdBilhetes().setValue(0);

        atualizando = false;

        atualizarTotal();

    }







    private void abrirCadeiras() {

        if (!buscarSessao()) {

            JOptionPane.showMessageDialog(view, "Selecione uma sessao.");

            return;

        }



        if (quantidade() < 1) {

            JOptionPane.showMessageDialog(view,

                    "Informe pelo menos 1 ingresso antes de escolher cadeiras.");

            return;

        }



        CadeiraView tela = new CadeiraView();

        CadeiraController controlador = new CadeiraController(

                tela, sessaoSelecionada, this, quantidade());



        if (qtdIngressos == quantidade()) {

            controlador.preSelecionarCadeiras(linhasIngressos, colunasIngressos, qtdIngressos);

        }



        tela.setVisible(true);

        view.setVisible(false);

    }







    public boolean cadeiraDaCompraEmEdicao(Sessao sessao, int l, int c) {



        if (indiceEdicao < 0) return false;

        Compra original = menu.getCompra(indiceEdicao);

        if (original == null) return false;

        for (int i = 0; i < original.getBilhetes().size(); i++) {

            Bilhete b = original.getBilhetes().get(i);

            if (b.getSessao() == sessao && b.getCadeira().getLinha() == l && b.getCadeira().getColuna() == c) return true;



        }



        return false;



    }







    public boolean cadeiraJaNoRascunho(int l, int c, int exceto) {

        for (int i = 0; i < qtdIngressos; i++) {

            if (i != exceto && linhasIngressos[i] == l && colunasIngressos[i] == c) return true;

        }



        return false;



    }







    public void setCadeirasSelecionadas(int l, int c, int qtd) {

        if (!buscarSessao() || qtd < 1 || qtd != quantidade()

                || qtd > linhasIngressos.length || c < 0 || c + qtd > 15) {

            JOptionPane.showMessageDialog(view, "Quantidade ou selecao de cadeiras invalida.");



            return;



        }



        for (int i = 0; i < qtd; i++) {

            if (!sessaoSelecionada.cadeiraValida(l, c + i)

                    || (!sessaoSelecionada.cadeiraDisponivel(l, c + i)

                        && !cadeiraDaCompraEmEdicao(sessaoSelecionada, l, c + i))) {

                JOptionPane.showMessageDialog(view, "As cadeiras devem estar livres, juntas e na mesma linha.");



                return;



            }



        }



        qtdIngressos = qtd;

        for (int i = 0; i < qtd; i++) {

            linhasIngressos[i] = l;

            colunasIngressos[i] = c + i;



        }



        atualizarTotal();



    }







    public void trocarCadeiraIngresso(int indice, int l, int c) {

        if (indice < 0 || indice >= qtdIngressos || !buscarSessao()) return;



        // A troca individual so e aceita se o conjunto continuar consecutivo.

        if (!sessaoSelecionada.cadeiraValida(l, c)

                || (!sessaoSelecionada.cadeiraDisponivel(l, c)

                    && !cadeiraDaCompraEmEdicao(sessaoSelecionada, l, c))) return;



        int linhaAnterior = linhasIngressos[indice];

        int colunaAnterior = colunasIngressos[indice];

        linhasIngressos[indice] = l;

        colunasIngressos[indice] = c;



        if (!cadeirasConsecutivas()) {

            linhasIngressos[indice] = linhaAnterior;

            colunasIngressos[indice] = colunaAnterior;

            JOptionPane.showMessageDialog(view,

                "Os ingressos precisam ficar lado a lado na mesma linha. Use Editar cadeiras para trocar o grupo.");



            return;



        }



        atualizarTotal();



    }







    private boolean cadeirasConsecutivas() {

        if (qtdIngressos <= 1) return true;

        int linha = linhasIngressos[0];

        int menor = colunasIngressos[0];

        int maior = menor;

        for (int i = 1; i < qtdIngressos; i++) {

            if (linhasIngressos[i] != linha) return false;

            menor = Math.min(menor, colunasIngressos[i]);

            maior = Math.max(maior, colunasIngressos[i]);



        }



        if (maior - menor + 1 != qtdIngressos) return false;

        for (int i = 0; i < qtdIngressos; i++) {

            for (int j = i + 1; j < qtdIngressos; j++) {

                if (colunasIngressos[i] == colunasIngressos[j]) return false;



            }



        }



        return true;



    }









    private void adicionarProduto() {

        Produtos produto = (Produtos) view.getCbProduto().getSelectedItem();

        int qtd = ((Number) view.getSpQtdProduto().getValue()).intValue();

        if (!rascunho.comprarProduto(produto, qtd)) {

            JOptionPane.showMessageDialog(view, "Nao foi possivel adicionar o produto.");

            return;



        }

        atualizarTotal();



    }







    private void editarProduto(int indice) {

        if (indice < 0 || indice >= rascunho.getProdutos().size()) return;

        Produtos produto = rascunho.getProdutos().get(indice);

        int atual = rascunho.getQtdCadaProduto().get(indice);

        JSpinner quantidade = new JSpinner(new SpinnerNumberModel(atual, 1, 1000, 1));

        JPanel formulario = new JPanel(new java.awt.GridLayout(2, 1, 5, 5));

        formulario.add(new JLabel("Nova quantidade de " + produto.name() + ":"));

        formulario.add(quantidade);

        int resposta = JOptionPane.showConfirmDialog(view, formulario, "Editar produto",

                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (resposta != JOptionPane.OK_OPTION) return;

        int novaQtd = ((Number) quantidade.getValue()).intValue();

        if (!rascunho.alterarQuantidadeProduto(produto, novaQtd)) {

            JOptionPane.showMessageDialog(view, "Nao foi possivel alterar a quantidade.");

            return;



        }



        atualizarTotal();



    }







    private void removerProduto(int indice) {

        if (indice < 0 || indice >= rascunho.getProdutos().size()) return;

        Produtos produto = rascunho.getProdutos().get(indice);



        int resposta = JOptionPane.showConfirmDialog(view,

                "Excluir " + produto.name() + " da compra?", "Excluir produto",

                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);



        if (resposta != JOptionPane.YES_OPTION) return;



        if (!rascunho.removerProduto(produto)) {

            JOptionPane.showMessageDialog(view, "Produto nao encontrado no resumo.");

            return;



        }



        atualizarTotal();



    }







    private Compra montarCompra() {

        if (!buscarSessao() || qtdIngressos == 0 || qtdIngressos != quantidade()

                || !cadeirasConsecutivas()) return null;



        Compra nova = new Compra(usuario);



        for (int i = 0; i < qtdIngressos; i++) {

            int l = linhasIngressos[i], c = colunasIngressos[i];

            if (!sessaoSelecionada.cadeiraValida(l, c)) return null;

            if (!sessaoSelecionada.cadeiraDisponivel(l, c) &&

                    !cadeiraDaCompraEmEdicao(sessaoSelecionada, l, c)) return null;



            for (int j = 0; j < i; j++) {

                if (linhasIngressos[j] == l && colunasIngressos[j] == c) return null;



            }



            Bilhete bilhete = new Bilhete(usuario, salaSelecionada, sessaoSelecionada,

                    sessaoSelecionada.buscarCadeira(l, c));



            boolean ok = cupom() == null ? nova.comprarBilhete(bilhete) :

                    nova.comprarBilhete(bilhete, cupom());



            if (!ok) return null;



        }



        for (int i = 0; i < rascunho.getProdutos().size(); i++) {

            Produtos p = rascunho.getProdutos().get(i);

            int qtd = rascunho.getQtdCadaProduto().get(i);

            boolean ok = cupom() == null ? nova.comprarProduto(p, qtd) :

                    nova.comprarProduto(p, qtd, cupom());



            if (!ok) return null;



        }



        return nova;



    }







    private double totalBilhetes(Compra c, boolean perfil) {

        return perfil ? usuario.calcularValorDoBilhete(c) : c.calcularTotalBilhetes();



    }







    private void atualizarTotal() {

        buscarSessao();

        view.getBtnEscolherCadeiras().setText(

                qtdIngressos == 0 ? "Escolher cadeiras" : "Editar cadeiras");



        view.getBtnExcluirCadeiras().setEnabled(qtdIngressos > 0);



        StringBuilder descricao = new StringBuilder("Cadeiras: ");

        if (qtdIngressos == 0) {

            descricao.append("nenhuma selecionada");



        } else {

            for (int i = 0; i < qtdIngressos; i++) {

                if (i > 0) descricao.append(" | ");

                descricao.append("L").append(linhasIngressos[i] + 1)

                         .append(" C").append(colunasIngressos[i] + 1);



            }



        }



        view.getLblCadeira().setText(descricao.toString());

        double bilhetes = 0;



        if (sessaoSelecionada != null && salaSelecionada != null) {

            Compra simulacao = new Compra(usuario);



            for (int i = 0; i < qtdIngressos; i++) {

                Bilhete b = new Bilhete(usuario, salaSelecionada, sessaoSelecionada,

                        new Cadeira(linhasIngressos[i], colunasIngressos[i]));



                if (cupom() == null) simulacao.comprarBilhete(b);

                else simulacao.comprarBilhete(b, cupom());



            }



            bilhetes = totalBilhetes(simulacao, usarPerfil());



        }



        double produtos = 0;

        int selecionado = view.getTabelaProdutos().getSelectedRow();

        atualizando = true;



        view.getModeloProdutos().setRowCount(0);



        for (int i = 0; i < rascunho.getProdutos().size(); i++) {

            Produtos p = rascunho.getProdutos().get(i);

            int qtd = rascunho.getQtdCadaProduto().get(i);

            double subtotal = p.getValor() * qtd * (cupom() == null ? 1 : 1 - cupom().getPercentualDesconto());

            produtos += subtotal;

            view.getModeloProdutos().addRow(new Object[]{p.name(), qtd, String.format("R$ %.2f", subtotal), ""});



        }



        if (selecionado >= 0 && selecionado < rascunho.getProdutos().size()) {

            view.getTabelaProdutos().setRowSelectionInterval(selecionado, selecionado);



        }



        atualizando = false;

        view.getLblBilhetes().setText(String.format("Bilhetes: R$ %.2f", bilhetes));

        view.getLblProdutos().setText(String.format("Produtos: R$ %.2f", produtos));

        view.getLblTotal().setText(String.format("TOTAL: R$ %.2f", bilhetes + produtos));

        view.getTxtResumo().setText("Sessao: " + (sessaoSelecionada == null ? "nenhuma" :



                sessaoSelecionada.getFilme().getNome() + " - " + sessaoSelecionada.getHorario()) +

                "\nIngressos adicionados: " + qtdIngressos +

                "\n" + view.getLblCadeira().getText() +

                "\nDesconto: " + view.getCbCupom().getSelectedItem() +

                "\n" + view.getLblTotal().getText());



    }







    private void confirmarCompra() {

        if (indiceEdicao < 0 && !menu.podeRegistrarCompra()) {

            JOptionPane.showMessageDialog(view, "Limite de compras atingido.");

            return;



        }



        Compra nova = montarCompra();



        if (nova == null || !nova.realizarCompra()) {

            JOptionPane.showMessageDialog(view, "Adicione ingressos e selecione cadeiras validas. A quantidade deve corresponder aos ingressos.");

            return;



        }



        Compra antiga = indiceEdicao < 0 ? null : menu.getCompra(indiceEdicao);



        // A compra antiga so e liberada depois de validar o novo rascunho.



        if (antiga != null) antiga.liberarCadeiras();

        int ocupadas = 0;

        for (int i = 0; i < nova.getBilhetes().size(); i++) {

            Bilhete b = nova.getBilhetes().get(i);

            if (!b.getSessao().ocuparCadeira(b.getCadeira().getLinha(), b.getCadeira().getColuna())) break;

            ocupadas++;



        }



        if (ocupadas != nova.getBilhetes().size()) {

            for (int i = 0; i < ocupadas; i++) {

                Bilhete b = nova.getBilhetes().get(i);

                b.getSessao().liberarCadeira(b.getCadeira().getLinha(), b.getCadeira().getColuna());



            }



            if (antiga != null) {

                for (int i = 0; i < antiga.getBilhetes().size(); i++) {

                    Bilhete b = antiga.getBilhetes().get(i);

                    b.getSessao().ocuparCadeira(b.getCadeira().getLinha(), b.getCadeira().getColuna());



                }



            }



            JOptionPane.showMessageDialog(view, "Nao foi possivel reservar as cadeiras.");



            return;



        }



        boolean ok = antiga == null ? menu.registrarCompra(nova, usarPerfil(), cupom()) :

                menu.atualizarCompra(indiceEdicao, nova, usarPerfil(), cupom());



        if (!ok) {



            nova.liberarCadeiras();

            if (antiga != null) {

                for (int i = 0; i < antiga.getBilhetes().size(); i++) {

                    Bilhete b = antiga.getBilhetes().get(i);

                    b.getSessao().ocuparCadeira(b.getCadeira().getLinha(), b.getCadeira().getColuna());



                }



            }



            JOptionPane.showMessageDialog(view, "Nao foi possivel registrar a compra.");



            return;



        }



        JOptionPane.showMessageDialog(view, String.format("Compra salva! Total: R$ %.2f",

                totalBilhetes(nova, usarPerfil()) + nova.calcularTotalProdutos()));



        novaCompra();



        view.getAbas().setSelectedIndex(1);



    }







    private void cancelarCompra() {

        int selecionada = indiceSelecionado();

        if (selecionada < 0) {

            JOptionPane.showMessageDialog(view, "Selecione uma compra registrada.");

            return;



        }



        if (JOptionPane.showConfirmDialog(view, "Cancelar a compra registrada e liberar as cadeiras?",

            "Confirmar", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) return;



        Compra original = menu.getCompra(selecionada);



        if (original != null && original.cancelarCompra() && menu.excluirCompra(selecionada)) {

            JOptionPane.showMessageDialog(view, "Compra cancelada.");

            novaCompra();

            view.getAbas().setSelectedIndex(1);



        } else {



            JOptionPane.showMessageDialog(view, "Nao foi possivel cancelar a compra.");



        }



    }



    private void voltar() { menu.voltarMenu(); view.dispose(); }



    public void mostrarView() { view.setVisible(true); }



}
