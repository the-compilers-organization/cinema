# 🎬 Sistema de Cinema — Projeto de Programação Orientada a Objetos

Projeto desenvolvido para a disciplina de **Programação Orientada a Objetos**, do **3º período do curso de Ciência da Computação da Universidade Católica de Pernambuco (UNICAP)**, no semestre **2026.2**.

**Professor:** Jheymesson A. Cavalcanti

---

## 📖 Sobre o projeto

O projeto consiste no desenvolvimento de um **sistema de cinema utilizando Programação Orientada a Objetos em Java**.

O sistema busca representar algumas das principais funcionalidades relacionadas à compra de ingressos de cinema, como cadastro de usuários, filmes, salas, sessões, bilhetes e compras.

Ao longo do desenvolvimento, serão aplicados conceitos estudados na disciplina, como:

- Classes e objetos;
- Encapsulamento;
- Construtores;
- Getters e setters;
- Associação entre objetos;
- Vetores e matrizes;
- Enumerações (`enum`);
- Herança;
- Sobrescrita de métodos;
- Sobrecarga de métodos;
- Classes abstratas;
- Tratamento de exceções;
- Padrões de projeto.

---

## 🎯 Objetivo

O objetivo do projeto é aplicar, de forma prática, os conceitos de **Programação Orientada a Objetos**, desenvolvendo um sistema capaz de representar o funcionamento de um cinema e as interações entre usuários, filmes, salas, sessões, bilhetes e compras.

---

## 🏗️ Estrutura do sistema

O sistema é composto inicialmente pelas seguintes classes:

### 👤 Usuario

Representa o usuário responsável pela compra dos bilhetes.

Entre as informações associadas ao usuário estão:

- Usuário;
- CPF;
- Senha;
- Idade;
- Sexo;
- E-mail;
- Dados do cartão.

O usuário poderá realizar, alterar e cancelar compras.

### 🎞️ Filme

Representa os filmes disponíveis no cinema.

Cada filme possui informações como:

- Nome;
- Duração;
- Sinopse;
- Valor;
- Nota;
- Quantidade de críticos;
- Críticas recebidas.

### 🎟️ Bilhete

Representa o ingresso adquirido pelo usuário.

O bilhete reúne informações relacionadas ao:

- Usuário;
- CPF;
- Sala;
- Sessão;
- Filme;
- Valor;
- Cadeira escolhida.

### 🏢 Sala

Representa uma sala do cinema.

Cada sala possui uma grade de sessões em horários definidos. Caso não exista uma sessão em determinado horário, aquela posição poderá permanecer vazia.

O sistema também considera diferentes tipos de sala, com multiplicadores aplicados ao valor do ingresso:

| Tipo de sala | Multiplicador |
|--------------|:-------------:|
| Comum | 1.0 |
| 3D | 1.2 |
| XD | 1.3 |
| XD/3D | 1.4 |

### 🕐 Sessao

Representa uma sessão de cinema, relacionando o filme exibido às cadeiras disponíveis.

Cada sessão possui uma sala com:

- **10 linhas**;
- **15 colunas**;
- Total de **150 lugares**.

A disponibilidade das cadeiras pode ser representada utilizando uma matriz.

### 🛒 Compra

Representa as compras realizadas pelo usuário.

Uma compra pode conter:

- Um ou mais bilhetes;
- Produtos vendidos no cinema;
- Cupom promocional.

Entre os produtos podem estar pipoca, refrigerante, chocolate e outros itens definidos pela equipe.

---

## 🍿 Produtos

Os produtos disponíveis no cinema são representados através de uma enumeração (`enum`).

Cada produto possui:

- Nome;
- Valor.

Esses produtos podem ser adicionados às compras realizadas pelos usuários.

---

## 🏷️ Cupons promocionais

O sistema também possui cupons promocionais implementados através de `enum`.

Cada cupom possui um desconto que poderá ser aplicado às compras de produtos ou bilhetes.

---

## 👥 Tipos de usuário

Além do usuário comum, o projeto possui especializações utilizando **herança**.

### 🎓 Estudante

A classe `Estudante` herda as características de `Usuario`.

Na compra de bilhetes, o estudante possui o benefício de pagar **metade do valor do ingresso**.

### 📝 Critico

A classe `Critico` também é uma especialização de `Usuario`.

Além dos dados herdados, possui a informação da organização ou órgão ao qual o crítico está ligado.

O crítico poderá:

- Comprar bilhetes com valor zerado;
- Atribuir notas aos filmes;
- Registrar críticas sobre os filmes.

---

## ⭐ Avaliação dos filmes

Os críticos poderão avaliar os filmes através de notas de **0 a 10**.

Sempre que uma nova nota for atribuída:

1. A quantidade de críticos que avaliaram o filme será atualizada;
2. A média das avaliações do filme será recalculada.

Também será possível registrar comentários através da classe `Critica`.

---

## 💺 Escolha de cadeiras

Cada sessão possui uma representação das cadeiras disponíveis na sala.

O usuário poderá escolher uma cadeira livre ao comprar um bilhete.

Quando vários bilhetes forem comprados, o sistema poderá procurar e sugerir cadeiras próximas para que as pessoas possam sentar juntas.

---

## 💻 Tecnologias utilizadas

- **Java**
- **Programação Orientada a Objetos**
- **Git**
- **GitHub**

---

## 📚 Conceitos de POO aplicados

Durante o desenvolvimento do projeto serão utilizados conceitos como:

- Encapsulamento;
- Composição e associação entre objetos;
- Herança;
- Polimorfismo;
- Sobrescrita de métodos;
- Sobrecarga de métodos;
- Enumerações;
- Classes abstratas;
- Tratamento de exceções;
- Padrões de projeto.

---

## 👩‍💻 Desenvolvedoras

| Desenvolvedora | GitHub |
|----------------|--------|
| Amanda Aziz | [@Amanda-Aziz](https://github.com/Amanda-Aziz) |
| Francis Lauriano | [@francisLauriano](https://github.com/francisLauriano) |
| Sofia Farias | [@Sofiafs](https://github.com/Sofiafs) |

---

## 🎓 Informações acadêmicas

**Universidade:** Universidade Católica de Pernambuco — UNICAP  
**Curso:** Ciência da Computação  
**Disciplina:** Programação Orientada a Objetos  
**Período:** 3º período  
**Semestre:** 2026.2  
**Professor:** Jheymesson A. Cavalcanti

---

## 📌 Status do projeto

🚧 **Em desenvolvimento**

O projeto será desenvolvido de forma incremental durante a disciplina, incorporando novos conceitos de Programação Orientada a Objetos em cada etapa.
