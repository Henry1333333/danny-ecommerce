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

import pe.idat.backend.model.Producto;
import pe.idat.backend.model.request.ProductoRequest;
import pe.idat.backend.model.response.ProductoListResponse;
import pe.idat.backend.model.response.ProductoResponse;
import pe.idat.backend.service.ProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoController {
	
	@Autowired
	private ProductoService service;
	
	@GetMapping("/listar")
	@CrossOrigin(origins ="http://localhost:4200/")
	public List<ProductoListResponse> listar(){
		return service.listar();
	}
	
	@GetMapping("/encontrar/{id}")
	@CrossOrigin(origins = "http://localhost:4200/")
	@ResponseBody
	public ResponseEntity<Producto> encontrarPorId(@PathVariable Integer id) {
		Optional<Producto> encontrado = service.encontrarPorId(id);
		
		if (encontrado.isPresent()) {
			return new ResponseEntity<Producto>(encontrado.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<Producto>(new Producto(),HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/crear")
	@CrossOrigin(origins = "http://localhost:4200/")
	@ResponseBody
	public ResponseEntity<ProductoResponse> registrar(@RequestBody ProductoRequest mascoPet) {
		ProductoResponse registrado = service.registrar(mascoPet);
				
			return new ResponseEntity <ProductoResponse>(registrado,
					HttpStatus.CREATED);
	}
	
	@PutMapping("/actualizar")
	@CrossOrigin(origins = "http://localhost:4200/")
	@ResponseBody
	public ResponseEntity<ProductoResponse> actualizar(@RequestBody ProductoRequest mascoPet) {
		ProductoResponse registrado = service.actualizar(mascoPet);
				
			return new ResponseEntity <ProductoResponse>(registrado,
					HttpStatus.CREATED);
	}
	
	@DeleteMapping("/desactivar/{id}")
	@CrossOrigin(origins = "http://localhost:4200/")
	@ResponseBody
	public ResponseEntity<Producto>  desactivar(@PathVariable Integer id) {
		Producto desactivado = service.desactivar(id);
		
		if(desactivado != null) {
			return new ResponseEntity <Producto>(desactivado,
					HttpStatus.OK);
		}
		return new ResponseEntity<Producto>(desactivado, HttpStatus.NOT_FOUND);
		
	}
}
