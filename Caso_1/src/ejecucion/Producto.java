package ejecucion;

public class Producto {
    private int identificador;
    private String mensaje;
    private int colorReceptor; // El color será 1 en caso de que el proceso sea naranja y 0 si son azules

    public Producto (int identificador, String mensajeInicial, int procesoEncargado){
        this.identificador = identificador;
        this.mensaje = mensajeInicial;
        this.colorReceptor = procesoEncargado;
    }

    public void transformar (String modificacion){
        this.mensaje = this.mensaje + "\n" + modificacion;
    }
    public int getThread(){
        return this.colorReceptor;
    }
}
