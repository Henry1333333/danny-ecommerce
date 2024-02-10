package pe.idat.backend.model.response;

public class CloudinaryResponse {
    private String url;

    // Constructor, getters, setters, etc.
    
    public CloudinaryResponse(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
