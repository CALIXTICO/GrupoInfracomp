package ejecucion;

public class Producto {
    private int identificador;
    private String mensaje;

    public Producto (int identificador, String mensajeInicial, int procesoEncargado){
        this.identificador = identificador;
        this.mensaje = mensajeInicial;
    }

    public void transformar (String colorNuevo){
        this.mensaje = colorNuevo;
    }
    public int getIdentificador(){
        return this.identificador;
    }
    public String getMensaje(){
        return this.mensaje;
    }
    public void setMensaje(String mensaje){
        this.mensaje = mensaje;
    }

}
