package ejecucion;

import java.util.HashMap;

public class BuzonFinal extends Buzon{
    private HashMap <Integer, Producto> map = new HashMap<Integer, Producto>();

    public BuzonFinal(int cant) {
        super(cant);
    }

    //Dado que aca no hay dos listas es necesario reescribir el metodo de capacidad actual para que retorne el tamanio del HashMap
  
    public synchronized int numProductos(){
        return map.size();
    }

    // Recibe la llave y de alla toma un elemento y lo retorna 
    public synchronized Producto retirarElemento(Integer llave){
        return map.get(llave);
    }
    
    @Override
    public synchronized boolean estaLleno()
    {
        return (false);
        
    }
    
    @Override
    public synchronized void almacenarNaranja (Producto product){
    	Integer identificador = product.getIdentificador();
        map.put(identificador, product);
    }
    
    @Override
    public synchronized void almacenarAzul (Producto product){
    	Integer identificador = product.getIdentificador();
        map.put(identificador, product);
    }

    
}
