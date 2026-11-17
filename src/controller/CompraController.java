package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import exceptions.CompraException;
import exceptions.PersistenciaException;
import exceptions.ValidacaoException;
import exceptions.VendasException;
import model.Bilhete;
import model.Compra;
import model.Sala;
import model.Sessao;
import model.Usuario;
import model.enums.CupomPromocional;
import model.enums.Produtos;
import repository.IRepositorioCompra;


// Camada de NEGÓCIO (controller) - realizar, alterar e cancelar compras
// Acessa os dados somente pela interface IRepositorioCompra

public class CompraController {

    private IRepositorioCompra repositorio;
    private SessaoController sessaoController;

    public CompraController(IRepositorioCompra repositorio, SessaoController sessaoController) {
        this.repositorio = repositorio;
        this.sessaoController = sessaoController;

        // Poltronas das compras já gravadas começam ocupadas
        sessaoController.ocuparCadeiras(repositorio.listar());
    }

    public Compra novaCompra(Usuario usuario) {
        return new Compra(usuario);
    }

    
    // Adiciona ao carrinho um bilhete para cada cadeira escolhida
    // Primeiro cria todos os bilhetes (validando); só depois coloca no carrinho
    // Assim, se uma cadeira der erro, nenhuma é adicionada
   
    public void adicionarIngressos(Compra carrinho, Usuario usuario, Sala sala, Sessao sessao, ArrayList<int[]> cadeiras, int quantidadePessoas, CupomPromocional cupom) throws VendasException, ValidacaoException {

        if (cadeiras == null || cadeiras.isEmpty()) {
            throw new ValidacaoException("Selecione pelo menos uma poltrona no mapa.");
        }
        if (quantidadePessoas < 1) {
            throw new ValidacaoException("A quantidade de pessoas deve ser pelo menos 1.");
        }
        // Regra: deve haver exatamente uma poltrona para cada pessoa informada
        if (cadeiras.size() != quantidadePessoas) {
            throw new ValidacaoException("Você informou " + quantidadePessoas + " pessoa(s), mas selecionou "
                    + cadeiras.size() + " poltrona(s). Selecione " + quantidadePessoas
                    + " poltrona(s) ou altere a quantidade de pessoas.");
        }

        ArrayList<Bilhete> novos = new ArrayList<Bilhete>();
        for (int[] cadeira : cadeiras) {
            Bilhete bilhete;
            if (cupom == null) {
                bilhete = usuario.comprarBilhete(sala, sessao, cadeira[0], cadeira[1]);
            } else {
                bilhete = usuario.comprarBilhete(sala, sessao, cadeira[0], cadeira[1], cupom); // sobrecarga
            }

            if (carrinho.contemCadeira(sala.getNumero(), sessao.getHorario(), cadeira[0], cadeira[1])) {
                throw new VendasException(VendasException.POLTRONA_OCUPADA, sessao.getFilme().getNome(), "A poltrona " + bilhete.getNomeCadeira() + " já está no seu carrinho.");
            }
            novos.add(bilhete);
        }

        for (Bilhete bilhete : novos) {
            carrinho.adicionarBilhete(bilhete);
        }
        carrinho.setCupom(cupom);
    }

    public void adicionarProduto(Compra carrinho, Produtos produto, int quantidade, CupomPromocional cupom) throws ValidacaoException {
        if (cupom == null) {
            carrinho.comprarProduto(produto, quantidade);
        } else {
            carrinho.comprarProduto(produto, quantidade, cupom); // sobrecarga
        }
        carrinho.setCupom(cupom);
    }

    // Remove um item do carrinho: primeiro vêm os bilhetes, depois os produtos
    public void removerItemDoCarrinho(Compra carrinho, int indice) throws CompraException {
        int qtdBilhetes = carrinho.getBilhetes().size();
        if (indice < qtdBilhetes) {
            carrinho.removerBilhete(indice);
        } else {
            carrinho.removerProduto(indice - qtdBilhetes);
        }
    }

    
    // REALIZAR COMPRA: confere tudo de novo, ocupa as poltronas e grava
    public void finalizar(Compra compra) throws CompraException, VendasException, PersistenciaException {
        if (compra.getBilhetes().isEmpty()) {
            throw new CompraException("A compra deve ter pelo menos um ingresso.");
        }

        // Confere se as poltronas continuam livres e se as sessões ainda são válidas
        for (Bilhete bilhete : compra.getBilhetes()) {
            Sessao sessao = sessaoController.buscarSessaoDoBilhete(bilhete);
            if (sessao == null || !sessao.getFilme().isEmCartaz()) {
                throw new VendasException(VendasException.FORA_DE_CARTAZ, bilhete.getFilme(),
                        "O filme \"" + bilhete.getFilme() + "\" não está mais em cartaz.");
            }
            if (sessao.jaComecou()) {
                throw new VendasException(VendasException.SESSAO_INICIADA, bilhete.getFilme(),
                        "A sessão de \"" + bilhete.getFilme() + "\" às " + sessao.getHorario() + " já começou.");
            }
            if (!sessao.cadeiraDisponivel(bilhete.getLinha(), bilhete.getColuna())) {
                throw new VendasException(VendasException.POLTRONA_OCUPADA, bilhete.getFilme(),
                        "A poltrona " + bilhete.getNomeCadeira() + " acabou de ser vendida.");
            }
        }

        compra.setId(repositorio.gerarProximoId());
        compra.setDataHora(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        repositorio.inserir(compra); // se falhar, lança PersistenciaException e nada é ocupado

        for (Bilhete bilhete : compra.getBilhetes()) {
            sessaoController.buscarSessaoDoBilhete(bilhete).ocuparCadeira(bilhete.getLinha(), bilhete.getColuna());
        }
    }

    public ArrayList<Compra> comprasDoUsuario(String cpf) {
        return repositorio.listarPorCpf(cpf);
    }

    // CANCELAR COMPRA: apaga a compra e libera as poltronas
    public void cancelar(Compra compra) throws CompraException, PersistenciaException {
        if (!repositorio.listar().contains(compra)) {
            throw new CompraException("Compra não encontrada.");
        }
        repositorio.remover(compra);
        sessaoController.liberarCadeiras(compra);
    }

    // ALTERAR COMPRA: remover um ingresso ou um produto
    public void removerItemDaCompra(Compra compra, int indice) throws CompraException, PersistenciaException {
        int qtdBilhetes = compra.getBilhetes().size();

        if (indice < qtdBilhetes) {
            if (qtdBilhetes == 1) {
                throw new CompraException("A compra precisa ter pelo menos um ingresso. "
                        + "Para remover o último, cancele a compra inteira.");
            }
            Bilhete removido = compra.removerBilhete(indice);
            sessaoController.liberarCadeira(removido);
        } else {
            compra.removerProduto(indice - qtdBilhetes);
        }
        repositorio.atualizar(compra);
    }

    // ALTERAR COMPRA: adicionar produto (usa o mesmo cupom da compra)
    public void adicionarProdutoNaCompra(Compra compra, Produtos produto, int quantidade) throws ValidacaoException, PersistenciaException {
        if (compra.getCupom() == null) {
            compra.comprarProduto(produto, quantidade);
        } else {
            compra.comprarProduto(produto, quantidade, compra.getCupom());
        }
        repositorio.atualizar(compra);
    }
}
