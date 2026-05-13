package com.example.demo.service;

import com.example.demo.model.Disciplina;
import com.example.demo.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository repository;

    public Disciplina salvar(Disciplina entity) {
        return repository.save(entity);
    }

    public List<Disciplina> listar() {
        return repository.findAll();

    }

    public Disciplina buscarPorId(Long id) {
        return repository.findById(id).get();
    }


    public void remover(Long id) {
        repository.deleteById(id);
    }
}