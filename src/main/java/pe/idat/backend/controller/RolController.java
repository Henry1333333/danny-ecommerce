package pe.idat.backend.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.idat.backend.model.Rol;
import pe.idat.backend.model.response.RolResponse;
import pe.idat.backend.service.RolService;

@RestController
@RequestMapping("/rol")
public class RolController {
	@Autowired
	private RolService service;
	
	@GetMapping("/listar")
	@CrossOrigin(origins = "http://localhost:4200/")
	public List<RolResponse> listar(){
		return service.listar();
	}
	
	@GetMapping("/encontrar/{id}")
	@CrossOrigin(origins = "http://localhost:4200/")
	@ResponseBody
	public ResponseEntity<Rol> encontrarPorId(@PathVariable Integer id) {
		Optional<Rol> encontrado = service.encontrarPorId(id);
		
		if (encontrado.isPresent()) {
			return new ResponseEntity<Rol>(encontrado.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<Rol>(new Rol(),HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/registrar")
	@CrossOrigin(origins = "http://localhost:4200/")
	@ResponseBody
	public ResponseEntity <Rol> registrar(@RequestBody Rol cli){
		Rol registrado = service.registrar(cli);
		
		return new ResponseEntity <Rol>(registrado,
				HttpStatus.CREATED);
	}
	
	@PutMapping("/actualizar")
	@CrossOrigin(origins = "http://localhost:4200/")
	@ResponseBody
	public ResponseEntity <Rol> actualizar(@RequestBody Rol cli){
		Rol registrado = service.actualizar(cli);
		
		return new ResponseEntity <Rol>(registrado,
				HttpStatus.CREATED);
	}
	
	@DeleteMapping("/desactivar/{id}")
	@CrossOrigin(origins = "http://localhost:4200/")
	@ResponseBody
	public ResponseEntity<Rol>  desactivar(@PathVariable Integer id) {
		Rol desactivado = service.desactivar(id);
		
		if(desactivado != null) {
			return new ResponseEntity <Rol>(desactivado,
					HttpStatus.OK);
		}
		return new ResponseEntity<Rol>(desactivado, HttpStatus.NOT_FOUND);
		
	}
}
