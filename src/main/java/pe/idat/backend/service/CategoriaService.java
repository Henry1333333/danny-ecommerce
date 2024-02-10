package pe.idat.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.idat.backend.model.Categoria;
import pe.idat.backend.repository.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repository;
	
	
	public Optional<Categoria> encontrarPorId(Integer id){
		return repository.findById(id);
	}

	public List<Categoria> listar(){
		return 
				repository.findAll()
				.stream()
				.filter(l -> l.getEstado()==true)
				.collect(Collectors.toList());
	}
	
	public Categoria registrar(Categoria al) {
		Categoria registro = null;
		try {
			registro = repository.save(al);
			System.out.println("OK");
			
		} catch (Exception e) {
			System.out.println("hubo un error: " + e.getMessage());
		}
		
		return registro;		
	}
	
	public Categoria actualizar(Categoria al) {
		Categoria registro = null;
		try {
			registro = repository.save(al);
			System.out.println("OK");
			
		} catch (Exception e) {
			System.out.println("hubo un error: " + e.getMessage());
		}
		
		return registro;		
	}
	
	public Categoria desactivar(Integer id) {
		Optional<Categoria> desactivado = Optional.empty();
		try {
			desactivado = repository.findById(id);
			if(desactivado.isPresent()) {
				desactivado.get().setEstado(false);
				repository.save(desactivado.get());
			}
		} catch (Exception e) {
			System.out.println("Hubo un error: " + e.getMessage() );
		}
		return desactivado.get();
	}
}
