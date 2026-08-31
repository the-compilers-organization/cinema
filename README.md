<h1 align="center">🎬 Sistema de Cinema</h1>

<p align="center">
  <img src="https://img.shields.io/static/v1?label=Java&message=POO&color=ED8B00&style=for-the-badge&logo=openjdk"/>
  <img src="https://img.shields.io/static/v1?label=Git&message=Versionamento&color=F05032&style=for-the-badge&logo=git"/>
  <img src="https://img.shields.io/static/v1?label=GitHub&message=2026.2&color=181717&style=for-the-badge&logo=github"/>
  <img src="https://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=yellow&style=for-the-badge"/>
  <img src="https://img.shields.io/static/v1?label=License&message=MIT&color=green&style=for-the-badge"/>
</p>

> **Status do Projeto:** :heavy_check_mark: (concluído) | :warning: (em desenvolvimento) | :x: (não iniciada)

---

# Tópicos

:small_blue_diamond: [Contexto](#contexto-information_source)

:small_blue_diamond: [Objetivo](#objetivo-dart)

:small_blue_diamond: [Documentos de Orientação](#documentos-de-orientação-book)

:small_blue_diamond: [Funcionalidades](#funcionalidades-video_game)

:small_blue_diamond: [Estrutura do Sistema](#estrutura-do-sistema-classical_building)

:small_blue_diamond: [Tipos de Usuário](#tipos-de-usuário-busts_in_silhouette)

:small_blue_diamond: [Salas e Sessões](#salas-e-sessões-film_projector)

:small_blue_diamond: [Conceitos de POO](#conceitos-de-poo-books)

:small_blue_diamond: [Etapas do Projeto](#etapas-do-projeto-clipboard)

:small_blue_diamond: [Estrutura do Projeto](#estrutura-do-projeto-file_folder)

:small_blue_diamond: [Pré-requisitos](#pré-requisitos-warning)

:small_blue_diamond: [Instalação](#instalação-rocket)

:small_blue_diamond: [Como Rodar o Projeto](#como-rodar-o-projeto-computer)

:small_blue_diamond: [Tecnologias Utilizadas](#tecnologias-utilizadas-wrench)

:small_blue_diamond: [Desenvolvedoras](#desenvolvedoras-octocat)

:small_blue_diamond: [Informações Acadêmicas](#informações-acadêmicas-mortar_board)

:small_blue_diamond: [Status do Projeto](#status-do-projeto-construction)

:small_blue_diamond: [Licença](#licença)

---

# Contexto :information_source:

<p align="justify">

O <strong>Sistema de Cinema</strong> é um projeto desenvolvido para a disciplina de <strong>Programação Orientada a Objetos</strong>, do 3º período do curso de Ciência da Computação da Universidade Católica de Pernambuco (UNICAP), durante o semestre 2026.2.

</p>

<p align="justify">

O projeto consiste na implementação de um sistema de cinema em Java, envolvendo usuários, filmes, salas, sessões, bilhetes e compras. Durante seu desenvolvimento serão aplicados, de forma prática, os conceitos estudados na disciplina de Programação Orientada a Objetos.

</p>

<p align="justify">

O desenvolvimento será realizado de forma incremental. A estrutura inicial será ampliada ao longo da disciplina à medida que novos conceitos de Programação Orientada a Objetos forem apresentados.

</p>

---

# Objetivo :dart:

<p align="justify">

O objetivo do projeto é desenvolver um sistema capaz de representar algumas das principais operações de um cinema, permitindo trabalhar com usuários, filmes, salas, sessões, escolha de cadeiras, bilhetes e compras.

</p>

<p align="justify">

O usuário poderá consultar as sessões disponíveis, selecionar uma sessão, escolher uma ou mais cadeiras e realizar a compra de bilhetes. Também será possível incluir outros itens disponíveis no cinema durante uma compra.

</p>

<p align="justify">

Além das funcionalidades do sistema, o projeto tem como objetivo aplicar conceitos de Programação Orientada a Objetos, como encapsulamento, associação entre objetos, herança, sobrescrita, sobrecarga, enumerações, classes abstratas, exceções e padrões de projeto, de acordo com a evolução do projeto durante a disciplina.

</p>

---

# Documentos de Orientação :book:

<p align="justify">

O projeto é desenvolvido de forma <strong>incremental</strong>, seguindo as etapas e orientações disponibilizadas pelo professor ao longo da disciplina de Programação Orientada a Objetos.

</p>

<p align="justify">

Os documentos utilizados como base para o desenvolvimento do projeto estão disponíveis na pasta <code>doc</code> do repositório.

</p>

### 📄 Parte 01 — Classes e Encapsulamento

Primeira etapa do projeto, responsável pela definição da estrutura inicial do sistema de cinema, suas classes e relacionamentos.

🔗 [Apresentação de projeto - Parte 1](doc/apresentacao_de_projeto_parte1.pdf)

### 📄 Parte 02 — Herança, Sobrescrita e Sobrecarga

Segunda etapa do projeto, responsável pela evolução da estrutura inicial, acrescentando novos conceitos de Programação Orientada a Objetos, como herança, sobrescrita, sobrecarga e enumerações.

🔗 [Apresentação de projeto - Parte 2](doc/apresentacao_de_projeto_parte2.pdf)

📁 [Acessar todos os documentos do projeto](https://github.com/the-compilers-organization/cinema/tree/main/doc)

---

# Funcionalidades :video_game:

### Funcionalidades principais

O sistema deverá permitir:

- cadastrar e trabalhar com usuários;
- consultar os filmes disponíveis;
- consultar as sessões disponíveis nas salas;
- visualizar o filme e o horário de cada sessão;
- visualizar as cadeiras disponíveis em uma sessão;
- escolher uma cadeira para a compra de um bilhete;
- comprar um ou mais bilhetes;
- sugerir cadeiras próximas quando vários bilhetes forem comprados;
- adicionar itens disponíveis no cinema à compra;
- realizar uma compra;
- alterar uma compra;
- cancelar uma compra.

### Itens adicionais

Além dos bilhetes, uma compra poderá conter itens como:

- pipoca;
- refrigerante;
- chocolate;
- outros itens disponíveis no cinema.

Para esses itens, não será necessário realizar controle de estoque. O projeto considera que o estoque dos produtos vendidos é infinito.

---

# Estrutura do Sistema :classical_building:

A estrutura inicial do sistema é formada por seis classes principais:

| Classe | Responsabilidade |
| :--- | :--- |
| `Usuario` | Representa o usuário responsável pela compra dos bilhetes. |
| `Filme` | Representa um filme exibido pelo cinema. |
| `Sala` | Representa uma sala e sua lista de sessões. |
| `Sessao` | Representa uma sessão, seu filme e suas cadeiras. |
| `Bilhete` | Representa um bilhete adquirido pelo usuário. |
| `Compra` | Representa uma compra contendo bilhetes e outros itens. |

Por padrão, as classes deverão possuir:

- atributos `private`;
- métodos `public`;
- construtores;
- getters;
- setters.

---

## 👤 Usuario

A classe `Usuario` representa a pessoa responsável pela compra dos bilhetes online.

Entre suas informações estão:

- usuário;
- CPF;
- senha;
- idade;
- sexo;
- e-mail;
- nome do cartão;
- número do cartão;
- código verificador do cartão.

O usuário poderá:

- realizar uma compra;
- alterar uma compra;
- cancelar uma compra.

---

## 🎬 Filme

A classe `Filme` representa o filme exibido em uma sessão.

Deverá possuir informações como:

- nome;
- duração;
- sinopse;
- valor.

---

## 🎟️ Bilhete

A classe `Bilhete` representa o ingresso adquirido pelo usuário.

O bilhete deverá conter informações relacionadas a:

- usuário;
- CPF;
- sala;
- sessão;
- filme;
- valor;
- cadeira comprada.

O valor do bilhete será calculado com base no filme e na sala escolhidos.

---

## 🏢 Sala

A classe `Sala` representa uma sala do cinema.

Cada sala deverá possuir uma lista de sessões organizada de acordo com uma grade de horários.

Caso não exista uma sessão em determinado horário, aquela posição será registrada como `null` e não deverá aparecer para o usuário.

---

## 🕐 Sessao

A classe `Sessao` representa uma sessão disponível em uma sala do cinema.

Cada sessão deverá conter:

- o filme exibido;
- as cadeiras disponíveis para compra.

A sessão permitirá verificar quais cadeiras ainda estão disponíveis e quais já foram escolhidas.

---

## 🛒 Compra

A classe `Compra` representa as compras realizadas pelo usuário.

Uma compra poderá possuir:

- um ou mais bilhetes;
- pipoca;
- refrigerante;
- chocolate;
- outros itens disponíveis.

O método responsável pela compra dos bilhetes deverá ser implementado separadamente.

Ao comprar apenas um bilhete, o usuário poderá escolher uma cadeira livre.

Quando vários bilhetes forem comprados, o sistema deverá sugerir cadeiras que estejam próximas, permitindo que as pessoas possam se sentar juntas.

---

# Tipos de Usuário :busts_in_silhouette:

Na estrutura inicial do projeto existe um usuário responsável pela compra dos bilhetes online.

Esse usuário possui seus dados pessoais, informações de acesso e informações relacionadas ao cartão utilizado para realizar a compra.

Entre as operações relacionadas ao usuário estão:

- realizar uma compra;
- alterar uma compra;
- cancelar uma compra.

Novos tipos de usuário poderão ser acrescentados posteriormente caso sejam definidos nas próximas etapas do projeto.

---

# Salas e Sessões :film_projector:

Cada sala possuirá uma lista de sessões organizada em uma grade de horários.

Uma sessão deverá relacionar:

```text
Sala
  │
  └── Sessao
        │
        ├── Filme
        │
        └── Cadeiras
```

Caso um determinado horário não possua sessão cadastrada, sua posição será representada por `null`.

### Exemplo de grade de sessões

| Horário | Filme |
| :---: | :--- |
| 08:00 - 10:00 | Gato de Botas 2 |
| 10:00 - 12:00 | Shazam! 2 |
| 12:00 - 14:00 | Avatar: O Caminho da Água |
| 14:00 - 16:00 | Homem-Formiga e Vespa |
| 16:00 - 18:00 | John Wick 4: Baba Yaga |
| 18:00 - 20:00 | Pânico VI |
| 20:00 - 22:00 | Creed III |

### 💺 Cadeiras

As cadeiras de uma sessão poderão ser implementadas utilizando uma matriz de tamanho fixo.

A sala de cinema possui:

- **10 linhas**;
- **15 colunas**.

Portanto, a estrutura poderá ser representada inicialmente por uma matriz:

```java
boolean[][] cadeiras = new boolean[10][15];
```

Por exemplo:

```text
                 TELA

       01 02 03 04 05 06 07 08 ... 15

01      O  O  O  O  O  O  O  O ... O
02      O  O  X  X  O  O  O  O ... O
03      O  O  O  O  O  O  O  O ... O
04      O  X  O  O  O  O  O  O ... O
05      O  O  O  O  O  O  O  O ... O
06      O  O  O  O  O  O  O  O ... O
07      O  O  O  O  O  O  O  O ... O
08      O  O  O  O  O  O  O  O ... O
09      O  O  O  O  O  O  O  O ... O
10      O  O  O  O  O  O  O  O ... O

O = cadeira disponível
X = cadeira ocupada
```

A implementação definitiva poderá ser alterada conforme a evolução das classes e as orientações das próximas etapas.

---

# Conceitos de POO :books:

Durante o desenvolvimento do projeto serão aplicados progressivamente conceitos de Programação Orientada a Objetos.

Entre eles:

- classes;
- objetos;
- atributos;
- métodos;
- encapsulamento;
- modificadores de acesso;
- construtores;
- getters e setters;
- associação entre objetos;
- vetores;
- vetores de objetos;
- matrizes;
- herança;
- sobrescrita;
- sobrecarga;
- enumerações;
- classes abstratas;
- exceções;
- padrões de projeto.

Os conceitos serão incorporados conforme forem apresentados durante a disciplina.

---

# Etapas do Projeto :clipboard:

O projeto será desenvolvido de forma incremental.

### Etapa 01 — Classes e Encapsulamento

⚠️ **Em desenvolvimento**

Criação da estrutura inicial do sistema, incluindo as classes:

- `Usuario`;
- `Sala`;
- `Sessao`;
- `Filme`;
- `Bilhete`;
- `Compra`.

Nesta etapa serão trabalhados principalmente:

- classes;
- objetos;
- atributos;
- métodos;
- encapsulamento;
- construtores;
- getters e setters;
- relacionamentos entre objetos.

### Etapa 02 — Herança, Sobrescrita e Sobrecarga

❌ **Não iniciada**

Evolução da estrutura inicial do projeto com a aplicação dos novos conceitos definidos para a segunda etapa.

### Etapa 03 — Classes Abstratas e Exceções

❌ **Não iniciada**

Implementação de classes abstratas e tratamento de exceções no sistema.

### Etapa 04 — Padrões de Projeto

❌ **Não iniciada**

Aplicação de padrões de projeto e preparação da versão final do sistema.

---

# Estrutura do Projeto :file_folder:

A organização inicial do repositório poderá seguir a seguinte estrutura:

```text
cinema/
│
├── doc/
│   ├── apresentacao_de_projeto_parte1.pdf
│   └── apresentacao_de_projeto_parte2.pdf
│
├── src/
│   ├── Main.java
│   ├── Usuario.java
│   ├── Filme.java
│   ├── Sala.java
│   ├── Sessao.java
│   ├── Bilhete.java
│   └── Compra.java
│
├── README.md
└── LICENSE
```

A estrutura poderá ser modificada conforme o desenvolvimento e as novas etapas do projeto.

Inicialmente, a entrada e a saída de dados poderão ser realizadas pelo terminal da IDE através da classe `Main.java`.

A classe `Main` será utilizada para criar os objetos e testar a interação entre as classes durante o desenvolvimento inicial do sistema.

---

# Pré-requisitos :warning:

Antes de clonar e executar o projeto, é necessário ter instalado:

### 1. Java

- JDK instalado e configurado no computador.

### 2. Git

- Necessário para clonar e trabalhar com o repositório.

### 3. IDE ou editor de código

O projeto pode ser desenvolvido utilizando, por exemplo:

- VS Code;
- IntelliJ IDEA;
- Eclipse;
- NetBeans.

---

# Instalação :rocket:

### 1️⃣ Clonar o repositório

```bash
git clone https://github.com/the-compilers-organization/cinema.git
```

### 2️⃣ Entrar na pasta do projeto

```bash
cd cinema
```

---

# Como Rodar o Projeto :computer:

Após clonar o repositório, abra o projeto em uma IDE compatível com Java.

Compile os arquivos:

```bash
javac src/*.java
```

Execute a classe principal:

```bash
java -cp src Main
```

> Os comandos de compilação e execução poderão ser atualizados de acordo com a organização final dos pacotes do projeto.

---

# Tecnologias Utilizadas :wrench:

- **Java**
- **Programação Orientada a Objetos**
- **Git**
- **GitHub**

---

# Desenvolvedoras :octocat:

Time responsável pelo desenvolvimento do projeto.

| [<img src="https://github.com/Amanda-Aziz.png" width="115"><br><sub>Amanda Aziz</sub>](https://github.com/Amanda-Aziz) | [<img src="https://github.com/francisLauriano.png" width="115"><br><sub>Francis Lauriano</sub>](https://github.com/francisLauriano) | [<img src="https://github.com/Sofiafs.png" width="115"><br><sub>Sofia Farias</sub>](https://github.com/Sofiafs) |
| :---: | :---: | :---: |

---

# Informações Acadêmicas :mortar_board:

**Universidade:** Universidade Católica de Pernambuco - UNICAP  
**Curso:** Ciência da Computação  
**Disciplina:** Programação Orientada a Objetos  
**Período:** 3º período  
**Semestre:** 2026.2  
**Docente:** Jheymesson A. Cavalcanti  

### Discentes

| Discente | RA |
| :--- | :---: |
| Amanda Aziz | 00000859509 |
| Francis Lauriano | 00000860720 |
| Sofia Farias | xxxxxxxxxxx |

---

# Status do Projeto :construction:

⚠️ **Em desenvolvimento**

O projeto será desenvolvido de forma incremental durante a disciplina, incorporando novos conceitos de Programação Orientada a Objetos em cada etapa.

| Etapa | Status |
| :--- | :---: |
| Classes e Encapsulamento | ⚠️ Em desenvolvimento |
| Herança, Sobrescrita e Sobrecarga | ❌ Não iniciada |
| Classes Abstratas e Exceções | ❌ Não iniciada |
| Padrões de Projeto | ❌ Não iniciada |

### Legenda

- ✅ Concluído
- ⚠️ Em desenvolvimento
- ❌ Não iniciada

---

# Licença

The [MIT License](LICENSE) (MIT)

Copyright © 2026 - Sistema de Cinema
