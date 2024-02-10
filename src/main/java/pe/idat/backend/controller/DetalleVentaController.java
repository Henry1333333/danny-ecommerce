package pe.idat.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pe.idat.backend.model.DetalleVenta;
import pe.idat.backend.model.request.DetalleVentaRequest;
import pe.idat.backend.model.response.DVListResponse;
import pe.idat.backend.model.response.DetalleVentaResponse;
import pe.idat.backend.service.DetalleVentaService;

@RestController
@RequestMapping("/detalleventa")
public class DetalleVentaController {
	@Autowired
	private DetalleVentaService service;
	
	
	@GetMapping("/listar")
	@CrossOrigin(origins ="http://localhost:4200/")
	public List<DVListResponse> listar(){
		return service.listar();
	}
	
	@GetMapping("/encontrar/{id}")
	@CrossOrigin(origins = "http://localhost:4200/")
	@ResponseBody
	public ResponseEntity<DetalleVenta> encontrarPorId(@PathVariable Integer id) {
		Optional<DetalleVenta> encontrado = service.encontrarPorId(id);
		
		if (encontrado.isPresent()) {
			return new ResponseEntity<DetalleVenta>(encontrado.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<DetalleVenta>(new DetalleVenta(),HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/registrar")
	@CrossOrigin(origins = "http://localhost:4200/")
	@ResponseBody
	public ResponseEntity<DetalleVentaResponse> registrar(@RequestBody DetalleVentaRequest mascoPet) {
		DetalleVentaResponse registrado = service.registrar(mascoPet);
				
			return new ResponseEntity <DetalleVentaResponse>(registrado,
					HttpStatus.CREATED);
	}
	
	@PostMapping("/registrar-multiples")
	@CrossOrigin(origins = "http://localhost:4200/")
	@ResponseBody
	public ResponseEntity<Map<String, String>> registrarMultiplesDetallesVenta(@RequestBody List<DetalleVentaRequest> detallesVenta) {
	    service.registrarMultiplesDetallesVenta(detallesVenta);

	    Map<String, String> response = new HashMap<>();
	    response.put("message", "Detalles de venta registrados con éxito.");

	    return ResponseEntity.ok(response);
	}
	
	@PutMapping("/actualizar")
	@CrossOrigin(origins = "http://localhost:4200/")
	@ResponseBody
	public ResponseEntity<DetalleVentaResponse> actualizar(@RequestBody DetalleVentaRequest mascoPet) {
		DetalleVentaResponse registrado = service.registrar(mascoPet);
				
			return new ResponseEntity <DetalleVentaResponse>(registrado,
					HttpStatus.CREATED);
	}
}
