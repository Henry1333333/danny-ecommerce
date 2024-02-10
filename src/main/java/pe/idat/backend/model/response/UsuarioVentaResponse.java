package pe.idat.backend.model.response;

import java.util.Calendar;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UsuarioVentaResponse {
	private Calendar fechaVenta;
	private Integer precioTotal;
	private String producto;
	private String imagen;
	private Integer precioUnitario;
	private Integer cantidad;

}
