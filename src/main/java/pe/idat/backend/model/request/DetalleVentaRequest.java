package pe.idat.backend.model.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DetalleVentaRequest {
	private Integer iddetventa;
	private Integer idventa;
	private Integer idproducto;
	private Integer cantidad;
    private Integer precio;
    private Boolean estado;
}
