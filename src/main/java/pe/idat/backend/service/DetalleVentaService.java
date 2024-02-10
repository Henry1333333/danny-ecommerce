package pe.idat.backend.service;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.idat.backend.model.DetalleVenta;
import pe.idat.backend.model.Producto;
import pe.idat.backend.model.Venta;
import pe.idat.backend.model.mapper.DetalleVentaMapper;
import pe.idat.backend.model.request.DetalleVentaRequest;
import pe.idat.backend.model.response.DVListResponse;
import pe.idat.backend.model.response.DetalleVentaResponse;
import pe.idat.backend.repository.DetalleVentaRepository;
import pe.idat.backend.repository.ProductoRepository;
import pe.idat.backend.repository.VentaRepository;

@Service
public class DetalleVentaService {

	@Autowired
	private DetalleVentaRepository repository;
	
	@Autowired
	private ProductoRepository productoRepo;
	
	@Autowired
	private VentaRepository ventaRepo;
	
	public List<DVListResponse> listar(){
		return 
				repository.findAll()
				.stream()
				.map(DetalleVentaMapper::detalleVentaMap)
				.collect(Collectors.toList());
	}
	
	public Optional<DetalleVenta> encontrarPorId(Integer id){
		return repository.findById(id);
	}
    public void registrarDetalleVenta(DetalleVentaRequest detalle) {
        DetalleVenta detalleVenta = buildFromDto(detalle);
        repository.save(detalleVenta);
    }
    public void registrarMultiplesDetallesVenta(List<DetalleVentaRequest> detallesVenta) {
        for (DetalleVentaRequest detalle : detallesVenta) {
            Integer idVenta = detalle.getIdventa();
            Integer idProducto = detalle.getIdproducto();
            Optional<Venta> ventaEncontrado = ventaRepo.findById(idVenta);
            Optional<Producto> productoEncontrado = productoRepo.findById(idProducto); 
            if(ventaEncontrado.isPresent() && productoEncontrado.isPresent()) {
            	System.err.println(ventaEncontrado.get().getIdventa());
            	System.err.println(productoEncontrado.get().getIdproducto());
            }
            registrarDetalleVenta(detalle);
        }
    }
	
	public DetalleVentaResponse registrar(DetalleVentaRequest req) {
		
		Optional <Venta> ventaEncontrado = Optional.empty();
		Optional <Producto> productoEncontrado = Optional.empty();
		try {
		
			ventaEncontrado = ventaRepo.findById((req.getIdventa()));
			productoEncontrado = productoRepo.findById((req.getIdproducto()));
			System.out.println( "id: "+ventaEncontrado.get().getIdventa());
			System.out.println("Encontrado: " + productoEncontrado.get().getNombre() + "idusuario: "+productoEncontrado.get().getIdproducto());
			if(ventaEncontrado.isPresent() && productoEncontrado.isPresent()) {
				repository.save(buildFromDto(req));
			}
		
		} catch (Exception e) {
			System.out.println("hubo un error: " + e.getMessage());
		}
		
		return DetalleVentaResponse(req.getIddetventa());		
	}
	
	public DetalleVentaResponse actualizar(DetalleVentaRequest req) {
		
		Optional <Venta> ventaEncontrado = Optional.empty();
		Optional <Producto> productoEncontrado = Optional.empty();
		try {
		
			ventaEncontrado = ventaRepo.findById((req.getIdventa()));
			productoEncontrado = productoRepo.findById((req.getIdproducto()));
			System.out.println( "id: "+ventaEncontrado.get().getIdventa());
			System.out.println("Encontrado: " + productoEncontrado.get().getNombre() + "idusuario: "+productoEncontrado.get().getIdproducto());
			if(ventaEncontrado.isPresent() && productoEncontrado.isPresent()) {
				repository.save(buildFromDto(req));
			}
		
		} catch (Exception e) {
			System.out.println("hubo un error: " + e.getMessage());
		}
		
		return DetalleVentaResponse(req.getIddetventa());		
	}
	

	
	private DetalleVentaResponse DetalleVentaResponse(Integer iddetventa) {
		// TODO Auto-generated method stub
		return null;
	}

	public DetalleVenta buildFromDto(DetalleVentaRequest request) {
		DetalleVenta productoR = DetalleVenta.builder()
				.iddetventa(request.getIddetventa())
				.idventa(Venta.builder().idventa(request.getIdventa()).build())
				.idproducto(Producto.builder().idproducto(request.getIdproducto()).build())
				.cantidad(request.getCantidad())
				.precio(request.getPrecio())
				.estado(request.getEstado())
				.build();
		return productoR;
	}
}
