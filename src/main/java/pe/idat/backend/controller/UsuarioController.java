package pe.idat.backend.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pe.idat.backend.model.Rol;
import pe.idat.backend.model.Usuario;
import pe.idat.backend.model.UsuarioRol;
import pe.idat.backend.model.response.ProductoListResponse;
import pe.idat.backend.model.response.UsuarioVentaResponse;
import pe.idat.backend.repository.UsuarioService;
import pe.idat.backend.service.impl.UsuarioServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private UsuarioServiceImpl service;

    @PostMapping("/agregar")
    @CrossOrigin(origins = "http://localhost:4200/")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception{
        Set<UsuarioRol> usuarioRoles = new HashSet<>();
        Rol rol = new Rol();
        rol.setRolId(1);
        rol.setRolNombre("admin");
        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);
        usuarioRoles.add(usuarioRol);
        return usuarioService.guardarUsuario(usuario,usuarioRoles);
    }
    
	@PutMapping("/actualizar")
	@CrossOrigin(origins = "http://localhost:4200/")
	@ResponseBody
	public ResponseEntity<Usuario> actualizar(@RequestBody Usuario newUser)
	{
		Usuario actualizado = service.actualizar(newUser);
		if(actualizado != null) {
            String pwdClearText = actualizado.getPassword();
    		String encoded =  new BCryptPasswordEncoder().encode(pwdClearText);
    		actualizado.setPassword(encoded);
			return new ResponseEntity <Usuario>(actualizado,
					HttpStatus.CREATED);
		}
		return new ResponseEntity<Usuario>(actualizado, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/listar")
	@CrossOrigin(origins = "http://localhost:4200/")
	public List<Usuario> listar(){
		return service.listar();
	}
    
	@GetMapping("/listarPorId/{id}")
	@CrossOrigin(origins ="http://localhost:4200/")
	  public List<UsuarioVentaResponse> listarVentasPorId(@PathVariable Integer id) {
        List<UsuarioVentaResponse> ventas = usuarioService.listarPorId(id);

        // Log the size of the list to help with debugging
        System.out.println("Number of UsuarioVentaResponse: " + ventas.size());

        return ventas;
    }
	
    @GetMapping("/{username}")
    public Usuario obtenerUsuario(@PathVariable("username") String username){
        return usuarioService.obtenerUsuario(username);
    }

    
	@DeleteMapping("/desactivar/{id}")
	@ResponseBody
	@CrossOrigin(origins = "http://localhost:4200/")
	public ResponseEntity<Usuario>  desactivarAlumno(@PathVariable Integer id) {
		Usuario desactivado = service.eliminar(id);
		
		if(desactivado != null) {
			return new ResponseEntity <Usuario>(desactivado,
					HttpStatus.OK);
		}
		return new ResponseEntity<Usuario>(desactivado, HttpStatus.NOT_FOUND);
		
	}

}
