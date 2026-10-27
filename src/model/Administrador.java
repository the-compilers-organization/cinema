package model;

import enums.Sexo;
import interfaces.GerenciaFilmes;
import java.util.ArrayList;

// A classe Administrador é uma classe concreta que deve possuir as seguintes
    // características:
    // ○ Atributos: salário e ID.
    // ○ Métodos: getters e setters para os atributos;
    // ○ Métodos implementados na classe abstrata;
    // ○ Método: Excluir usuário;
    // ○ Implementar métodos de gerência de filmes.
    // OBS: Usar ArrayList. Exemplos: 
    // ArrayList<Usuario> usuarios
    // ArrayList<Filme> filmes

public class Administrador extends Base implements GerenciaFilmes{

    private int id;
    private double salario;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Filme> filmes;

    public Administrador(int id, double salario, String nome, int idade, String email){
        super(nome, idade, email);
        this.id = id;
        this.salario = salario;
        this.usuarios = new ArrayList<Usuario>();
        this.filmes = new ArrayList<Filme>();

    }


    // ○ Métodos implementados na classe abstrata;

    public int buscarUsuario(String cpf){

        return -1;
    }


    public boolean adicionarUsuario(Usuario usuario){


        return false;
    }

    public boolean alterarUsuario(Usuario usuarioAtualizado){

        return false;
    }


    // ○ Método: Excluir usuário;
        



    // ○ Implementar métodos de gerência de filmes.
    public int buscarFilme(String nome){

        return -1;
    }
    
    public boolean incluirFilme(Filme filme){

        return false;
    }

    public boolean excluirFilme(Filme filme){

        return false;
    }

    public boolean alterarFilme(Filme filme){

        return false;
    }

    


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public double getSalario() {
        return salario;
    }


    public void setSalario(double salario) {
        this.salario = salario;
    }


    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }


    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }


    public ArrayList<Filme> getFilmes() {
        return filmes;
    }


    public void setFilmes(ArrayList<Filme> filmes) {
        this.filmes = filmes;
    }


    
        
}
