package pe.idat.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.idat.backend.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	public Usuario findByUsername(String username);
}
