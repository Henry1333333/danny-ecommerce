package pe.idat.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.idat.backend.model.Categoria;



public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
