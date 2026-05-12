package com.example.demo.controller;

import com.example.demo.model.Aluno;
import com.example.demo.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService service;

    @GetMapping
    public String listarAlunos(Model model) {
        model.addAttribute("alunos", service.listarAlunos());
        return "aluno/alunoList";
    }

    @GetMapping("/novo")
    public String abrirForm(Aluno aluno, Model model) {
        return "aluno/alunoForm";
    }

    @PostMapping("/salvar")
    public String salvar(Aluno aluno, Model model) {
        service.salvar(aluno);
        return "redirect:/alunos";
    }

    @GetMapping("/alterar/{ra}")
    public String alterar(@PathVariable Long ra, Model model) {
        model.addAttribute("aluno", service.buscarPorId(ra));
        return "aluno/alunoForm";
    }

    @GetMapping("/remover/{ra}")
    public String remover(@PathVariable Long ra, Model model) {
        service.remover(ra);
        return "redirect:/alunos";
    }
}