package com.example.demo.model;


public class Aluno {

    private Long ra;
    private String nome;
    private String cpf;
    private String endereco;

    public Aluno(){}

    public Aluno(Long ra, String nome, String cpf, String endereco) {
        this.ra = ra;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public Long getRa() {
        return ra;
    }

    public void setRa(Long ra) {
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "ra=" + ra +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}
