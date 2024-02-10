package pe.idat.backend.model.mapper;

import pe.idat.backend.model.Rol;
import pe.idat.backend.model.response.RolResponse;


public class RolMapper {
	public static RolResponse rolMap(Rol mapeado) {
		return RolResponse.builder().idrol(mapeado.getRolId())
				.nombrerol(mapeado.getRolNombre())
				.build();
	}

}
