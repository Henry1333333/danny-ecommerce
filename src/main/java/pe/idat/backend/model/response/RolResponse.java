package pe.idat.backend.model.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RolResponse {
	private Integer idrol;
	private String nombrerol;
}
