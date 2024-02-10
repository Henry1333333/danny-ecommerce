package pe.idat.backend.model.response;

import java.util.List;

import lombok.Data;
import pe.idat.backend.model.DetalleVenta;

@Data
public class NewDetalleVentaResponse {
	public class DetalleVentaResponse {
	    private String message;
	    private List<DetalleVenta> detallesRegistrados;

	    // Constructor y getters

	    // Otros métodos, si es necesario
	}
}
