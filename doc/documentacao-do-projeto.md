## Mapeamento dos pdf's x Código Implementado

### 🎬 ATO 1 (Parte 1) — Classes e Encapsulamento
| Exigência do Slide (Ato 1) | Onde e Como Foi Implementado no Código |
| :--- | :--- |
| **Atributos `private`, métodos `public`, construtores, getters e setters** | Todas as classes de [`src/model`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model) têm atributos `private`. Os construtores chamam os próprios `setters` (ex.: [`Usuario.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Usuario.java#L25-L37)), garantindo que nenhum objeto nasça inválido. |
| **Classe `Usuario`** (`user`, `CPF`, `senha`, `idade`, `sexo`, `email`, `nomeCartao`, `numeroCartao`, `codigoVerificador`) + realizar, alterar e cancelar compra | Implementada em [`Usuario.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Usuario.java#L13-L202). O método `comprarBilhete` gera o ingresso; as operações de realizar, alterar e cancelar compra são coordenadas por [`CompraController.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/controller/CompraController.java) e [`Compra.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Compra.java). |
| **Classe `Filme`** (`nome`, `duração`, `sinopse`, `valor`) | Implementada em [`Filme.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Filme.java#L10-L35). |
| **Classe `Bilhete`** (`user`, `CPF`, `sala`, `sessão`, `filme`, `valor` calculado por filme e sala, `cadeira`) | Implementada em [`Bilhete.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Bilhete.java#L10-L33). Calcula o preço inicial na linha 30: `this.valor = sala.calcularValorIngresso(sessao.getFilme())`. |
| **Classe `Sala`** (lista de sessões de tamanho fixo com grade de horário; horário sem sessão fica `null` e não aparece para o usuário) | Implementada em [`Sala.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Sala.java#L12-L50). Possui o vetor estático `HORARIOS` com 7 horários (`10:00` às `22:00`) e o vetor fixo `Sessao[7]`. O método [`getSessoesDisponiveis()`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Sala.java#L42-L50) filtra posições `null`. |
| **Classe `Sessao`** (filme exibido + matriz de cadeiras `10 linhas x 15 colunas`) | Implementada em [`Sessao.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Sessao.java#L9-L28) com `Cadeira[10][15]` (fileiras `A` a `J`, colunas `1` a `15`). |
| **Classe `Compra`** (itens de lanchonete com estoque infinito + 1 ou mais bilhetes; método de compra de bilhetes à parte; sugestão de cadeiras juntas) | Implementada em [`Compra.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Compra.java) e [`Sessao.buscarCadeirasJuntas(int quantidade)`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Sessao.java#L61-L86). |

---

### 🎬 ATO 2 (Parte 2) — Herança, Sobrescrita, Sobrecarga e Enums
| Exigência do Slide (Ato 2) | Onde e Como Foi Implementado no Código |
| :--- | :--- |
| **Enum `Produtos`** (5 ou 6 produtos de balcão com `String nome` e `double valor` integrados à `Compra`) | [`Produtos.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/enums/Produtos.java): 6 itens (`PIPOCA` R\$ 18, `REFRIGERANTE` R\$ 9, `CHOCOLATE` R\$ 7,50, `AGUA` R\$ 5, `NACHOS` R\$ 16, `BALA` R\$ 4). |
| **Enum `CupomPromocional`** (3 cupons com descontos à escolha da equipe) | [`CupomPromocional.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/enums/CupomPromocional.java): `BEMVINDO10` (10%), `CINEPOO20` (20%) e `QUARTA30` (30%), com método `aplicarDesconto(double valor)`. |
| **Enum de Salas** (`Comum 1.0`, `3D 1.2`, `XD 1.3`, `XD/3D 1.4`) e escolha do fluxo (`Sala -> Filme/Sessão`) | [`TipoSala.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/enums/TipoSala.java). A equipe adotou o fluxo simplificado sugerido pelo professor: **escolha da Sala $\rightarrow$ escolha da Sessão/Filme**. |
| **Herança e Sobrescrita — `Estudante`** (herda tudo de `Usuario` e sobrescreve compra de bilhete para metade do valor) | [`Estudante.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Estudante.java#L24-L31): `@Override comprarBilhete(...)` chama `super.comprarBilhete(...)` e aplica `bilhete.setValor(bilhete.getValor() / 2)`. |
| **Herança e Sobrescrita — `Critico`** (herda de `Usuario`, atributo `origem`, compra de bilhete sempre zero, `atribuirNota` e `atribuirCritica`) | [`Critico.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Critico.java#L11-L56): possui `private String origem`, `@Override comprarBilhete(...)` que define `bilhete.setValor(0.0)`, além de `atribuirNota(double nota, Filme filme)` e `atribuirCritica(String critica, Filme filme)`. |
| **Classe `Critica` e evolução de `Filme`** (`nota`, `quantidadeCriticos`, vetor `Critica[100]`) | [`Critica.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Critica.java) guarda `nome`, `origem` e `mensagem`. [`Filme.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Filme.java#L12-L60) tem `MAX_CRITICAS = 100`, recalcula a média em `adicionarNota` e insere no final do vetor em `adicionarCritica`. |
| **Sobrecarga nas Compras** (sobrecarregar compra de produtos e compra de bilhetes com `CupomPromocional`) | 1) Em [`Usuario.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Usuario.java#L89-L98): `comprarBilhete(Sala, Sessao, int, int, CupomPromocional)`.<br>2) Em [`Compra.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Compra.java#L61-L89): `comprarProduto(Produtos, int)` e `comprarProduto(Produtos, int, CupomPromocional)`. |

---

### 🎬 ATO 3 (Parte 3) — Classes Abstratas, Interfaces, Exceções e Persistência
| Exigência do Slide (Ato 3) | Onde e Como Foi Implementado no Código |
| :--- | :--- |
| **Exceção `VendasException`** tratando os 3 erros de compra de bilhetes:<br>1) Horário do filme já passou;<br>2) Poltrona já selecionada;<br>3) Filme fora de cartaz. | [`VendasException.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/exceptions/VendasException.java) define as 3 constantes (`SESSAO_INICIADA`, `POLTRONA_OCUPADA`, `FORA_DE_CARTAZ`) e guarda `motivo` e `filme`. É lançada em [`Usuario.comprarBilhete`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Usuario.java#L60-L81), [`Compra.adicionarBilhete`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Compra.java#L34-L41) e [`CompraController.finalizar`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/controller/CompraController.java#L113-L127). |
| **Classe Abstrata `Base`** (`nome`, `idade`, `email`, getters/setters e métodos abstratos `adicionarUsuario` e `alterarUsuario`) | [`Base.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Base.java#L13-L62). |
| **Interface `GerenciaFilmes`** (`incluirFilme`, `excluirFilme`, `alterarFilme`) | [`GerenciaFilmes.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/interfaces/GerenciaFilmes.java). |
| **Classe Concreta `Funcionario`** (herda de `Base`, atributo `salario`, implementa métodos abstratos e `GerenciaFilmes`) | [`Funcionario.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Funcionario.java#L16-L58). Não possui método de excluir usuário e delega a persistência ao `Administrador`. |
| **Classe Concreta `Administrador`** (herda de `Base`, atributos `salario` e `id`, implementa `Base` + `GerenciaFilmes` + `excluirUsuario` + responsável pela **Persistência**) | [`Administrador.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Administrador.java#L18-L120). Recebe `IRepositorioUsuario` e `IRepositorioFilme` e executa todas as gravações de usuários e filmes. |
| **Escolha da Tecnologia de Persistência** (Arquivos de texto, vetores, listas ou BD) | A equipe escolheu **Arquivos JSON (`data/*.json`) com a biblioteca Gson** (e listas `ArrayList` em memória), isolados pela camada `repository`. |

---

### 🎬 ATO 4 (Parte 4) — Padrões de Projeto e Justificativa
| Exigência do Slide (Ato 4) | Escolha da Equipe e Justificativa para a Apresentação |
| :--- | :--- |
| **Escolher um Padrão de Projeto, aplicá-lo ao sistema e justificar a escolha** | **Padrão Principal Escolhido: Fachada (*Facade*)** — [`IFachada.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/controller/IFachada.java) e [`Fachada.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/controller/Fachada.java).<br><br>**Por que escolhemos o Facade (Justificativa):**<br>1. **Redução de Acoplamento:** O sistema possui 11 telas/componentes no pacote `view`, 4 controladores (`UsuarioController`, `FilmeController`, `SessaoController`, `CompraController`), 15 classes no `model` e 3 repositórios JSON. Sem a Fachada, cada tela precisaria conhecer e instanciar vários controladores e modelos.<br>2. **Ponto Único de Entrada:** As telas recebem apenas `IFachada`. Toda operação da interface gráfica resume-se a uma chamada na fachada (ex.: `fachada.finalizarCompra(carrinho)`).<br>3. **Coordenação entre Subsistemas:** Operações que envolvem mais de um controlador são orquestradas pela própria `Fachada`. Exemplo: em [`Fachada.excluirUsuario`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/controller/Fachada.java#L96-L99), a fachada consulta primeiro o `compraController` para verificar se o cliente tem compras ativas antes de chamar o `usuarioController`. Em [`Fachada.excluirFilme`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/controller/Fachada.java#L124-L130), ela consulta o `sessaoController` para impedir a exclusão de um filme que está na grade de hoje. |

> [!TIP]
> **Outros Padrões Presentes no Código (Bônus para citar se o professor perguntar):**
> - **MVC (Model-View-Controller):** Separação clara entre `model`, `view` e `controller`.
> - **Repository / DAO (Data Access Object):** Interfaces `IRepositorio...` desacoplam a lógica de negócio dos arquivos JSON.
> - **Observer (Listener):** Usado em [`MapaCadeirasPanel.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/view/MapaCadeirasPanel.java#L235-L243) (`adicionarOuvinte` / `avisarOuvintes`) para notificar a `CompraView` sempre que o usuário clica em uma poltrona.

---

## Roteiro

### ⏱️ Tópico 1: Introdução e Arquitetura Geral (0:00 – 0:50 | ~50 seg)
**O que falar:**
> "Boa noite, professor e colegas. Nosso projeto é um **Sistema de Cinema** completo com interface gráfica em **Java Swing** e persistência em arquivos **JSON** usando a biblioteca Gson.
>
> Para organizar o projeto sem misturar código de tela com regra de negócio, dividimos a arquitetura em pacotes bem definidos seguindo o **MVC com camadas**:
> - No pacote **`model`**, estão nossas entidades de domínio, enums e interfaces de negócio;
> - No pacote **`view`**, temos todas as telas em Java Swing, que tratam apenas da exibição e dos cliques do usuário;
> - No pacote **`controller`**, ficam os 4 controladores de negócio centralizados pela nossa **`Fachada`**;
> - No pacote **`repository`**, isolamos a leitura e gravação dos arquivos JSON atrás de interfaces `IRepositorio`;
> - E transversalmente temos o pacote **`exceptions`**, garantindo que qualquer erro de validação ou regra de negócio chegue na tela como um alerta amigável."

---

### ⏱️ Tópico 2: Núcleo do Domínio e Matriz 10×15 (0:50 – 2:00 | ~1 min 10s)
**O que falar:**
> "Entrando no núcleo do domínio (pacote `model`), tudo gira em torno de quatro classes principais: [`Sala`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Sala.java), [`Sessao`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Sessao.java), [`Bilhete`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Bilhete.java) e [`Compra`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Compra.java).
>
> - A **[`Sala`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Sala.java#L12-L25)** possui um vetor fixo de 7 posições (`Sessao[7]`), onde cada posição representa um horário fixo da grade, das 10h às 22h. Se um horário não tem sessão, fica `null` e é ocultado da tela.
> - Cada **[`Sessao`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Sessao.java#L9-L28)** guarda o `Filme` exibido e uma matriz bidimensional **`Cadeira[10][15]`** — são 10 fileiras de `A` a `J` por 15 colunas, totalizando 150 poltronas.
> - Um destaque importante em `Sessao` é o algoritmo **[`buscarCadeirasJuntas`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Sessao.java#L61-L86)**: quando o cliente quer comprar vários ingressos, esse método percorre a matriz procurando blocos contíguos de cadeiras livres na mesma fileira, começando pelas fileiras centrais (`4` e `5`, fileiras E e F), onde a visão da tela é melhor.
> - Já o **[`Bilhete`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Bilhete.java#L23-L33)** calcula automaticamente seu preço multiplicando o valor base do filme pelo multiplicador do tipo da sala. E a classe **[`Compra`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Compra.java#L15-L30)** funciona como nosso carrinho e pedido fechado, reunindo uma lista de `Bilhete` e uma lista de `ItemCompra` da lanchonete."

---

### ⏱️ Tópico 3: Herança, Polimorfismo, Sobrecarga e Enums (2:00 – 3:15 | ~1 min 15s)
**O que falar:**
> "Na segunda etapa, aplicamos **Enums, Herança, Polimorfismo e Sobrecarga**:
>
> - Criamos três **Enums** principais para travar valores fixos em tempo de compilação: [`TipoSala`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/enums/TipoSala.java) (Comum `1.0x`, 3D `1.2x`, XD `1.3x` e XD/3D `1.4x`), [`Produtos`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/enums/Produtos.java) da lanchonete com seus preços, e [`CupomPromocional`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/enums/CupomPromocional.java) (`BEMVINDO10`, `CINEPOO20` e `QUARTA30`).
> - Na **Herança**, a superclasse [`Usuario`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Usuario.java#L51-L84) implementa o método `comprarBilhete` com todas as validações e preço cheio. As subclasses **[`Estudante`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Estudante.java#L24-L31)** e **[`Critico`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Critico.java#L27-L34)** sobrescrevem (`@Override`) esse método reaproveitando as validações via `super.comprarBilhete(...)`: o `Estudante` divide o valor por 2 (meia-entrada) e o `Critico` zera o valor do ingresso (`R$ 0,00`).
> - Além disso, o **[`Critico`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Critico.java#L39-L55)** possui o atributo `origem` e os métodos para atribuir nota de 0 a 10 e escrever críticas, que são salvas no vetor `Critica[100]` dentro de [`Filme`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Filme.java#L40-L59), recalculando a média ponderada do filme.
> - Por fim, aplicamos **Sobrecarga (Overload)** tanto em [`Compra.comprarProduto`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Compra.java#L61-L89) quanto em [`Usuario.comprarBilhete`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Usuario.java#L89-L98): existe a assinatura sem cupom e a assinatura sobrecarregada que recebe o `CupomPromocional` e aplica o desconto percentual."

---

### ⏱️ Tópico 4: Abstração, Interfaces e Exceções (3:15 – 4:25 | ~1 min 10s)
**O que falar:**
> "Para modelar a equipe interna do cinema, combinamos **Classe Abstrata** e **Interface**:
>
> - A classe abstrata **[`Base`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Base.java)** representa a identidade comum da equipe (`nome`, `idade`, `email`) e obriga as subclasses a implementarem `adicionarUsuario` e `alterarUsuario`.
> - Já a interface **[`GerenciaFilmes`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/interfaces/GerenciaFilmes.java)** define um contrato de capacidade: `incluirFilme`, `alterarFilme` e `excluirFilme`.
> - Tanto **[`Funcionario`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Funcionario.java)** quanto **[`Administrador`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Administrador.java)** herdam de `Base` e implementam `GerenciaFilmes`. Qual a diferença? O `Administrador` é o único dono dos repositórios de persistência e o único que possui o método `excluirUsuario()`. O `Funcionario` delega as gravações ao `Administrador` e, se tentar excluir um usuário, é barrado por uma `UsuarioException`.
> - No tratamento de erros, destacamos a **[`VendasException`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/exceptions/VendasException.java)**, que guarda o motivo e o nome do filme, e é disparada em 3 cenários críticos:
>   1. **Sessão já iniciada:** quando o horário da sessão já passou em relação ao relógio do computador;
>   2. **Poltrona já selecionada:** quando o assento já foi vendido ou já está no carrinho;
>   3. **Filme fora de cartaz:** quando a equipe desmarca o filme como 'em cartaz', bloqueando novas vendas sem quebrar as sessões existentes."

---

### ⏱️ Tópico 5: Padrões de Projeto e Demonstração ao Vivo (`Main.java`) (4:25 – 7:05 | ~2 min 40s)
**O que falar enquanto abre a IDE e roda o [`Main.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/Main.java):**
> "Na arquitetura geral, escolhemos o padrão **MVC** aliado ao **Repository** para isolar o JSON e manter baixo acoplamento, além de constantes padronizadas em `VendasException` e um padrão **Observer** no [`MapaCadeirasPanel`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/view/MapaCadeirasPanel.java#L235-L243) para avisar a tela `CompraView` sempre que o usuário clica numa poltrona.
>
> Vamos ver isso funcionando na prática executando o [`Main.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/Main.java):"

#### **Passo a Passo Cronometrado da Demo (2 min):**
1. **Cadastro / Login (~30s):**
   * Na `LoginView`, clique em **"Criar conta"** (`CadastroUsuarioView`) para mostrar que o formulário muda se selecionarmos **Cliente**, **Estudante** ou **Crítico** (habilitando o campo *Origem*).
   * *Dica para ganhar tempo:* Você pode cadastrar um novo ou logar direto com o Crítico já existente no JSON (**CPF:** `33333333333` / **Senha:** `1234`) ou Estudante (**CPF:** `22222222222` / **Senha:** `1234`).
2. **Avaliar um Filme como Crítico (~30s):**
   * Logada como Crítico (`Paula Crítica`), mostre que a aba **"Avaliar filmes"** ([`CriticaView`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/view/CriticaView.java)) aparece exclusivamente para esse perfil.
   * Escolha um filme, dê uma nota (ex.: `9.5`) e escreva um comentário rápido. Clique em salvar e mostre na aba **"Filmes"** que a média e a lista de críticas atualizaram na hora e já foram gravadas em `data/filmes.json`.
3. **Escolher Assentos Juntos na Matriz Gráfica (~45s):**
   * Vá na aba **"Comprar ingressos"** ([`CompraView`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/view/CompraView.java)).
   * Escolha uma **Sala** e uma **Sessão futura** (ex.: `22:00`, para não cair no `jaComecou()`).
   * Em *"Quantas pessoas?"*, mude o spinner para **`3`** e clique em **"Sugerir lugares juntos"**. Destaque como o [`MapaCadeirasPanel`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/view/MapaCadeirasPanel.java) pinta automaticamente 3 poltronas lado a lado no centro da sala (fileira E).
   * Selecione o cupom **`CINEPOO20 (20%)`** e clique em **"Adicionar ingressos ao carrinho"** (mostre que para Crítico sai `R$ 0,00` de cortesia, ou para Estudante sai meia com desconto do cupom).
4. **Lanchonete e Finalizar Compra (~15s):**
   * No painel da direita, escolha **Pipoca grande** e **Refrigerante**, clique em **"Adicionar"** (veja a sobrecarga com cupom aplicando os 20% de desconto na lanchonete!).
   * Clique em **"Finalizar compra"**, confirme no diálogo com os 4 últimos dígitos do cartão e mostre a compra salva em **"Minhas compras"**!

---

### ⏱️ Tópico 6: Por que o Padrão Facade? — Fechamento (7:05 – 8:00 | ~55 seg)
**O que falar:**
> "Para fechar, por que escolhemos o **Padrão de Projeto Estrutural Facade** ([`IFachada`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/controller/IFachada.java) e [`Fachada`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/controller/Fachada.java)) na etapa final?
>
> Como vimos na demonstração, uma única ação do usuário — como finalizar uma compra ou excluir um cadastro — envolve várias classes do `model`, quatro `Controllers` diferentes e três `Repositorios` JSON.
>
> Sem o **Facade**, cada tela Swing teria que conhecer e instanciar vários controladores, gerando alto acoplamento. Com a classe [`Fachada`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/controller/Fachada.java#L36-L60) implementando a interface [`IFachada`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/controller/IFachada.java):
> 1. O [`Main.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/Main.java#L26-L28) instancia apenas `IFachada fachada = new Fachada()` e entrega para as telas;
> 2. As telas fazem chamadas de uma única linha, como `fachada.finalizarCompra(carrinho)`;
> 3. E quando uma regra cruza dois domínios — como [`excluirUsuario`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/controller/Fachada.java#L96-L99), que consulta o `CompraController` antes de autorizar o `UsuarioController`, ou [`excluirFilme`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/controller/Fachada.java#L124-L130), que verifica no `SessaoController` se o filme está na grade de hoje — a própria `Fachada` coordena tudo de forma transparente.
>
> Assim garantimos um sistema coeso, fácil de manter e que aplica todos os pilares de POO na prática. Muito obrigada!"

---
