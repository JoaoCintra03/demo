package com.example.demo.controller;

import com.example.demo.model.Aluno;
import com.example.demo.service.AlunoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("alunos")
public class AlunoController {

    @GetMapping
    public String listarAlunos(Model model) {
        var service = new AlunoService();
        var alunos = service.listarAlunos();
        model.addAttribute("alunos", alunos);
        return "aluno/alunoList";
    }

    @GetMapping("/new")
    public String abrirform(Aluno aluno, Model model) {
        return "aluno/alunoForm";
    }

    @PostMapping("/salvar")
    public String salvar(Aluno aluno, Model model) {
        var service = new AlunoService();
        service.salvar(aluno);
        return "redirect:/alunos";
    }

    @GetMapping("/alterar/{ra}")
    public String alterar(@PathVariable Long ra, Model model) {
        var service = new AlunoService();
        var aluno = service.buscarPorId(ra);
        model.addAttribute("aluno", aluno);
        return "aluno/alunoForm";
    }

    @GetMapping("/remover/{ra}")
    public String remover(@PathVariable Long ra, Model model){
        var service = new AlunoService();
        service.remover(ra);
        return "redirect:/alunos";
    }
}
