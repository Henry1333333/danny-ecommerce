package pe.idat.backend.model.mapper;

import pe.idat.backend.model.DetalleVenta;
import pe.idat.backend.model.response.DVListResponse;

public class DetalleVentaMapper {
	public static DVListResponse detalleVentaMap(DetalleVenta mapeado) {
		return DVListResponse.builder().iddetventa(mapeado.getIddetventa())
				.idventa(mapeado.getIdventa().getIdventa())
				.nombreproducto(mapeado.getIdproducto().getNombre())
				.cantidad(mapeado.getCantidad())
				.precio(mapeado.getPrecio())
				.estado(mapeado.getEstado())
				.build();
				
	}
}
