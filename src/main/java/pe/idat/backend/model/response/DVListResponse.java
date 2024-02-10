package pe.idat.backend.model.response;

import lombok.Builder;
import lombok.Data;
import pe.idat.backend.model.Venta;

@Data
@Builder
public class DVListResponse {
    private Integer iddetventa;
    private Integer idventa;
	private String nombreproducto;
	private Integer cantidad;
    private Integer precio;
    private Boolean estado;
}
