package com.example.demo.controller;

import com.example.demo.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfessorController {
    @Autowired
    private ProfessorService service;

    @GetMapping("/professores")
    public String listar(Model model){
        model.addAttribute("professores");
        return "professor/profList";
    }
}
