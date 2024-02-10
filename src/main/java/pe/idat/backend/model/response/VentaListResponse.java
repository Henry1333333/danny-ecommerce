package pe.idat.backend.model.response;

import java.util.Calendar;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class VentaListResponse {
	private Integer idventa;
	private String nombrecliente;
	private String apellidocliente;
	private String dnicliente;
	private String tipoComprobate;
	private String tipoEntrega;
	private Calendar fechaVenta;
	private Integer preciototal;
	private String producto;
	private Integer cantidad;
	private Integer precioUnitario;
}
