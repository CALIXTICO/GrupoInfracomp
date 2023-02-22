package threads;

import ejecucion.Buzon;
import ejecucion.Identificador;
import ejecucion.Producto;

public class Azul extends Thread {
	
	
	private int cantidadProductosProcesar;
	private int etapa;
	private Producto productoEnProceso;
	private Identificador identificador;
	private Buzon buzonEntrada;
	private Buzon buzonSalida;
	
	public Azul(int etapa, int cantidadProductosProcesar, Buzon buzonEntrada, Buzon buzonSalida, Identificador identificador)
	{
		this.etapa = etapa;
		this.cantidadProductosProcesar = cantidadProductosProcesar;
		this.buzonEntrada = buzonEntrada;
		this.buzonSalida = buzonSalida;
		this.identificador = identificador;
	}
	

	public void run () 
	{
		if (etapa == 1)
		{
			while (cantidadProductosProcesar > 0)
			{
				crearProducto();
				System.out.println(productoEnProceso.getIdentificador());
				cantidadProductosProcesar--;
			}
			
		}
	}
	
	public void crearProducto()
	{
		synchronized(buzonSalida)
		{
			productoEnProceso = new Producto(identificador.getIdActual(), "Creación de Producto Azul en Etapa 1", 0);
			identificador.sumIdActual();
		}
	}
	

}
