package threads;
import ejecucion.Producto;
import ejecucion.buzonFinal;

public class rojo extends Thread{
	private buzonFinal buzon;
	private int cantTotal;

	//Metodo constructor el cual recibe el buzón final y la cantidad de productos a crear en total
	public rojo(buzonFinal buzon, Integer cant){
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
			System.out.println("El producto con identificador " + identificador + "con el mensaje " + resultado + " ha sido generado");
			int numero = (int)(Math.random()*6);
			try {
				rojo.sleep(5000* numero);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void run()
	{
		// En este While se revisa si el buzón final ya cuenta con todos los elementos necesarios para empezar a retirar
		// Acá se simula la espera activa de manera que hasta que no se cumpla la condición anunciará que aún no es posible realizar le ejecución
		while(this.buzon.capacidadActual() < cantTotal){
			int numero = (int)(Math.random()*6);
			System.out.println("Aún no es posible retirar todos los elementos");
			try {
				rojo.sleep(5000 * numero);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		retirarBuzonOut(); // Una vez ya se encuentren todos los elemetnos dentro del buzón ahora el thread puede empezar a imprimir los productos generados.
		
	}
}
