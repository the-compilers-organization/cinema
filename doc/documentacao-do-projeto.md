# Roteiro de Apresentação (8 Minutos) — Sistema de Cinema

> [!IMPORTANT]
> **3 Detalhes do Código Real para Ajustar na Sua Fala (Evita Pegadinhas do Professor!):**
> 1. **Nome da tela da matriz de poltronas:** No pacote `view`, o componente gráfico da matriz `10x15` chama-se [`MapaCadeirasPanel.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/view/MapaCadeirasPanel.java) (embutido dentro da tela [`CompraView.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/view/CompraView.java)), e não `CadeiraView`.
> 2. **Sobre `VendasException`:** Na versão final de [`VendasException.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/exceptions/VendasException.java#L10-L32), os 3 motivos são definidos por **constantes estáticas** (`SESSAO_INICIADA`, `POLTRONA_OCUPADA`, `FORA_DE_CARTAZ`) passadas ao construtor `new VendasException(motivo, filme, mensagem)` em [`Usuario.comprarBilhete`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Usuario.java#L51-L84). Já os métodos estáticos auxiliares estão em [`Validador.java`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Validador.java) e [`Sessao.nomeDaCadeira()`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Sessao.java#L89-L92).
> 3. **Cuidado com o relógio na Demo ao vivo!** O método [`Sessao.jaComecou()`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Sessao.java#L51-L54) compara o horário da sessão com `LocalTime.now()` do computador. Se você apresentar à noite (ex.: após as 20h), escolha a sessão das **22:00**! Se escolher 14:00 ou 18:00, o sistema vai disparar a `VendasException` de *"Sessão já iniciada"* (o que inclusive é ótimo para mostrar o erro funcionando!).

---

## Parte 1: O Que Escrever em Cada Slide no Canva (6 Slides + Capa)

Para não poluir os slides como no PDF original de 22 páginas, use apenas **tópicos curtos (bullet points)** em cada slide:

### **Slide 1 — Introdução e Arquitetura Geral**
* **Objetivo:** Sistema desktop completo para gestão de cinema, venda de ingressos/lanchonete e críticas de filmes.
* **Interface Gráfica (`view`):** Desenvolvida em **Java Swing** (`LoginView`, `PrincipalView`, `CompraView`, `MapaCadeirasPanel`).
* **Divisão de Pacotes (MVC + Repository + Facade):**
  * `view`: Telas Swing e eventos de interface.
  * `controller`: `IFachada` / `Fachada` + 4 Controllers (`Usuario`, `Filme`, `Sessao`, `Compra`).
  * `model`: Regras de domínio, entidades, `enums` e `interfaces`.
  * `repository`: Persistência em `data/*.json` via biblioteca **Gson** (`IRepositorio...`).
  * `exceptions`: 6 exceções customizadas transversais.

### **Slide 2 — Núcleo do Domínio e Matriz 10×15**
* **[`Sala`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Sala.java):** Número, `TipoSala` e vetor fixo `Sessao[7]` (grade das `10:00` às `22:00`).
* **[`Sessao`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Sessao.java):** Associa 1 `Filme` + Matriz **`Cadeira[10][15]`** (Fileiras `A` a `J`, Colunas `1` a `15` = 150 lugares).
* **Algoritmo [`buscarCadeirasJuntas(int qtd)`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Sessao.java#L61-L86):** Varre a matriz priorizando as fileiras centrais (`E`, `F`, `D`, `G`...) para sugerir poltronas livres lado a lado.
* **[`Bilhete`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Bilhete.java) & [`Compra`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Compra.java):**
  * `Bilhete`: Calcula `valor = filme.getValor() * tipoSala.getMultiplicador()`.
  * `Compra`: Agrupa `ArrayList<Bilhete>`, `ArrayList<ItemCompra>` e `CupomPromocional`.

### **Slide 3 — Herança, Polimorfismo, Sobrecarga e Enums**
* **Enums (`model.enums`):**
  * `TipoSala`: `COMUM (1.0)`, `SALA_3D (1.2)`, `XD (1.3)`, `XD_3D (1.4)`.
  * `Produtos`: Pipoca (R\$ 18), Refrigerante (R\$ 9), Nachos (R\$ 16), Chocolate, Água, Bala.
  * `CupomPromocional`: `BEMVINDO10` (10%), `CINEPOO20` (20%), `QUARTA30` (30%).
* **Herança e Sobrescrita (`@Override comprarBilhete`):**
  * `Usuario` (Superclasse): paga valor integral.
  * `Estudante extends Usuario`: chama `super` e divide o valor por 2 (**meia-entrada**).
  * `Critico extends Usuario`: atributo `origem`, ingresso **gratuito (`0.0`)** + métodos `atribuirNota` e `atribuirCritica` (vetor `Critica[100]` em `Filme`).
* **Sobrecarga (Overload):**
  * Em `Compra`: `comprarProduto(produto, qtd)` e `comprarProduto(produto, qtd, cupom)`.
  * Em `Usuario`: `comprarBilhete(..., cupom)`.

### **Slide 4 — Abstração, Interfaces e Exceções**
* **Classe Abstrata [`Base`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/Base.java) vs. Interface [`GerenciaFilmes`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/model/interfaces/GerenciaFilmes.java):**
  * `Base` (*"quem a equipe é"*): guarda `nome`, `idade`, `email` e métodos abstratos `adicionarUsuario` e `alterarUsuario`.
  * `GerenciaFilmes` (*"o que faz"*): contrato `incluirFilme`, `alterarFilme`, `excluirFilme`.
  * `Administrador` (persiste dados e possui `excluirUsuario`) vs. `Funcionario` (delega ao Admin e não exclui usuários).
* **[`VendasException`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/exceptions/VendasException.java) (3 Erros de Venda):**
  1. `SESSAO_INICIADA`: Horário da sessão já passou (`LocalTime.now().isAfter(inicio)`).
  2. `POLTRONA_OCUPADA`: Cadeira já vendida ou repetida no carrinho.
  3. `FORA_DE_CARTAZ`: Filme desmarcado de "Em cartaz" (`!filme.isEmCartaz()`).

### **Slide 5 — Padrões de Projeto e Demonstração (`Main.java`)**
* **Arquitetura MVC:** Separação clara entre Telas (`view`), Regras (`controller`/`model`) e Arquivos JSON (`repository`).
* **Padrões Auxiliares:**
  * **Repository (DAO):** Interfaces `IRepositorio...` desacoplam o JSON do negócio.
  * **Observer:** `MapaCadeirasPanel` notifica `CompraView` a cada clique de assento.
  * **Constantes Tipadas & Validador Estático:** Padronização dos motivos em `VendasException` e validações em `Validador`.
* **Roteiro da Demo ao Vivo (`Main.java`):**
  * Cadastrar/Logar Crítico ou Estudante $\rightarrow$ Avaliar Filme $\rightarrow$ Sugerir Assentos Juntos $\rightarrow$ Lanchonete + Cupom $\rightarrow$ Finalizar Compra!

### **Slide 6 — Por que o Padrão Facade?**
* **Problema:** Comprar ou excluir dados exige coordenar `Usuario`, `Sala`, `Sessao`, `Compra`, 4 Controllers e 3 Repositórios.
* **Solução ([`IFachada`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/controller/IFachada.java) + [`Fachada`](file:///C:/Users/Amanda%20Aziz/Documents/cinema/src/controller/Fachada.java)):**
  * Ponto único de comunicação para todas as telas Swing (`view` só enxerga `IFachada`).
  * Coordenação entre controllers: ex. `excluirUsuario` consulta `compraController` antes de chamar `usuarioController`; `excluirFilme` consulta `sessaoController`.

---

## Parte 2: Roteiro de Fala Cronometrado (8 Minutos)

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

## Cola Rápida para Deixar ao Lado na Hora da Apresentação

| Perfil | Login (CPF ou E-mail) | Senha | O que demonstrar |
| :--- | :--- | :--- | :--- |
| **Crítico** | `33333333333` | `1234` | Ingresso a `R$ 0,00` + Aba **Avaliar filmes** |
| **Estudante** | `22222222222` | `1234` | Paga **meia-entrada** + acumula com Cupom |
| **Cliente Comum** | `11111111111` | `1234` | Paga valor cheio (`filme × multiplicador da sala`) |
| **Administrador** | `admin@cinema.com` | `admin123` | Aba Equipe: gerencia filmes e **exclui usuários** |
| **Funcionário** | `func@cinema.com` | `func123` | Aba Equipe: gerencia filmes, mas **bloqueado ao excluir usuário** |

* **Dados válidos caso queira cadastrar alguém ao vivo:**
  * **Nome / Nome no Cartão:** Somente letras (ex.: `Amanda Crítica` / `AMANDA CRITICA`)
  * **CPF:** Exatamente 11 dígitos numéricos (ex.: `44444444444`)
  * **Cartão:** Exatamente 16 dígitos numéricos (ex.: `1111222233334444`)
  * **CVV:** Exatamente 3 dígitos numéricos (ex.: `123`)
