package com.example.demo.service;

import com.example.demo.dao.ProfessorDao;
import com.example.demo.model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorDao professorDao;

    public Professor buscarPorId(Long id) {
        return (Professor) professorDao.buscarPorId(id);
    }

    public List<Professor> listarProfessores() {
        List<Professor> professores = new ArrayList<>();
        professorDao.listar().forEach(o -> professores.add((Professor) o));
        return professores;
    }

    public boolean salvar(Professor professor) {
        if (professor.getId() == null) {
            return professorDao.salvar(professor);
        } else {
            Professor existente = (Professor) professorDao.buscarPorId(professor.getId());

            if (existente == null || existente.getNome() == null) {
                return professorDao.salvar(professor);
            } else {
                return professorDao.atualizar(professor);
            }
        }
    }

    public boolean remover(Long id) {
        return professorDao.deletar(id);
    }
}