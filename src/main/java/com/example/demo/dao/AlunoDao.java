package com.example.demo.dao;

import com.example.demo.model.Aluno;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Component
public class AlunoDao extends Dao implements DaoInterface {

    @Override
    public boolean salvar(Object entity) {
        try {
            Aluno aluno = (Aluno) entity;

            String sql = "INSERT INTO aluno(ra, nome, cpf, endereco) VALUES(?, ?, ?, ?)";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setLong(1, aluno.getRa());
            ps.setString(2, aluno.getNome());
            ps.setString(3, aluno.getCpf());
            ps.setString(4, aluno.getEndereco());
            ps.execute();
            getConnection().commit(); // ← adicione isso
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean atualizar(Object entity) {
        try {
            Aluno aluno = (Aluno) entity;

            String sql = "UPDATE aluno SET nome=?, cpf=?, endereco=? WHERE ra=?";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getCpf());
            ps.setString(3, aluno.getEndereco());
            ps.setLong(4, aluno.getRa());
            ps.execute();

            return true;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> alunos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM aluno";
            var rs = getConnection().prepareStatement(sql).executeQuery();

            while (rs.next()) {
                alunos.add(new Aluno(
                        rs.getLong("ra"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("endereco")
                ));
            }

            rs.close();
        } catch (Exception e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }

        return alunos;
    }

    @Override
    public Object buscarPorId(Long id) {
        Aluno aluno = new Aluno();

        try {
            String sql = "SELECT * FROM aluno WHERE ra=?";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setLong(1, id);
            var rs = ps.executeQuery();

            if (rs.next()) {
                aluno.setRa(rs.getLong("ra"));
                aluno.setNome(rs.getString("nome"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setEndereco(rs.getString("endereco"));
            }

            rs.close();
        } catch (Exception e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
        }

        return aluno;
    }

    @Override
    public boolean deletar(Long id) {
        try {
            String sql = "DELETE FROM aluno WHERE ra=?";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setLong(1, id);
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao deletar: " + e.getMessage());
            return false;
        }
    }
}