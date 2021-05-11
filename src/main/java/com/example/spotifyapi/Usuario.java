package com.example.spotifyapi;

// import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // @Column(name ="email")
    private String email;
    // @Column(name ="senha")
    private String senha;
    // @Column(name ="nome")
    private String nome;

    private String data;

    public Usuario() {
        
    }

    public Usuario(String email, String senha, String nome, String data) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.data = data;
    }

    public long getId() {
        return id;
    }  

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Usuario [data=" + data + ", email=" + email + ", id=" + id + ", nome=" + nome + ", senha=" + senha
                + "]";
    }  

}