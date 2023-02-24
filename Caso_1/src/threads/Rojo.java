package threads;
import ejecucion.Producto;
import ejecucion.BuzonFinal;

public class Rojo extends Thread{
	private BuzonFinal buzon;
	private int cantTotal;

	//Metodo constructor el cual recibe el buzon final y la cantidad de productos a crear en total
	public Rojo(BuzonFinal buzon, Integer cant){
		this.buzon = buzon;
		this.cantTotal = cant;

	}
	// Como en este proceso se cuantos productos se produciran en un for busco obtener todos los elementos del hashmap y imprimirlos
	// Para que no se impriman todos seguidos se usa un sleep para simular que el proceso tarda retirar producto a producto
	public synchronized void retirarBuzonOut(){
		for (int i = 0; i < cantTotal; i++){
			Producto productoRetirado = buzon.retirarElemento(i);
			Integer identificador = productoRetirado.getIdentificador();
			String resultado = productoRetirado.getMensaje();
			System.out.println("El producto con identificador " + identificador + " con el mensaje ' " + resultado + " ' ha sido generado por el thread rojo\n");
			long numero = ((long) (50 + Math.random()*450));
			try {
				sleep(numero);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void run()
	{
		// En este While se revisa si el buzon final ya cuenta con todos los elementos necesarios para empezar a retirar
		// Aca se simula la espera activa de manera que hasta que no se cumpla la condicion anunciar que aun no es posible realizar le ejecucion
		while(this.buzon.numProductos() < cantTotal){
			long numero = ((long) (50 + Math.random()*450));
			try {
				sleep(numero);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		retirarBuzonOut(); // Una vez ya se encuentren todos los elemetnos dentro del buzon ahora el thread puede empezar a imprimir los productos generados.
		
	}
}
