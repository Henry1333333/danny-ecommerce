package pe.idat.backend.model;

import java.io.Serializable;
import java.util.Calendar;
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
@Table(name = "venta")
public class Venta implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
    private Integer idventa;
	
	@JsonBackReference(value = "venta-usuario")
	@ManyToOne()
	@JoinColumn(name = "id")
	private Usuario idusuario;
	@Column
    private String tipoComprobate;
	@Column
    private String tipoEntrega;
	@Column
	private Calendar fechaVenta;
	@Column
    private Integer precioTotal;
	@Column
    private Boolean estado;

}
