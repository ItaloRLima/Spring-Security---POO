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

  @Autowired
  private UsuariosRepository ur; //cria novas instâncias

  @GetMapping("/")
  public String home() {
    return "home";
  }

  @GetMapping("/lista-usuarios")
  public String listarUsuarios() {
    return "lista-usuarios";
  }

  /*@GetMapping("/dados-acesso")
  public String relatorioAcessos() {
    return "dados-acesso";
  }*/
  
  @GetMapping("/login")
  public String login() {
      return "login";
  }

  @RequestMapping(value = "/cadastrarUsuarios",method = RequestMethod.GET)
  public String index(){return "cadastro/cadastrarUsuarios";}

  @RequestMapping(value = "/cadastrarUsuarios",method = RequestMethod.POST)
  public String index(Usuarios usuarios){
    ur.save(usuarios);
    return "redirect:/usuarios";}

  @RequestMapping("/usuarios")//mostrar os dados na páginas /usuarios
  public ModelAndView listaUsuarios(){
    ModelAndView mv = new ModelAndView("lista-usuarios");
    Iterable<Usuarios> usuarios = ur.findAll();
    mv.addObject("usuarios",usuarios);
    return mv;
  }

}