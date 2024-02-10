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

import pe.idat.backend.model.Categoria;
import pe.idat.backend.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaService service;
	
	@GetMapping("/listar")
	@CrossOrigin(origins = "http://localhost:4200/")
	public List<Categoria> listar(){
		return service.listar();
	}
	
	@GetMapping("/encontrar/{id}")
	@CrossOrigin(origins = "http://localhost:4200/")
	@ResponseBody
	public ResponseEntity<Categoria> encontrarPorId(@PathVariable Integer id) {
		Optional<Categoria> encontrado = service.encontrarPorId(id);
		
		if (encontrado.isPresent()) {
			return new ResponseEntity<Categoria>(encontrado.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<Categoria>(new Categoria(),HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/registrar")
	@CrossOrigin(origins = "http://localhost:4200/")
	@ResponseBody
	public ResponseEntity <Categoria> registrar(@RequestBody Categoria cli){
		Categoria registrado = service.registrar(cli);
		
		return new ResponseEntity <Categoria>(registrado,
				HttpStatus.CREATED);
	}
	
	@PutMapping("/actualizar")
	@CrossOrigin(origins = "http://localhost:4200/")
	@ResponseBody
	public ResponseEntity <Categoria> actualizar(@RequestBody Categoria cli){
		Categoria registrado = service.actualizar(cli);
		
		return new ResponseEntity <Categoria>(registrado,
				HttpStatus.CREATED);
	}
	
	@DeleteMapping("/desactivar/{id}")
	@CrossOrigin(origins = "http://localhost:4200/")
	@ResponseBody
	public ResponseEntity<Categoria>  desactivar(@PathVariable Integer id) {
		Categoria desactivado = service.desactivar(id);
		
		if(desactivado != null) {
			return new ResponseEntity <Categoria>(desactivado,
					HttpStatus.OK);
		}
		return new ResponseEntity<Categoria>(desactivado, HttpStatus.NOT_FOUND);
		
	}
}
