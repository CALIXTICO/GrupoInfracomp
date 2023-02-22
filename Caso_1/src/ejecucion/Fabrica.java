package ejecucion;

import java.util.Scanner;

import threads.Azul;
import threads.Rojo;

public class Fabrica {
	
	//Contador de IDs de productos
	static Identificador identificador;
	
	//Buzones de cada etapa
	static Buzon buzon1;
	static Buzon buzon2;
	static BuzonFinal buzonFinal;
	

	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		
		
		//Solicitudes de datos al usuario
		
		System.out.println("Por favor ingrese la capacidad máxima de los buzones 1 y 2:");
		int capacidadBuzones = in.nextInt();
		
		System.out.println("Por favor ingrese el número de procesos que habrá por etapa:");
		int numProcesosPorEtapa = in.nextInt();
		
		System.out.println("Por favor ingrese el número de productos que creará cada proceso en la etapa 1:");
		int numProductosPorProceso = in.nextInt();
		
		
		//Creación del identificador
		identificador = new Identificador((numProductosPorProceso * numProcesosPorEtapa)  - 1);
		
				
		//Creacion de buzones
		buzon1 = new Buzon(capacidadBuzones);
		buzon2 = new Buzon(capacidadBuzones);
		buzonFinal = new BuzonFinal(-1);
		
		//Creacion de Procesos
		
		//Azules
		creacionProcesosAzules(numProcesosPorEtapa, numProductosPorProceso );
		
		//Naranjas
		
		
		//Rojo
		Rojo procesoRojo = new Rojo(buzonFinal, numProcesosPorEtapa*numProductosPorProceso);
		procesoRojo.start();
		
		

	}
	
	public static void creacionProcesosAzules(int numProcesosPorEtapa, int numProductosPorProceso)
	{
		int numProcesosAzules = (numProcesosPorEtapa-1)*3;
		
		Azul [] procesosAzules = new Azul[numProcesosAzules];
		
		//Creación Etapa 1
		for (int i = 0; i < numProcesosPorEtapa-1; i++)
		{
			procesosAzules[i] = new Azul(1, numProductosPorProceso, null, buzon1, identificador);
		}
		
		//Creación Etapa 2
		for (int i = numProcesosPorEtapa-1; i < (numProcesosPorEtapa-1)*2; i++)
		{
			procesosAzules[i] = new Azul(2, numProductosPorProceso, buzon1, buzon2, identificador);
		}
		
		//Creación Etapa 3
		for (int i = (numProcesosPorEtapa-1)*2; i < (numProcesosPorEtapa-1)*3; i++)
		{
			procesosAzules[i] = new Azul(3, numProductosPorProceso, buzon2, buzonFinal, identificador);
		}
		
		//Despertado de Procesos
		for (int i = 0; i < numProcesosAzules; i++)
		{
			procesosAzules[i].start();
		}
		
		
		
	}

}
