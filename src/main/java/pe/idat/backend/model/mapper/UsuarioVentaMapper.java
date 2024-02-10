package pe.idat.backend.model.mapper;

import pe.idat.backend.model.DetalleVenta;
import pe.idat.backend.model.response.UsuarioVentaResponse;

public class UsuarioVentaMapper {
	public static UsuarioVentaResponse ventaMap(DetalleVenta mapeado) {
		return UsuarioVentaResponse.builder().fechaVenta(mapeado.getIdventa().getFechaVenta())
				.precioTotal(mapeado.getIdventa().getPrecioTotal())
				.producto(mapeado.getIdproducto().getNombre())
				.imagen(mapeado.getIdproducto().getImagen())
				.precioUnitario(mapeado.getPrecio())
				.cantidad(mapeado.getCantidad())
				.build();
	}
}
