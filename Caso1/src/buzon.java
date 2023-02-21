package ejecucion;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.plaf.synth.SynthButtonUI;

public class buzon {
    private int capacidadLimite;
    ArrayList<Producto> productoAzul = new ArrayList<Producto>();
    ArrayList<Producto> productoNaranja = new ArrayList<Producto>();

    public buzon (int cant){
        capacidadLimite = cant;
    }

    public synchronized void almacenarNaranja (Producto i){
        productoNaranja.add(i);
    }
    public synchronized void almacenarAzul (Producto i){
        productoAzul.add(i);
    }
    public synchronized Producto retirarNarnja (){
        Producto producto = productoNaranja.get(productoNaranja.size());
        return producto;
        
    }
    public synchronized Producto retirarAzul (){
        Producto producto = productoAzul.get(productoAzul.size());
        return producto;
    }
    public int capacidadActual(){
        return productoAzul.size() + productoNaranja.size();
    }
    public int getCapacidadLimite(){
        return capacidadLimite;
        
    }
    
}
