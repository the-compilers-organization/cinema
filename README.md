
<h1>🎬 Sistema de Cinema</h1>

<p align="center">
  <img src="https://img.shields.io/static/v1?label=Java&message=POO&color=ED8B00&style=for-the-badge&logo=openjdk"/>
  <img src="https://img.shields.io/static/v1?label=Git&message=Versionamento&color=F05032&style=for-the-badge&logo=git"/>
  <img src="https://img.shields.io/static/v1?label=GitHub&message=2026.2&color=181717&style=for-the-badge&logo=github"/>
  <img src="https://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=yellow&style=for-the-badge"/>
  <img src="https://img.shields.io/static/v1?label=License&message=MIT&color=green&style=for-the-badge"/>
</p>

> Status do Projeto: :heavy_check_mark: (concluído) | :warning: (em desenvolvimento) | :x: (não iniciada)

------------------------------------------------------------------------

# Tópicos

:small_blue_diamond: [Contexto](#contexto-information_source)

:small_blue_diamond: [Objetivo](#objetivo-dart)

:small_blue_diamond: [Documentos de Orientação](#documentos-de-orientação-book)

:small_blue_diamond: [Funcionalidades](#funcionalidades-video_game)

:small_blue_diamond: [Estrutura do
Sistema](#estrutura-do-sistema-classical_building)

:small_blue_diamond: [Tipos de
Usuário](#tipos-de-usuário-busts_in_silhouette)

:small_blue_diamond: [Salas e Sessões](#salas-e-sessões-film_projector)

:small_blue_diamond: [Conceitos de POO](#conceitos-de-poo-books)

:small_blue_diamond: [Estrutura do
Projeto](#estrutura-do-projeto-file_folder)

:small_blue_diamond: [Pré-requisitos](#pré-requisitos-warning)

:small_blue_diamond: [Instalação](#instalação-rocket)

:small_blue_diamond: [Como Rodar o
Projeto](#como-rodar-o-projeto-computer)

:small_blue_diamond: [Tecnologias
Utilizadas](#tecnologias-utilizadas-wrench)

:small_blue_diamond: [Desenvolvedoras](#desenvolvedoras-octocat)

:small_blue_diamond: [Informações
Acadêmicas](#informações-acadêmicas-mortar_board)

:small_blue_diamond: [Status do
Projeto](#status-do-projeto-construction)

:small_blue_diamond: [Licença](#licença)

------------------------------------------------------------------------

# Contexto :information_source:


<p align="justify">

O <strong>Sistema de Cinema</strong> é um projeto
desenvolvido para a disciplina de <strong>Programação Orientada
a Objetos</strong>, do 3º período do curso de Ciência da
Computação da Universidade Católica de Pernambuco (UNICAP), durante o
semestre 2026.2.
</p>

<p align="justify">
O projeto consiste na implementação de um sistema de cinema em Java,
envolvendo usuários, filmes, salas, sessões, bilhetes e compras. Durante
seu desenvolvimento serão aplicados, de forma prática, os conceitos
estudados na disciplina de Programação Orientada a Objetos.
</p>

------------------------------------------------------------------------

# Objetivo :dart:

<p align="justify">
O objetivo do projeto é desenvolver um sistema capaz de representar
algumas das principais operações de um cinema, permitindo trabalhar com
usuários, filmes, salas, sessões, escolha de cadeiras, bilhetes e
compras.
</p>

<p align="justify">
Além das funcionalidades do sistema, o projeto tem como objetivo aplicar
conceitos de Programação Orientada a Objetos, como encapsulamento,
associação entre objetos, herança, sobrescrita, sobrecarga, enumerações,
classes abstratas, exceções e padrões de projeto.
</p>

------------------------------------------------------------------------

# Documentos de Orientação :book:

<p align="justify">
O projeto é desenvolvido de forma <strong>incremental</strong>, seguindo as etapas e orientações disponibilizadas pelo professor ao longo da disciplina de Programação Orientada a Objetos.
</p>

<p align="justify">
Os documentos utilizados como base para o desenvolvimento do projeto estão disponíveis na pasta <code>doc</code> do repositório.
</p>

### 📄 Parte 01 — Classes e Encapsulamento

Primeira etapa do projeto, responsável pela definição da estrutura inicial do sistema de cinema, suas classes e relacionamentos.

🔗 [Apresentação de projeto - parte 1](doc/Apresentação%20de%20projeto%20-%20parte%201.pdf)

### 📄 Parte 02 — Herança, Sobrescrita e Sobrecarga

Segunda etapa do projeto, responsável pela evolução da estrutura inicial, acrescentando novos conceitos de Programação Orientada a Objetos, como herança, sobrescrita, sobrecarga e enumerações.

🔗 [Apresentação de projeto - parte 2](doc/Apresentação%20de%20projeto%20-%20parte%202.pdf)

📁 [Acessar todos os documentos do projeto](https://github.com/the-compilers-organization/cinema/tree/main/doc)

------------------------------------------------------------------------

# Funcionalidades :video_game:

### Funcionalidades principais

-   Cadastro e gerenciamento de usuários;
-   Cadastro de filmes;
-   Organização de salas e sessões;
-   Consulta das sessões disponíveis;
-   Escolha de cadeiras;
-   Compra de um ou mais bilhetes;
-   Compra de produtos do cinema;
-   Aplicação de cupons promocionais;
-   Cálculo do valor do ingresso de acordo com o tipo de sala;
-   Desconto para estudantes;
-   Gratuidade de bilhetes para críticos;
-   Avaliação de filmes por críticos;
-   Registro de críticas dos filmes;
-   Sugestão de cadeiras próximas para compras de vários bilhetes.

------------------------------------------------------------------------

# Estrutura do Sistema :classical_building:

O sistema é composto inicialmente pelas seguintes classes:

### 👤 Usuario

Representa o usuário responsável pela compra dos bilhetes.

Entre suas informações estão:

-   Usuário;
-   CPF;
-   Senha;
-   Idade;
-   Sexo;
-   E-mail;
-   Nome do cartão;
-   Número do cartão;
-   Código verificador do cartão.

O usuário poderá realizar, alterar ou cancelar uma compra.

### 🎞️ Filme

Representa um filme exibido nas sessões do cinema.

Possui informações como:

-   Nome;
-   Duração;
-   Sinopse;
-   Valor;
-   Nota;
-   Quantidade de críticos;
-   Críticas recebidas.

### 🎟️ Bilhete

Representa o ingresso adquirido pelo usuário.

O bilhete contém informações relacionadas ao:

-   Usuário;
-   CPF;
-   Sala;
-   Sessão;
-   Filme;
-   Valor;
-   Cadeira escolhida.

### 🏢 Sala

Representa uma sala do cinema.

Cada sala possui uma lista de sessões organizada em uma grade de
horários. Caso não exista uma sessão em determinado horário, aquela
posição poderá permanecer nula e não será apresentada ao usuário.

### 🕐 Sessao

Representa uma sessão do cinema.

A sessão relaciona:

-   Filme exibido;
-   Horário;
-   Cadeiras disponíveis.

As cadeiras podem ser representadas através de uma matriz de tamanho
fixo.

### 🛒 Compra

Representa as compras realizadas pelos usuários.

Uma compra poderá possuir:

-   Um ou mais bilhetes;
-   Produtos do cinema.

O sistema também permite a realização de compras utilizando cupons
promocionais.

### 📝 Critica

Representa um comentário realizado por um crítico sobre determinado
filme.

Possui:

-   Nome;
-   Origem;
-   Mensagem da crítica.

Cada filme poderá armazenar até 100 críticas.

### 🍿 Produtos

Os produtos vendidos pelo cinema são representados através de uma
enumeração (`enum`).

Cada produto possui:

-   Nome;
-   Valor.

Os produtos são definidos pela equipe e integrados às compras realizadas
pelos usuários.

### 🏷️ CupomPromocional

Os cupons promocionais também são representados através de uma
enumeração (`enum`).

O sistema possui três cupons, cada um com seu respectivo desconto.

------------------------------------------------------------------------

# Tipos de Usuário :busts_in_silhouette:

O projeto utiliza **herança** para representar diferentes tipos de
usuários.

### 👤 Usuario

Representa o usuário comum do sistema e funciona como base para as
especializações.

### 🎓 Estudante

A classe `Estudante` herda as características da classe `Usuario`.

Na compra de bilhetes, o estudante paga **metade do valor total**.

### 📝 Critico

A classe `Critico` também herda as características de `Usuario`.

Além dos dados do usuário, possui:

-   Origem do crítico.

O crítico poderá:

-   Comprar bilhetes com valor igual a zero;
-   Atribuir notas de 0 a 10 aos filmes;
-   Registrar críticas sobre os filmes.

Sempre que uma nova nota for atribuída, a quantidade de críticos será
incrementada e uma nova média do filme será calculada.

------------------------------------------------------------------------

# Salas e Sessões :film_projector:

Cada sessão possui as cadeiras disponíveis para compra.

A sala de cinema possui:

-   **10 linhas**;
-   **15 colunas**;
-   **150 cadeiras no total**.

A disponibilidade das cadeiras poderá ser representada utilizando uma
matriz.

Quando apenas um bilhete for comprado, o usuário poderá escolher uma
cadeira livre.

Na compra de vários bilhetes, o sistema poderá sugerir cadeiras próximas
para que as pessoas possam se sentar juntas.

### Tipos de sala

  Tipo     Multiplicador
  ------- ---------------
  Comum         1.0
  3D            1.2
  XD            1.3
  XD/3D         1.4

O multiplicador da sala será utilizado no cálculo do valor do bilhete.

------------------------------------------------------------------------

# Conceitos de POO :books:

Durante o desenvolvimento do projeto serão trabalhados conceitos de
Programação Orientada a Objetos, como:

-   Classes e objetos;
-   Encapsulamento;
-   Construtores;
-   Getters e setters;
-   Associação entre objetos;
-   Vetores de objetos;
-   Matrizes;
-   Enumerações (`enum`);
-   Herança;
-   Polimorfismo;
-   Sobrescrita de métodos;
-   Sobrecarga de métodos;
-   Classes abstratas;
-   Tratamento de exceções;
-   Padrões de projeto.

------------------------------------------------------------------------

# Estrutura do Projeto :file_folder:

A estrutura abaixo poderá ser atualizada conforme o desenvolvimento do
projeto:

``` text
cinema/
│
├── doc/
│   ├── Apresentação de projeto - parte 1.pdf
│   └── Apresentação de projeto - parte 2.pdf
│
├── src/
│   ├── Usuario.java
│   ├── Estudante.java
│   ├── Critico.java
│   ├── Critica.java
│   ├── Filme.java
│   ├── Sala.java
│   ├── Sessao.java
│   ├── Bilhete.java
│   ├── Compra.java
│   ├── Produtos.java
│   ├── CupomPromocional.java
│   └── Main.java
│
├── .gitignore
├── LICENSE
└── README.md
```

------------------------------------------------------------------------

# Pré-requisitos :warning:

Antes de clonar e executar o projeto, é necessário ter instalado:

**1. Java:**

-   JDK instalado e configurado no computador.

**2. Git:**

-   Necessário para clonar e trabalhar com o repositório.

**3. IDE ou editor de código:**

O projeto pode ser desenvolvido utilizando, por exemplo:

-   VS Code;
-   IntelliJ IDEA;
-   Eclipse;
-   NetBeans.

------------------------------------------------------------------------

# Instalação :rocket:

### 1️⃣ Clonar o repositório

``` bash
git clone https://github.com/the-compilers-organization/cinema.git
```

### 2️⃣ Entrar na pasta do projeto

``` bash
cd cinema
```

------------------------------------------------------------------------

# Como Rodar o Projeto :computer:

Após clonar o repositório, abra o projeto em uma IDE compatível com
Java.

Compile os arquivos:

``` bash
javac src/*.java
```

Execute a classe principal:

``` bash
java -cp src Main
```

> Os comandos de compilação e execução poderão ser atualizados de acordo
> com a organização final dos pacotes do projeto.

------------------------------------------------------------------------

# Tecnologias Utilizadas :wrench:

-   **Java**
-   **Programação Orientada a Objetos**
-   **Git**
-   **GitHub**

------------------------------------------------------------------------

# Desenvolvedoras :octocat:

Time responsável pelo desenvolvimento do projeto.

| [<img src="https://github.com/Amanda-Aziz.png" width=115><br><sub>Amanda Aziz</sub>](https://github.com/Amanda-Aziz) | [<img src="https://github.com/francisLauriano.png" width=115><br><sub>Francis Lauriano</sub>](https://github.com/francisLauriano) | [<img src="https://github.com/Sofiafs.png" width=115><br><sub>Sofia Farias</sub>](https://github.com/Sofiafs) |
| :------------------------------------------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------------------------: |

------------------------------------------------------------------------

# Informações Acadêmicas :mortar_board:

**Universidade:** Universidade Católica de Pernambuco - UNICAP\
**Curso:** Ciência da Computação\
**Disciplina:** Programação Orientada a Objetos\
**Período:** 3º período\
**Semestre:** 2026.2\
**Docente:** Jheymesson A. Cavalcanti\
**Discente:**
  - Amanda Aziz | RA:
  - Francis Lauriano | RA: 00000860720
  - Sofia Farias | RA: 

------------------------------------------------------------------------

# Status do Projeto :construction:

:warning: **Em desenvolvimento**

O projeto será desenvolvido de forma incremental durante a disciplina,
incorporando novos conceitos de Programação Orientada a Objetos em cada
etapa.

------------------------------------------------------------------------

# Licença

The [MIT License](LICENSE) (MIT)

Copyright :copyright: 2026 - Sistema de Cinema
