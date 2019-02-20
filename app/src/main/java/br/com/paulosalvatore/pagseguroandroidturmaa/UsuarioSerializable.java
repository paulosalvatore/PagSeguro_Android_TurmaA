package br.com.paulosalvatore.pagseguroandroidturmaa;

import java.io.Serializable;

public class UsuarioSerializable implements Serializable {
    private String nome;
    private String sobrenome;

    public UsuarioSerializable(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
}
