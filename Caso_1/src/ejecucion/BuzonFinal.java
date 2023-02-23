package ejecucion;

import java.util.HashMap;

public class BuzonFinal extends Buzon{
    private HashMap <Integer, Producto> map = new HashMap<Integer, Producto>();

    public BuzonFinal(int cant) {
        super(cant);
    }

    //Dado que acá no hay dos listas es necesario reescribir el metodo de capacidad actual para que retorne el tamaño del HashMap
    @Override
    public synchronized int capacidadActual(){
        return map.size();
    }

    // Recibe la llave y de allí toma un elemento y lo retorna 
    public synchronized Producto retirarElemento(Integer llave){
        return map.get(llave);
    }

    // Toma el identificador de cada producto y lo usa como llave para ponerlo en el HashMap
    public synchronized void agregarElemento(Producto product){
        Integer identificador = product.getIdentificador();
        map.put(identificador, product);

    }
    
    @Override
    public synchronized boolean estaLleno()
    {
        return (false);
        
    }

    
}
