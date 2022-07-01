package br.com.springboot.controller;

import br.com.springboot.models.Role;
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
@RequestMapping({"/roles"})
public class RoleController {
    @Autowired
    private RoleRepository repository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("roles", repository.findAll());
        return "/roles/index";
    }

    @GetMapping("/add")
    public String add(Role role, Model model) {
        role = new Role();
        model.addAttribute("role", role);
        return "/roles/add";
    }

    @PostMapping("/create")
    public String create(@Validated Role role, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/roles/add";
        }

        repository.save(role);
        return "redirect:/roles/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        Role role = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));

        model.addAttribute("role", role);
        return "/roles/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @Validated Role role,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            role.setId(id);
            return "/roles/update";
        }

        repository.save(role);
        return "redirect:/roles/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        Role role = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));
        repository.delete(role);
        return "redirect:/roles/";
    }
}
