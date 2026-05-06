package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String iniciar(Model model) {
        model.addAttribute("titulo", "UniALFA");
        model.addAttribute("texto1", getTexto1());
        model.addAttribute("texto2", getTexto2());
        return "index";
    }

    private String getTexto1() {
        return """
                Portal de Eventos da Faculdade ALFA Umuarama (UniALFA). Encontre aqui o calendário atualizado de palestras, workshops, cursos de capacitação e eventos científicos realizados pela 3ª melhor instituição privada do Paraná.
                """;
    }

    private String getTexto2() {
        return """
                Jornada Acadêmica e Empresarial, um dos eventos mais importantes para os cursos de gestão.
                """;
    }
}
