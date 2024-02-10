package pe.idat.backend.model.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RecogerEnTiendaRequest {
	private Integer id;
	private Integer idventa;
	private String direccion;
}
