package edu.unq.desapp.groupA.backend.dto;

public class ArchivoDTO {
	
	private Long id;
	
    private String nombre;

    private String path;

    public ArchivoDTO(){}

    public ArchivoDTO(Long id, String nombre, String path) {
        this.nombre = nombre;
        this.path = path;
        this.id = id;
    }
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
