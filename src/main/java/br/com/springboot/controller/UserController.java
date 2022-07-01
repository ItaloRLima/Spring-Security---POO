package br.com.springboot.controller;

import br.com.springboot.models.Role;
import br.com.springboot.models.Usuarios;
import br.com.springboot.repository.RoleRepository;
import br.com.springboot.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;

@Controller
@RequestMapping({"/usuarios"})
public class UserController {

    @Autowired
    private UsuariosRepository uRepository;

    @Autowired
    private RoleRepository rRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("usuarios", uRepository.findAll());
        return "/usuarios/index";
    }

    @GetMapping("/add")
    public String add(Usuarios user, Model model) {
        user = new Usuarios();
        model.addAttribute("usuarios", user);
        return "/usuarios/add";
    }

    @PostMapping("/create")
    public String create(@Validated Usuarios user, BindingResult result, Model model) throws Exception {
        if (result.hasErrors()) {
            return "/usuarios/add";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role r = rRepository.findById(Integer.toUnsignedLong(2)).get();
        HashSet hsr = new HashSet<Role>();
        hsr.add(r);
        user.setRoles(hsr);

        uRepository.save(user);

        return "redirect:/usuarios/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        Usuarios user = uRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));

        model.addAttribute("usuarios", user);
        return "/usuarios/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @Validated Usuarios user,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "/usuarios/update";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        uRepository.save(user);
        return "redirect:/usuarios/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        Usuarios user = uRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));
        uRepository.delete(user);
        return "redirect:/usuarios/";
    }
}
