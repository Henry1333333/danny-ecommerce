package pe.idat.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.idat.backend.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Integer>  {
	

}
