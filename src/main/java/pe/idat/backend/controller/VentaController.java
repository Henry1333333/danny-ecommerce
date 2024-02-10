package pe.idat.backend.controller;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pe.idat.backend.model.Venta;
import pe.idat.backend.model.request.VentaRequest;
import pe.idat.backend.model.response.VentaListResponse;
import pe.idat.backend.service.VentaService;

@RestController
@RequestMapping("/venta")
public class VentaController {
	
	@Autowired
	private VentaService service;
	
	@GetMapping("/listar")
	@CrossOrigin(origins ="http://localhost:4200/")
	public List<VentaListResponse> listar(){
		return service.listar();
	}
	
    @GetMapping("/listarPorFecha")
    @CrossOrigin(origins ="http://localhost:4200/")
    public ResponseEntity<List<VentaListResponse>> listarVentas(
    		@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Calendar fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Calendar fechaFin) {

        List<VentaListResponse> ventas = service.listarPorFecha(fechaInicio, fechaFin);

        return ResponseEntity.ok(ventas);
    }
	
	@GetMapping("/encontrar/{id}")
	@CrossOrigin(origins = "http://localhost:4200/")
	@ResponseBody
	public ResponseEntity<Venta> encontrarPorId(@PathVariable Integer id) {
		Optional<Venta> encontrado = service.encontrarPorId(id);
		
		if (encontrado.isPresent()) {
			return new ResponseEntity<Venta>(encontrado.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<Venta>(new Venta(),HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/registrar")
	@CrossOrigin(origins = "http://localhost:4200/")
	@ResponseBody
	public ResponseEntity<Venta> registrar(@RequestBody VentaRequest mascoPet) {
		Venta registrado = service.registrar(mascoPet);	
		return new ResponseEntity <Venta>(registrado,
				HttpStatus.CREATED);
	}
	@PutMapping("/actualizar")
	@CrossOrigin(origins = "http://localhost:4200/")
	@ResponseBody
	public ResponseEntity<Venta> actualizar(@RequestBody VentaRequest mascoPet) {
		Venta registrado = service.registrar(mascoPet);	
		return new ResponseEntity <Venta>(registrado,
				HttpStatus.CREATED);
	}
	
	@DeleteMapping("/desactivar/{id}")
	@CrossOrigin(origins = "http://localhost:4200/")
	@ResponseBody
	public ResponseEntity<Venta>  desactivar(@PathVariable Integer id) {
		Venta desactivado = service.desactivar(id);
		
		if(desactivado != null) {
			return new ResponseEntity <Venta>(desactivado,
					HttpStatus.OK);
		}
		return new ResponseEntity<Venta>(desactivado, HttpStatus.NOT_FOUND);
		
	}
}
