package pe.idat.backend.service;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.idat.backend.model.Rol;
import pe.idat.backend.model.mapper.RolMapper;
import pe.idat.backend.model.request.RolRequest;
import pe.idat.backend.model.response.RolResponse;
import pe.idat.backend.repository.RolRepository;

@Service
public class RolService {
	@Autowired
	private RolRepository repository;
	
	
	public List<RolResponse> listar(){
		return 
				repository.findAll()
				.stream()
				.filter(l -> l.getEstado()==true)
				.map(RolMapper::rolMap)
				.collect(Collectors.toList());
	}

	public Optional<Rol> encontrarPorId(Integer id){
		return repository.findById(id);
	}
	
	public Rol registrar(Rol al) {
		Rol registro = null;
		try {
			registro = repository.save(al);
			System.out.println("OK");
			
		} catch (Exception e) {
			System.out.println("hubo un error: " + e.getMessage());
		}
		
		return registro;		
	}
	
	public Rol actualizar(Rol al) {
		Rol registro = null;
		try {
			registro = repository.save(al);
			System.out.println("OK");
			
		} catch (Exception e) {
			System.out.println("hubo un error: " + e.getMessage());
		}
		
		return registro;		
	}
	
	public Rol desactivar(Integer id) {
		Optional<Rol> desactivado = Optional.empty();
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
