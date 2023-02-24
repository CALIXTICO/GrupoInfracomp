package ejecucion;

public class Identificador {
	
	private int idActual;
	public int idMaximo; // id maximo que puede llegar a tener un producto de acuerdo con la cantida de productos por proceso,
	
	public Identificador (int idMaximo)
	{
		this.idActual = 0;
		this.idMaximo = idMaximo;
	}

	public synchronized int getIdActual() {
		return idActual;
	}
	// Esta función permite que cada vez que se cree un producto se pueda aumentar el numero de identificador que deberia tener el siguiente producto.
	public synchronized void sumIdActual() {
		this.idActual+= 1;
	}

}
