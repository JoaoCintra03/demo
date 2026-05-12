package com.example.demo.service;

import com.example.demo.dao.AlunoDao;
import com.example.demo.model.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoDao alunoDao;

    public Aluno buscarPorId(Long id) {
        return (Aluno) alunoDao.buscarPorId(id);
    }

    public List<Aluno> listarAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        alunoDao.listar().forEach(o -> alunos.add((Aluno) o));
        return alunos;
    }

    public boolean salvar(Aluno aluno) {
        if (aluno.getRa() == null) {
            return alunoDao.salvar(aluno);
        } else {
            Aluno existente = (Aluno) alunoDao.buscarPorId(aluno.getRa());
            if (existente.getNome() == null) {
                return alunoDao.salvar(aluno);
            } else {
                return alunoDao.atualizar(aluno);
            }
        }
    }

    public boolean remover(Long ra) {
        return alunoDao.deletar(ra);
    }
}