package com.example.demo.dao;

import com.example.demo.model.Professor;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProfessorDao extends Dao implements DaoInterface {

    @Override
    public boolean salvar(Object entity) {
        try {
            Professor professor = (Professor) entity;

            String sql = "INSERT INTO professor(nome, titulo, PROFESSOR_ENDEREÇO) VALUES(?, ?, ?)";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, professor.getNome());
            ps.setString(2, professor.getTitulo());
            ps.setString(3, professor.getEndereco());

            ps.execute();
            getConnection().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao salvar professor: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean atualizar(Object entity) {
        try {
            Professor professor = (Professor) entity;

            String sql = "UPDATE professor SET nome=?, titulo=?, PROFESSOR_ENDEREÇO=? WHERE id=?";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, professor.getNome());
            ps.setString(2, professor.getTitulo());
            ps.setString(3, professor.getEndereco());
            ps.setLong(4, professor.getId());

            ps.execute();
            getConnection().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar professor: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> professores = new ArrayList<>();

        try {
            String sql = "SELECT * FROM professor";
            var rs = getConnection().prepareStatement(sql).executeQuery();

            while (rs.next()) {
                Professor p = new Professor();
                p.setId(rs.getLong("id"));
                p.setNome(rs.getString("nome"));
                p.setTitulo(rs.getString("titulo"));
                p.setEndereco(rs.getString("PROFESSOR_ENDEREÇO"));
                professores.add(p);
            }

            rs.close();
        } catch (Exception e) {
            System.out.println("Erro ao listar professores: " + e.getMessage());
        }

        return professores;
    }

    @Override
    public Object buscarPorId(Long id) {
        Professor professor = null;

        try {
            String sql = "SELECT * FROM professor WHERE id=?";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setLong(1, id);
            var rs = ps.executeQuery();

            if (rs.next()) {
                professor = new Professor();
                professor.setId(rs.getLong("id"));
                professor.setNome(rs.getString("nome"));
                professor.setTitulo(rs.getString("titulo"));
                professor.setEndereco(rs.getString("PROFESSOR_ENDEREÇO"));
            }

            rs.close();
        } catch (Exception e) {
            System.out.println("Erro ao buscar professor: " + e.getMessage());
        }

        return professor;
    }

    @Override
    public boolean deletar(Long id) {
        try {
            String sql = "DELETE FROM professor WHERE id=?";
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setLong(1, id);
            ps.execute();
            getConnection().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao deletar professor: " + e.getMessage());
            return false;
        }
    }
}