package supergiros.model;

public class mdlUsuario {
    private Integer Id;
    private String Nombre;
    private String Apellido;
    private Integer Edad;
    private Boolean Estado;

    public mdlUsuario(Integer id, String nombre, String apellido, Integer edad, Boolean estado) {
        Id = id;
        Nombre = nombre;
        Apellido = apellido;
        Edad = edad;
        Estado = estado;
    }

    public mdlUsuario() {

    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public Integer getEdad() {
        return Edad;
    }

    public void setEdad(Integer edad) {
        Edad = edad;
    }

    public Boolean getEstado() {
        return Estado;
    }

    public void setEstado(Boolean estado) {
        Estado = estado;
    }
}
