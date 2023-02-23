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
				//Creación de un producto
				crearProducto();
				productos--;
				
				//Entrega del producto al buzón de salida
				entregarProducto();
				
			}			
		}
		
		else
		{
			while (productos > 0)
			{
				//Extracción del producto del buzon de entrada
				extraerProducto();
				
				//Procesamiento del producto
				procesarProducto();
				productos--;
				
				//Entrega del producto al buzón de salida
				entregarProducto();
			}
		}
	}
	
	public void crearProducto()
	{
		synchronized(buzonSalida)
		{
				
			productoEnProceso = new Producto(identificador.getIdActual(), "Creacion de Producto Naranja en Etapa 1", 0);
			System.out.println("Se creo producto " + productoEnProceso.getIdentificador() + " en etapa " + etapa);
			identificador.sumIdActual();
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
						System.out.println("Se entrego producto " + productoEnProceso.getIdentificador() + " en etapa " + etapa);
						buzonSalida.almacenarNaranja(productoEnProceso);
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
					if (!buzonEntrada.hayProductosNaranjas())
					{
						buzonEntrada.wait();
					}
					else
					{
						productoEnProceso = buzonEntrada.retirarNaranja();
						System.out.println("Se extrajo producto " + productoEnProceso.getIdentificador() + " en etapa " + etapa);
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
			
			productoEnProceso.setMensaje(productoEnProceso.getMensaje() + "Modificado en proceso naranja de la etapa " + etapa + ". ");
		} 
		catch (InterruptedException e) {}
	}
}
