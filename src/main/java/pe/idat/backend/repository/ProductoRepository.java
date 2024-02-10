package pe.idat.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.idat.backend.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>  {

}
