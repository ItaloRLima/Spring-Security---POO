package br.com.springboot.controller;

import br.com.springboot.models.Noticias;
import br.com.springboot.models.Role;
import br.com.springboot.repository.NoticiasRepository;
import br.com.springboot.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/noticias"})
public class NoticiasController {
    @Autowired
    private NoticiasRepository repository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("noticias", repository.findAll());
        return "/noticias/index";
    }

    @GetMapping("/principal")
    public String principal(Model model) {
        model.addAttribute("noticias", repository.findAll());
        return "/noticias/principal";
    }

    @GetMapping("/add")
    public String add(Noticias noticias, Model model) {
        noticias = new Noticias();
        model.addAttribute("noticias", noticias);
        return "/noticias/add";
    }

    @PostMapping("/create")
    public String create(@Validated Noticias noticias, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/noticias/add";
        }

        repository.save(noticias);
        return "redirect:/noticias/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        Noticias noticias = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));

        model.addAttribute("noticias", noticias);
        return "/noticias/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @Validated Noticias noticias,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            noticias.setId(id);
            return "/noticias/update";
        }

        repository.save(noticias);
        return "redirect:/noticias/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        Noticias noticias = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));
        repository.delete(noticias);
        return "redirect:/noticias/";
    }
}
