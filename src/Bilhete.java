public class Bilhete {
        private String user;
        private String cpf;
        private Sessao sessao;
        private Filme filme;
        private double valor;
        private Cadeira cadeira;


        public Bilhete(Usuario usuario, Sala sala, Sessao sessao, Cadeira cadeira){
            this.user = usuario.getUser();
            this.cpf = usuario.getCpf();
            this.sessao = sessao;
            this.filme = sessao.getFilme();
            this.valor = filme.getValor() * sala.getTipoSala().getMultiplicador();
            this.cadeira = cadeira;
        }
}
