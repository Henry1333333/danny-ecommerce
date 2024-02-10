package pe.idat.backend.model.request;

public class JwtRequest {

    private String usuario;
    private String contrasena;

    public JwtRequest(){

    }

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}


}
