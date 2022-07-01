package br.com.springboot.repository;

import br.com.springboot.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,Long> {

}
