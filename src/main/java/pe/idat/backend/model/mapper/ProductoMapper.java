package pe.idat.backend.model.mapper;

import pe.idat.backend.model.Producto;
import pe.idat.backend.model.response.ProductoListResponse;

public class ProductoMapper {
	public static ProductoListResponse productoMap(Producto mapeado) {
		return ProductoListResponse.builder().idproducto(mapeado.getIdproducto())
				.categoria(mapeado.getIdcategoria().getNombre())
				.codigo(mapeado.getCodigo())
				.nombre(mapeado.getNombre())
				.descripcion(mapeado.getDescripcion())
				.precio(mapeado.getPrecio())
				.cantidad(mapeado.getCantidad())
				.imagen(mapeado.getImagen())
				.estado(mapeado.getEstado())
				.build();
	}
}
