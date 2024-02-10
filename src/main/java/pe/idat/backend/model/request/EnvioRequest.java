package pe.idat.backend.model.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EnvioRequest {
	private Integer idenvio;
	private Integer idventa;
	private String region;
	private String provincia;
	private String distrito;
	private String direccionenvio;
}
