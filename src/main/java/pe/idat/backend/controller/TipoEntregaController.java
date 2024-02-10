package pe.idat.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.idat.backend.model.Envio;
import pe.idat.backend.model.Venta;
import pe.idat.backend.model.request.EnvioRequest;
import pe.idat.backend.model.request.RecogerEnTiendaRequest;
import pe.idat.backend.service.TipoEntregaService;

@RestController
@RequestMapping("/tipoEntrega")
public class TipoEntregaController {
	@Autowired
	private TipoEntregaService service;
	
	@PostMapping("/registrarEnvio")
	@CrossOrigin(origins = "http://localhost:4200/")
	@ResponseBody
	public ResponseEntity<Envio> registrarEnvio(@RequestBody EnvioRequest envio) {
	    Envio registrado = service.registrarEnvio(envio);



		return new ResponseEntity <Envio>(registrado,
				HttpStatus.CREATED);
	}
	
	@PostMapping("/registrarRecogerTienda")
	@CrossOrigin(origins = "http://localhost:4200/")
	@ResponseBody
	public ResponseEntity<Map<String, String>> registrarRecogerEnTienda(@RequestBody RecogerEnTiendaRequest req) {
	    service.registrarRecogerEnTienda(req);

	    Map<String, String> response = new HashMap<>();
	    response.put("message", "Envio registrado con éxito.");

	    return ResponseEntity.ok(response);
	}

}
