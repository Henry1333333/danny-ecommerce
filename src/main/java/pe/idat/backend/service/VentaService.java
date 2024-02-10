package pe.idat.backend.service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.idat.backend.model.Venta;
import pe.idat.backend.model.Usuario;
import pe.idat.backend.model.mapper.VentaMapper;
import pe.idat.backend.model.request.VentaRequest;
import pe.idat.backend.model.response.VentaListResponse;
import pe.idat.backend.model.response.VentaResponse;
import pe.idat.backend.repository.DetalleVentaRepository;
import pe.idat.backend.repository.UsuarioRepository;
import pe.idat.backend.repository.VentaRepository;

@Service
public class VentaService {
	@Autowired
	private VentaRepository repository;
	
	@Autowired
	private DetalleVentaRepository detalleVentaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	public List<VentaListResponse> listar(){
		return 
				detalleVentaRepository.findAll()
				.stream()
				.filter(l -> l.getEstado()==true)
				.map(VentaMapper::ventaMap)
				.collect(Collectors.toList());
	}
	
    public List<VentaListResponse> listarPorFecha(Calendar fechaInicio, Calendar fechaFin) {
        return detalleVentaRepository.findAll()
                .stream()
                .filter(detalleVenta -> detalleVenta.getEstado() == true
                        && detalleVenta.getIdventa() != null
                        && detalleVenta.getIdventa().getFechaVenta() != null
                        && detalleVenta.getIdventa().getFechaVenta().after(fechaInicio)
                        && detalleVenta.getIdventa().getFechaVenta().before(fechaFin))
                .map(VentaMapper::ventaMap)
                .collect(Collectors.toList());
    }
	
	public Optional<Venta> encontrarPorId(Integer id){
		return repository.findById(id);
	}
	
	public Venta registrar(VentaRequest req) {		
	    Optional<Usuario> usuarioEncontrado = Optional.empty();
	    Venta ventaRegistrada = null; // Inicializamos la variable para retornar

	    try {	
	        usuarioEncontrado = usuarioRepo.findById(req.getIdusuario());
	        
	        if(usuarioEncontrado.isPresent()) {
	            // Guardar la venta y asignarla a la variable de retorno
	            ventaRegistrada = repository.save(buildFromDto(req));
	        } else {
	            // Manejar el caso cuando el usuario no se encuentra
	            System.out.println("Usuario no encontrado con ID: " + req.getIdusuario());
	        }
	    } catch (Exception e) {
	        System.out.println("Hubo un error: " + e.getMessage());
	    }		
	    
	    return ventaRegistrada;		
	}
	
	public VentaResponse actualizar(VentaRequest req) {
		Optional <Usuario> usuarioEncontrado = Optional.empty();
		try {
			usuarioEncontrado = usuarioRepo.findById((req.getIdusuario()));
			System.out.println("Encontrado: " + usuarioEncontrado.get().getNombre());
			if(usuarioEncontrado.isPresent()) {
				repository.save(buildFromDto(req));
			}
		
		} catch (Exception e) {
			System.out.println("hubo un error: " + e.getMessage());
		}
		
		return VentaResponse(req.getIdventa());		
	}
	
	public Venta desactivar(Integer id) {
		Optional<Venta> desactivado = Optional.empty();
		try {
			desactivado = repository.findById(id);
			if(desactivado.isPresent()) {
				desactivado.get().setEstado(false);
				repository.save(desactivado.get());
			}
		} catch (Exception e) {
			System.out.println("Hubo un error: " + e.getMessage());
		}
		return desactivado.get();
	}
	
	
	private VentaResponse VentaResponse(Integer idventa) {
		// TODO Auto-generated method stub
		return null;
	}

	public Venta buildFromDto(VentaRequest request) {
		Venta ventaR = Venta.builder()
				.idventa(request.getIdventa())
				.idusuario(Usuario.builder().id(request.getIdusuario()).build())
				.tipoComprobate(request.getTipoComprobate())
				.tipoEntrega(request.getTipoEntrega())
				.fechaVenta(request.getFecha())
				.precioTotal(request.getPrecioTotal())
				.estado(request.getEstado())
				.build();
		return ventaR;
	}
	

}
