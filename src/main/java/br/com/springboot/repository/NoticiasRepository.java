package br.com.springboot.repository;

import br.com.springboot.models.Noticias;
import org.springframework.data.repository.CrudRepository;

public interface NoticiasRepository extends CrudRepository<Noticias,Long> {
}
