package com.example.demo.service;

import com.example.demo.dao.AlunoDao;
import com.example.demo.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoService {

    public Aluno buscarPorId(Long id) {
        var alunoDao = new AlunoDao();
        return (Aluno) alunoDao.buscarPorId(id);
    }

    public List<Aluno> listarAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        var alunoDao = new AlunoDao();
        alunoDao.listar().forEach(o -> alunos.add((Aluno) o));
        return alunos;
    }

    public boolean salvar(Aluno aluno) {
        var alunoDao = new AlunoDao();

        return aluno.getRa() == null
                ? alunoDao.salvar(aluno)
                : alunoDao.atualizar(aluno);
    }

    public boolean remover(Long ra) {
        var dao = new AlunoDao();
        return dao.deletar(ra);
    }
}
