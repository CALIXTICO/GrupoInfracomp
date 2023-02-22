package ejecucion;

import java.util.ArrayList;


public class Buzon {
    private int capacidadLimite;
    ArrayList<Producto> productoAzul = new ArrayList<Producto>();
    ArrayList<Producto> productoNaranja = new ArrayList<Producto>();

    public Buzon (int cant){
        capacidadLimite = cant;
    }

    public synchronized void almacenarNaranja (Producto i){
        productoNaranja.add(i);
    }
    public synchronized void almacenarAzul (Producto i){
        productoAzul.add(i);
    }
    public synchronized Producto retirarNarnja (){
        Producto producto = productoNaranja.remove(0);
        return producto;
        
    }
    public synchronized Producto retirarAzul (){
        Producto producto = productoAzul.remove(0);
        return producto;
    }
    public int capacidadActual(){
        return productoAzul.size() + productoNaranja.size();
    }
    public int getCapacidadLimite(){
        return capacidadLimite;
        
    }
    
}
