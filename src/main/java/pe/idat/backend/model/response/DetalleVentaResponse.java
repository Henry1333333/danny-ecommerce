package pe.idat.backend.model.response;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DetalleVentaResponse {
	private Integer iddetventa;
	private Integer cantidad;
    private Integer precio;
    private Integer descuento;

}
