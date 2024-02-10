package pe.idat.backend.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "producto")
public class Producto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer idproducto;
	@ManyToOne()
	@JoinColumn(name = "idcategoria")
	private Categoria idcategoria;
	@Column
	private Integer codigo;
	@Column
	private String nombre;
	@Column
	private String descripcion;
	@Column
	private Integer precio;
	@Column
	private Integer cantidad;
	@Column
	private String imagen;
	@Column
	private Boolean estado;
	
}
