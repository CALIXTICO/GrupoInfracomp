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
	
	public Naranja(Buzon buzonEntada, Buzon buzonSalida, int productos, int etapa, Producto productoEnProceso, Identificador identificador)
	{
		this.buzonEntrada = buzonEntada;
		this.buzonSalida = buzonSalida;
		this.productos = productos;
		this.etapa = etapa;
		this.productoEnProceso = productoEnProceso;
		this.identificador = identificador;
	}
	
	@Override
	
	public void run()
	{
		boolean termino = false;
		while(!termino)
		{
			while(productos > 0)
			{
				Naranja.yield();
				if(etapa == 1)
				{
					crearProducto();
					System.out.println(productoEnProceso.getIdentificador());
					productos--;
					if(productos == 0)
						termino = true;
				}
				if(etapa > 1)
				{
					Producto prodE = buzonEntrada.retirarNarnja();
					System.out.println("El proceso recibió el producto: " + prodE);
					crearProducto();
					System.out.println(productoEnProceso.getIdentificador());
					productos--;
					if(productos == 0)
						termino = true;
				}
				
				
			}
		}
		
	}
	
	public void crearProducto()
	{
		synchronized(buzonSalida)
		{
			productoEnProceso = new Producto(identificador.getIdActual(), "Creacion de Producto naranja en Etapa 1", 0);
			identificador.sumIdActual();
		}
	}
}
