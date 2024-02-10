package pe.idat.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import pe.idat.backend.model.DetalleVenta;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer>  {
	
}
