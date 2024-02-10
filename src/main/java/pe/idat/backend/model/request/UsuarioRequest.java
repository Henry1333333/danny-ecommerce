package pe.idat.backend.model.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UsuarioRequest {
    private Integer idusuario;
    private Integer idrol;
    private String username;
	private String password;
	private String nombre;
    private String apellido;
    private String email;
	private String telefono;
	private String dni;
	private String direccion;
    private String perfil;
    private Boolean estado;
    
}
