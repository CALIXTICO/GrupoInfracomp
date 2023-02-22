package threads;

import ejecucion.Buzon;
import ejecucion.Producto;

public class Azul extends Thread {
	
	
	private int cantidadObjetosProcesar;
	private int etapa;
	private Producto producto;
	private Buzon buzonEntrada;
	private Buzon buzonSalida;
	
	public Azul(int etapa, int cantidadObjetosProcesar, Buzon buzonEntrada, Buzon BuzonSalida)
	{
		this.etapa = etapa;
		this.cantidadObjetosProcesar = cantidadObjetosProcesar;
		this.buzonEntrada = buzonEntrada;
		this.buzonSalida = buzonSalida;
	}
	

	public void run () {
		
	}

}
