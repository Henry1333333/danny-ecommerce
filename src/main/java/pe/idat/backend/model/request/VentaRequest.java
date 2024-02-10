package pe.idat.backend.model.request;

import java.util.Calendar;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VentaRequest {
	private Integer idventa;
	private Integer idusuario;
	private String tipoComprobate;
	private String tipoEntrega;
	private Calendar fecha;
	private Integer precioTotal;
	private Boolean estado;

}
