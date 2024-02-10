package pe.idat.backend.model;

import javax.persistence.*;


import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rolId;
    private String rolNombre;
    private Boolean estado;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "rol")
    private Set<UsuarioRol> usuarioRoles = new HashSet<>();

    public Rol(){

    }

    public Rol(int rolId, String rolNombre, Boolean estado) {
		super();
		this.rolId = rolId;
		this.rolNombre = rolNombre;
		this.estado = estado;
	}

	public Rol(int rolId, String rolNombre) {
        this.rolId = rolId;
        this.rolNombre = rolNombre;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    public Set<UsuarioRol> getUsuarioRoles() {
        return usuarioRoles;
    }

    public void setUsuarioRoles(Set<UsuarioRol> usuarioRoles) {
        this.usuarioRoles = usuarioRoles;
    }

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
}
