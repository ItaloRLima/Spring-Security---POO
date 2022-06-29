package br.com.springboot.security;

import br.com.springboot.models.Usuarios;
import br.com.springboot.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class ImplementesUserDetailsService implements UserDetailsService {
    @Autowired
    private UsuariosRepository ur;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        Usuarios usuarios = ur.findByLogin(login);
        if(usuarios == null){
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return new User(usuarios.getUsername(), usuarios.getPassword(), true,true,true,true,usuarios.getAuthorities());
    }
}
