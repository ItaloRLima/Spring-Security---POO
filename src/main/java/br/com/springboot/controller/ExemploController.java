package br.com.springboot.controller;

import br.com.springboot.models.Usuarios;
import br.com.springboot.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExemploController {

  @GetMapping("/")
  public String main() {
    return "home";
  }

  @GetMapping("/login")
  public String viewLoginPage() {
    // custom logic before showing login page...
    return "login";
  }

}