package br.com.springboot.repository;

import br.com.springboot.models.Usuarios;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UsuariosRepository extends CrudRepository<Usuarios,Long> {

    @Query("SELECT u FROM Usuarios u WHERE u.login = :login")
    public Usuarios getUserByUsername(String login);
}
