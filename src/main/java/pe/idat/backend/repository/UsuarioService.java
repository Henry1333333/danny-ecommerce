package pe.idat.backend.repository;



import java.util.List;
import java.util.Set;

import pe.idat.backend.model.Usuario;
import pe.idat.backend.model.UsuarioRol;
import pe.idat.backend.model.response.UsuarioVentaResponse;

public interface UsuarioService {

    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;

    public Usuario obtenerUsuario(String username);

	public List<UsuarioVentaResponse> listarPorId(Integer id);

}
