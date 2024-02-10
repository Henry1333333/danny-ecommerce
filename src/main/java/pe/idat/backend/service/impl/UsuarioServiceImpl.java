package pe.idat.backend.service.impl;


import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pe.idat.backend.model.DetalleVenta;
import pe.idat.backend.model.Producto;
import pe.idat.backend.model.Usuario;
import pe.idat.backend.model.UsuarioRol;
import pe.idat.backend.model.mapper.ProductoMapper;
import pe.idat.backend.model.mapper.UsuarioVentaMapper;
import pe.idat.backend.model.response.UsuarioVentaResponse;
import pe.idat.backend.repository.DetalleVentaRepository;
import pe.idat.backend.repository.RolRepository;
import pe.idat.backend.repository.UsuarioRepository;
import pe.idat.backend.repository.UsuarioService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private RolRepository rolRepository;


	public List<Usuario> listar(){
		return 
				usuarioRepository.findAll()
				.stream()
				.filter(l -> l.getEstado()==true)
				.collect(Collectors.toList());
	}

	public List<UsuarioVentaResponse> listarPorId(Integer id) {
	    Optional<Usuario> encontrado = usuarioRepository.findById(id);

	    if (encontrado.isPresent()) {
	        List<DetalleVenta> detalleVentas = detalleVentaRepository.findAll()
	                .stream()
	                .filter(l -> l.getEstado() == true)
	                .filter(l -> l.getIdventa().getIdusuario().getId() == id)
	                .collect(Collectors.toList());
	        System.out.println("Numero de listado: " + detalleVentas.size());

	        return detalleVentas.stream()
	                .map(UsuarioVentaMapper::ventaMap)
	                .collect(Collectors.toList());
	    } else {
	        return Collections.emptyList();
	    }
	}

	@Override
	public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
        Usuario usuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());
        if(usuarioLocal != null){
            System.out.println("El usuario ya existe");
            throw new Exception("El usuario ya esta presente");
        }
        else{
        	
            for(UsuarioRol usuarioRol:usuarioRoles){
                rolRepository.save(usuarioRol.getRol());
            }
            String pwdClearText = usuario.getPassword();
    		String encoded =  new BCryptPasswordEncoder().encode(pwdClearText);
    		usuario.setPassword(encoded);
            usuario.getUsuarioRoles().addAll(usuarioRoles);
            usuarioLocal = usuarioRepository.save(usuario);
        }
        return usuarioLocal;
	}
	
	public Usuario actualizar(Usuario al) {
		Usuario registro = null;
		try {
			registro = usuarioRepository.save(al);
			
			System.out.println("OK");
			
		} catch (Exception e) {
			System.out.println("hubo un error: " + e.getMessage());
		}
		
		return registro;		
	}

	@Override
	public Usuario obtenerUsuario(String username) {
		return usuarioRepository.findByUsername(username);
	}
	
	public Optional<Usuario> encontrarPorId(Integer id){
		return usuarioRepository.findById(id);
	}
	
	public Usuario eliminar(Integer id) {
	Optional<Usuario>eliminado = Optional.empty();
		try {
			eliminado = usuarioRepository.findById(id);
			
			if(eliminado.isPresent()) {
				eliminado.get().setEstado(false);
				usuarioRepository.save(eliminado.get());
			}			
		} catch (Exception e) {
			System.out.println("hubo un error: " + e.getMessage());

		}		
		return eliminado.get();
	}

}