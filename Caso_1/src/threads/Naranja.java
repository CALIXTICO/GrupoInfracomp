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
		boolean termino = false;
		while(!termino)
		{
			if(etapa == 1)
			{
				while(productos > 0)
				{
					crearProducto();
					productos--;
					
					if(buzonSalida.estaLleno())
					{
						Naranja.yield();
					}
					else
					{
						System.out.println("Se entregó producto " + productoEnProceso.getIdentificador() + " en etapa " + etapa);
						buzonSalida.almacenarNaranja(productoEnProceso);
						productoEnProceso = null;
					}
				}
			}
			else
			{
				while(productos > 0)
				{
					if (!buzonEntrada.hayProductosNaranjas())
					{
						Naranja.yield();
					}
					else
					{
						productoEnProceso = buzonEntrada.retirarNarnja();
						System.out.println("Se extrajo producto " + productoEnProceso.getIdentificador() + " en etapa " + etapa);
					}
					
				}
				
				try 
				{
					sleep((long) (Math.random()*450));		
					
					productoEnProceso.setMensaje(productoEnProceso.getMensaje() + "modificado en proceso naranja de la etapa " + etapa);
				} 
				catch (InterruptedException e) {
					
				}
				
				productos--;
				
				if (buzonSalida.estaLleno())
				{
					Naranja.yield();
				}
				else
				{
					System.out.println("Se entrego producto " + productoEnProceso.getIdentificador() + " en etapa " + etapa);
					buzonSalida.almacenarNaranja(productoEnProceso);
					productoEnProceso = null;
				}

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
}
