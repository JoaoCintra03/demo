package com.example.demo.controller;

import com.example.demo.model.Professor;
import com.example.demo.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService service;

    @GetMapping
    public String listarProfessores(Model model) {
        model.addAttribute("professores", service.listarProfessores());
        return "professor/professorList";
    }

    @GetMapping("/novo")
    public String abrirForm(Professor professor, Model model) {
        return "professor/professorForm";
    }

    @PostMapping("/salvar")
    public String salvar(Professor professor, Model model) {
        service.salvar(professor);
        return "redirect:/professores";
    }

    @GetMapping("/alterar/{id}")
    public String alterar(@PathVariable Long id, Model model) {
        model.addAttribute("professor", service.buscarPorId(id));
        return "professor/professorForm";
    }

    @GetMapping("/remover/{id}")
    public String remover(@PathVariable Long id, Model model) {
        service.remover(id);
        return "redirect:/professores";
    }
}