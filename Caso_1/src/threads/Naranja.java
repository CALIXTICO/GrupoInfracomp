package threads;

import ejecucion.Buzon;
import ejecucion.Identificador;
import ejecucion.Producto;

public class Naranja extends Thread{
	private Buzon buzonEntrada;
	private Buzon buzonSalida;
	private int productos;
	private int etapa;
	private Producto productoEnProceso;
	private Identificador identificador;
	
	public Naranja(Buzon buzonEntada, Buzon buzonSalida, int productos, int etapa, Identificador identificador)
	{
		this.buzonEntrada = buzonEntada;
		this.buzonSalida = buzonSalida;
		this.productos = productos;
		this.etapa = etapa;
		this.identificador = identificador;
	}
	
	@Override
	
	public void run()
	{
		//Comportamiento de Procesos de Etapa 1
		if (etapa == 1)
		{
			while (productos > 0)
			{
				//Creacion de un producto
				crearProducto();
				productos--;
				
				//Entrega del producto al buzon de salida
				entregarProducto();
				
			}			
		}
		
		else
		{
			while (productos > 0)
			{
				//Extraccion del producto del buzon de entrada
				extraerProducto();
				
				//Procesamiento del producto
				procesarProducto();
				productos--;
				
				//Entrega del producto al buz�n de salida
				entregarProducto();
			}
		}
	}
	
	public void crearProducto()
	{
		synchronized(buzonSalida)
		{
				
			productoEnProceso = new Producto(identificador.getIdActual(), "Creacion de Producto Naranja en Etapa 1 ", 0);
			System.out.println("Se creo producto naranja " + productoEnProceso.getIdentificador() + " en etapa " + etapa);
			identificador.sumIdActual();
		}
	}
	
	public void entregarProducto()
	{
			boolean entregoProducto = false;
			while (!entregoProducto)
			{
				if (buzonSalida.estaLleno())
				{
					Thread.yield();
				}
				else
				{
					System.out.println("Se entrego producto naranja " + productoEnProceso.getIdentificador() + " en etapa " + etapa);
					buzonSalida.almacenarNaranja(productoEnProceso);
					notificarAzules(buzonSalida);
					entregoProducto = true;
					productoEnProceso = null;
				}
			}
		
	}
	
	public void extraerProducto()
	{
			boolean extrajoProducto = false;
			while (!extrajoProducto)
			{
				if (!buzonEntrada.hayProductosNaranjas())
				{
					Thread.yield();
				}
				else
				{
					productoEnProceso = buzonEntrada.retirarNaranja();
					System.out.println("Se extrajo producto naranja " + productoEnProceso.getIdentificador() + " en etapa " + etapa);
					notificarAzules(buzonEntrada);
					extrajoProducto = true;
				}
			}
		
	}
	
	public void procesarProducto()
	{
		try 
		{
			sleep((long) (50 + Math.random()*450));		
			
			productoEnProceso.setMensaje(productoEnProceso.getMensaje() + "Modificado el proceso naranja de la etapa " + etapa + ". ");
		} 
		catch (InterruptedException e) {}
	}
	
	public void notificarAzules(Buzon buzon)
	{
		synchronized(buzon)
		{
			buzon.notifyAll();
		}
	}
}
