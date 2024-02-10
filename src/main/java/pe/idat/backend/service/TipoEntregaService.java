package pe.idat.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.idat.backend.model.Categoria;
import pe.idat.backend.model.Envio;
import pe.idat.backend.model.Producto;
import pe.idat.backend.model.RecogerEnTienda;
import pe.idat.backend.model.Venta;
import pe.idat.backend.model.request.EnvioRequest;
import pe.idat.backend.model.request.ProductoRequest;
import pe.idat.backend.model.request.RecogerEnTiendaRequest;
import pe.idat.backend.model.response.ProductoResponse;
import pe.idat.backend.repository.EnvioRepository;
import pe.idat.backend.repository.RecogerEnTiendaRepository;
import pe.idat.backend.repository.VentaRepository;

@Service
public class TipoEntregaService {
	
	@Autowired
	private EnvioRepository envioRepository;
	
	@Autowired
	private RecogerEnTiendaRepository recogerRepository;
	
	@Autowired
	private VentaRepository ventaRepository;
	
	public Envio registrarEnvio(EnvioRequest req) {
		Optional <Venta> ventaEncontrado = Optional.empty();
		Envio envioRegistrado = null;
		try {
		
			ventaEncontrado = ventaRepository.findById((req.getIdventa()));
			System.out.println("Encontrado: " + ventaEncontrado.get().getIdventa());		
			if(ventaEncontrado.isPresent()) {
				envioRegistrado = envioRepository.save(buildFromDto(req));
			}
		
		} catch (Exception e) {
			System.out.println("hubo un error: " + e.getMessage());
		}
		
		return envioRegistrado;		
	}
	
	public RecogerEnTienda registrarRecogerEnTienda(RecogerEnTiendaRequest req) {
		Optional <Venta> ventaEncontrado = Optional.empty();
		RecogerEnTienda recogerEnTiendaRegistrado = null;
		try {
		
			ventaEncontrado = ventaRepository.findById((req.getIdventa()));
			System.out.println("Encontrado: " + ventaEncontrado.get().getIdventa());		
			if(ventaEncontrado.isPresent()) {
				recogerEnTiendaRegistrado = recogerRepository.save(buildFromDtoR(req));
			}
		
		} catch (Exception e) {
			System.out.println("hubo un error: " + e.getMessage());
		}
		
		return recogerEnTiendaRegistrado;		
	}
	
	public Envio buildFromDto(EnvioRequest request) {
		Envio envioR = Envio.builder()
				.idenvio(request.getIdenvio())
				.idventa(Venta.builder().idventa(request.getIdventa()).build())
				.region(request.getRegion())
				.provincia(request.getProvincia())
				.distrito(request.getDistrito())
				.direccionenvio(request.getDireccionenvio())
				.build();
		return envioR;
	}
	
	public RecogerEnTienda buildFromDtoR(RecogerEnTiendaRequest request) {
		RecogerEnTienda envioR = RecogerEnTienda.builder()
				.id(request.getId())
				.idventa(Venta.builder().idventa(request.getIdventa()).build())
				.direccion(request.getDireccion())
				.build();
		return envioR;
	}
}
