package pe.idat.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.idat.backend.model.Categoria;
import pe.idat.backend.model.Producto;
import pe.idat.backend.model.mapper.ProductoMapper;
import pe.idat.backend.model.request.ProductoRequest;
import pe.idat.backend.model.response.ProductoListResponse;
import pe.idat.backend.model.response.ProductoResponse;
import pe.idat.backend.repository.CategoriaRepository;
import pe.idat.backend.repository.ProductoRepository;

@Service
public class ProductoService {
	
	@Autowired
	private ProductoRepository repository;
	
	@Autowired
	private CategoriaRepository catRepository;
	
	//listar
	public List<ProductoListResponse> listar(){
		return 
				repository.findAll()
				.stream()
				.filter(l -> l.getEstado()==true)
				.map(ProductoMapper::productoMap)
				.collect(Collectors.toList());
	}
	
	//buscar por id
	public Optional<Producto> encontrarPorId(Integer id){
		return repository.findById(id);
	}
	
	//registrar
	public ProductoResponse registrar(ProductoRequest req) {
		
		Optional <Categoria> categoriaEncontrado = Optional.empty();
		try {
		
			categoriaEncontrado = catRepository.findById((req.getIdcategoria()));
			System.out.println("Encontrado: " + categoriaEncontrado.get().getNombre() +categoriaEncontrado.get().getIdcategoria());		
			if(categoriaEncontrado.isPresent()) {
				repository.save(buildFromDto(req));
			}
		
		} catch (Exception e) {
			System.out.println("hubo un error: " + e.getMessage());
		}
		
		return ProductoResponse(req.getNombre());		
	}
	
	//actualizar
	public ProductoResponse actualizar(ProductoRequest req) {
		
		Optional <Categoria> categoriaEncontrado = Optional.empty();
		try {
		
			categoriaEncontrado = catRepository.findById((req.getIdcategoria()));
			System.out.println("Encontrado: " + categoriaEncontrado.get().getNombre() +categoriaEncontrado.get().getIdcategoria());		
			if(categoriaEncontrado.isPresent()) {
				repository.save(buildFromDto(req));
			}
		
		} catch (Exception e) {
			System.out.println("hubo un error: " + e.getMessage());
		}
		
		return ProductoResponse(req.getNombre());		
	}
	
	//desactivar - eliminar
	public Producto desactivar(Integer id) {
		Optional<Producto> desactivado = Optional.empty();
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
	
	
	private ProductoResponse ProductoResponse(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	//mapeado de un nuevo objeto
	public Producto buildFromDto(ProductoRequest request) {
		Producto productoR = Producto.builder()
				.idproducto(request.getIdproducto())
				.idcategoria(Categoria.builder().idcategoria(request.getIdcategoria()).build())
				.codigo(request.getCodigo())
				.nombre(request.getNombre())
				.descripcion(request.getDescripcion())
				.precio(request.getPrecio())
				.cantidad(request.getCantidad())
				.imagen(request.getImagen())
				.estado(request.getEstado())
				.build();
		return productoR;
	}
}
