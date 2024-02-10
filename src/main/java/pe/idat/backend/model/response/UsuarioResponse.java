package pe.idat.backend.model.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UsuarioResponse {
    private Integer idusuario;
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
