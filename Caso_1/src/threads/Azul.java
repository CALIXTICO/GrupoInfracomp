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
		//Comportamiento de Procesos de Etapa 1
		if (etapa == 1)
		{
			while (cantidadProductosProcesar > 0)
			{
				//Creacion de un producto
				crearProducto();
				cantidadProductosProcesar--;
				
				//Entrega del producto al buzon de salida
				entregarProducto();
				
			}			
		}
		
		else
		{
			while (cantidadProductosProcesar > 0)
			{
				//Extraccion del producto del buzon de entrada
				extraerProducto();
				
				//Procesamiento del producto
				procesarProducto();
				cantidadProductosProcesar--;
				
				//Entrega del producto al buzon de salida
				entregarProducto();
			}
		}
	}
	
	public void crearProducto()
	{
		synchronized(buzonSalida)
		{
			try 
			{
				sleep((long) (Math.random()*450));	
				
				productoEnProceso = new Producto(identificador.getIdActual(), "Creacion de Producto Azul en Etapa 1 ", 0);
				System.out.println("Se crea producto azul " + productoEnProceso.getIdentificador() + " en etapa " + etapa);
				identificador.sumIdActual();
			} 
			catch (InterruptedException e) {}
		}
	}
	
	public void entregarProducto()
	{
		synchronized(buzonSalida)
		{
			try
			{
				boolean entregoProducto = false;
				while (!entregoProducto)
				{
					if (buzonSalida.estaLleno())
					{
						buzonSalida.wait();
					}
					else
					{
						System.out.println("Se entrego producto azul  " + productoEnProceso.getIdentificador() + " en etapa " + etapa);
						buzonSalida.almacenarAzul(productoEnProceso);
						buzonSalida.notifyAll();
						entregoProducto = true;
						productoEnProceso = null;
					}
				}
			}
			catch(InterruptedException e) {}
		}
	}
	
	public void extraerProducto()
	{
		synchronized(buzonEntrada)
		{
			try
			{
				boolean extrajoProducto = false;
				while (!extrajoProducto)
				{
					if (!buzonEntrada.hayProductosAzules())
					{
						buzonEntrada.wait();
					}
					else
					{
						productoEnProceso = buzonEntrada.retirarAzul();
						System.out.println("Se extrajo producto azul " + productoEnProceso.getIdentificador() + " en etapa " + etapa);
						buzonEntrada.notifyAll();
						extrajoProducto = true;
					}
				}
			}
			catch(InterruptedException e) {}
		}
	}
	
	public void procesarProducto()
	{
		try 
		{
			sleep((long) (50 + Math.random()*450));		
			
			productoEnProceso.setMensaje(productoEnProceso.getMensaje() + "Modificado el proceso azul de la etapa " + etapa + ". ");
		} 
		catch (InterruptedException e) {}
	}
	

}
