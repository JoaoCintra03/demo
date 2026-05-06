package com.example.demo.dao;


import com.example.demo.model.Aluno;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class AlunoDao extends Dao implements DaoInterface {
    @Override
    public boolean salvar(Object entity) {
        try {
            Aluno aluno = (Aluno) entity;

            String sqlInsert = "insert into aluno(nome,cpf,endereco) values(?,?,?)";

            java.sql.PreparedStatement ps = getConnection().prepareStatement(sqlInsert);
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getCpf());
            ps.setString(3, aluno.getEndereco());
            ps.execute();

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean atualizar(Object entity) {
        try {
            var aluno = (Aluno) entity;

            String sqlUpdate = "UPDATE aluno SET nome=?, cpf=?, endereco=? WHERE ra=?";

            var ps = getConnection().prepareStatement(sqlUpdate);
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getCpf());
            ps.setString(3, aluno.getEndereco());
            ps.setLong(4, aluno.getRa());
            ps.execute();

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Aluno> alunos = new ArrayList<>();

        try {
            var resultSet = getConnection()
                    .prepareStatement("select * from aluno")
                    .executeQuery();

            while (resultSet.next()) {
                var aluno = new Aluno(
                        resultSet.getLong("ra"),
                        resultSet.getString("nome"),
                        resultSet.getString("cpf"),
                        resultSet.getString("endereco")
                );
                alunos.add(aluno);
            }

            resultSet.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new ArrayList<>(alunos);
    }

    @Override
    public Object buscarPorId(Long id) {
        var aluno = new Aluno();
        try {
            String sqlRequest = "select * from aluno where ra=?";
            PreparedStatement ps = getConnection().prepareStatement(sqlRequest);
            ps.setLong(1, id);
            var rs = ps.executeQuery();

            while (rs.next()) {
                aluno.setRa(rs.getLong("ra"));
                aluno.setNome(rs.getString("nome"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setEndereco(rs.getString("endereco"));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return aluno;
    }

    @Override
    public boolean deletar(Long id) {
        try{
            String sqlDelete = "DELETE FROM aluno WHERE ra=?";
            var ps = getConnection().prepareStatement(sqlDelete);
            ps.setLong(1, id);
            ps.execute();
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
