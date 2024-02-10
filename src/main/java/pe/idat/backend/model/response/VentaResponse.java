package pe.idat.backend.model.response;

import java.util.Calendar;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VentaResponse {
	private Integer idventa;
	private Integer idusuario;
	private String tipoComprobate;
	private Calendar fecha;
	private Integer precioTotal;
	private Boolean estado;
}
