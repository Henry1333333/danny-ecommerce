package pe.idat.backend.model.mapper;

import pe.idat.backend.model.DetalleVenta;
import pe.idat.backend.model.Venta;
import pe.idat.backend.model.response.VentaListResponse;

public class VentaMapper {
	public static VentaListResponse ventaMap(DetalleVenta mapeado) {
		return VentaListResponse.builder().idventa(mapeado.getIdventa().getIdventa())
				.nombrecliente(mapeado.getIdventa().getIdusuario().getNombre())
				.apellidocliente(mapeado.getIdventa().getIdusuario().getApellido())
				.dnicliente(mapeado.getIdventa().getIdusuario().getDni())
				.tipoComprobate(mapeado.getIdventa().getTipoComprobate())
				.tipoEntrega(mapeado.getIdventa().getTipoEntrega())
				.fechaVenta(mapeado.getIdventa().getFechaVenta())
				.preciototal(mapeado.getIdventa().getPrecioTotal())
				.producto(mapeado.getIdproducto().getNombre())
				.cantidad(mapeado.getCantidad())
				.precioUnitario(mapeado.getIdproducto().getPrecio())
				.build();
	}
}
