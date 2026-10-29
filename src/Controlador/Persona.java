package Controlador;

public class Persona {
    private int id;
    private String nombre;
    private int edad;
    private String genero;
    private String direccion;

    public Persona(int id, String nombre, int edad, String genero, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.direccion = direccion;
    }

    // Getters y Setters
    public int getId() { 
    	return id;
    	}
    public void setId(int id) { 
    	this.id = id; 
    	}

    public String getNombre() {
    	return nombre; 
    	}
    public void setNombre(String nombre) { 
    	this.nombre = nombre; 
    	}

    public int getEdad() { 
    	return edad; 
    	}
    public void setEdad(int edad) { 
    	this.edad = edad; 
    	}

    public String getGenero() { 
    	return genero; 
    	}
    public void setGenero(String genero) { 
    	this.genero = genero;
    	}

    public String getDireccion() { 
    	return direccion; 
    	}
    public void setDireccion(String direccion) { 
    	this.direccion = direccion; 
    	}
}
