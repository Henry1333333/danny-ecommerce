package pe.idat.backend.model.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductoResponse {
	private Integer idproducto;
	private Integer codigo;
	private String nombre;
	private String descripcion;
	private Integer precio;
	private Integer cantidad;
	private String imagen;
	private Boolean estado;
}
