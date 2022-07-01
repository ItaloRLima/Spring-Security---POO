package br.com.springboot.models;

import com.sun.istack.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "roles")
public class Role implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @NotNull
    @Id
    private Long id;
    @NotNull
    private String nomeRole;

    /*@ManyToMany //varias funçõea para varios usuarios
    private List<Usuarios> usuariosList;*/




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeRole() {
        return nomeRole;
    }

    public void setNomeRole(String nomeRole) {
        this.nomeRole = nomeRole;
    }
}
