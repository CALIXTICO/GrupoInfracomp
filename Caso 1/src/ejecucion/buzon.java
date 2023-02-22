package ejecucion;
import java.util.ArrayList;

public class buzon {
    private int capacidadLimite;
    ArrayList<Producto> productoAzul = new ArrayList<Producto>();
    ArrayList<Producto> productoNaranja = new ArrayList<Producto>();

    public buzon (int cant){
        capacidadLimite = cant;
    }
     // Almacena productos de tipo naranja en el array de productos naranjas
    public synchronized void almacenarNaranja (Producto i){
        productoNaranja.add(i);
    }
     // Almacena productos de tip azul en el arrey de productos azules
    public synchronized void almacenarAzul (Producto i){
        productoAzul.add(i);
    }
     // Retira productos de tipo naranja del array de productos naranjas
    public synchronized Producto retirarNarnja (){
        Producto producto = productoNaranja.get(productoNaranja.size());
        return producto;
        
    }
     // Retira productos de tipo azules del array de productos azules
    public synchronized Producto retirarAzul (){
        Producto producto = productoAzul.get(productoAzul.size());
        return producto;
    }

    // Para que la capacidad del buzón no se duplique al tener dos arrays el límite del buzón es por ende la suma de los tamaños del array, la suma de estos
    // debe ser inferior a la capacidad dada por parametro.
    public int capacidadActual(){
        return productoAzul.size() + productoNaranja.size();
    }
    public int getCapacidadLimite(){
        return capacidadLimite;
        
    }
    
}
