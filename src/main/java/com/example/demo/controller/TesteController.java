package com.example.demo.controller;

import com.example.demo.dao.AlunoDao;
import com.example.demo.model.Aluno;
import com.example.demo.service.AlunoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TesteController {


    public String testar(Model model){
        model.addAttribute("titulo","Formulário de Aluno");
        var alunoService = new AlunoService();
        var aluno1 = alunoService.buscarPorId(1L);
        model.addAttribute("nome", aluno1);
        System.out.println(aluno1);
        return "formaluno";
    }

}
