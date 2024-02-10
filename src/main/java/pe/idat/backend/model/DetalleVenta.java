package pe.idat.backend.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "detalleventa")
public class DetalleVenta implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
    private Integer iddetventa;
	
	@JsonBackReference(value = "detventa-venta")
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "idventa")
	private Venta idventa;
	
	@JsonBackReference(value = "detventa-producto")
	@ManyToOne()
	@JoinColumn(name = "idproducto")
	private Producto idproducto;
	@Column
    private Integer cantidad;
	@Column
    private Integer precio;
	@Column
    private Boolean estado;


}
