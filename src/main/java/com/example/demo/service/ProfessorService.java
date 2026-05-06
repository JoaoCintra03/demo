package com.example.demo.service;

import com.example.demo.model.Professor;
import com.example.demo.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository repository;

    public Professor salvar(Professor entity) {
        return repository.save(entity);
    }

    public List<Professor> listar(){
        return repository.findAll();
    }
}