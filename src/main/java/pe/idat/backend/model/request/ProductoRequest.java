package pe.idat.backend.model.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductoRequest {
	private Integer idproducto;
	private Integer idcategoria;
	private Integer codigo;
	private String nombre;
	private String descripcion;
	private Integer precio;
	private Integer cantidad;
	private String imagen;
	private Boolean estado;

}
