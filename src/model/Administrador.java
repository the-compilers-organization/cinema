package model;

import java.util.ArrayList;

import model.interfaces.GerenciaFilmes;

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
        if(cpf != null){
            int i = 0;

            for(Usuario usuario : usuarios){
                if(usuario.getCpf().equals(cpf)){
                    return i;
                }
                i += 1;
            }
        }

        return -1;
    }



    public boolean adicionarUsuario(Usuario usuario){
        if(usuario != null){
            int indice = buscarUsuario(usuario.getCpf());

            if(indice == -1){
                usuarios.add(usuario);
                return true;
            }

        }


        return false;
    }

    // regra de negocio: não pode alterar cpf do usuario
    public boolean alterarUsuario(Usuario usuarioAtualizado){
        if(usuarioAtualizado != null){
            int indice = buscarUsuario(usuarioAtualizado.getCpf());

            if(indice != -1){
                usuarios.set(indice, usuarioAtualizado);
                return true;
            }

        }

        return false;
    }


    // ○ Método: Excluir usuário;
    public boolean excluirUsuario(Usuario usuarioExcluido){
        if(usuarioExcluido != null){
            int indice = buscarUsuario(usuarioExcluido.getCpf());

            if(indice != -1){
                usuarios.remove(indice);
                return true;
            }
        }

        return false;
    }
        



    // ○ Implementar métodos de gerência de filmes.
    public int buscarFilme(String nome){
        if(nome != null){
            int i = 0;

            for(Filme filme : filmes){
                if(filme.getNome().equals(nome)){
                    return i;
                }
                i += 1;
            }

        }

        return -1;
    }
    
    public boolean incluirFilme(Filme filme){
        if(filme != null){
            int indice = buscarFilme(filme.getNome());

            if(indice == -1){
                filmes.add(filme);
                return true;
            }
        }

        return false;
    }

    
    // regra de negocio: não pode alterar nome do filme
    public boolean alterarFilme(Filme filmeAtualizado){
        if(filmeAtualizado != null){
            int indice = buscarFilme(filmeAtualizado.getNome());

            if(indice != -1){
                filmes.set(indice, filmeAtualizado);
                return true;
            }

        }

        return false;
    }

    public boolean excluirFilme(Filme filmeExcluido){
        if(filmeExcluido != null){
            int indice = buscarFilme(filmeExcluido.getNome());

            if(indice != -1){
                filmes.remove(indice);
                return true;
            }

        }

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
