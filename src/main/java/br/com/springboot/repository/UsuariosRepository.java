package br.com.springboot.repository;

import br.com.springboot.models.Usuarios;
import org.springframework.data.repository.CrudRepository;

public interface UsuariosRepository extends CrudRepository<Usuarios,String> {

    Usuarios findByLogin(String login);
}
