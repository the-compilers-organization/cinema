public class Bilhete {
        private String user;
        private String cpf;
        private Sessao sessao;
        private Filme filme;
        private double valor;
        private int linha;
        private int coluna;


        public Bilhete(Usuario usuario, Sessao sessao, Filme filme, int linha, int coluna){
            this.user = usuario.getUser();
            this.cpf = usuario.getCpf();
            this.sessao = sessao;
            this.filme = filme;
            this.valor = filme.getValor();
            this.linha = linha;
            this.coluna = coluna;
        }
}
