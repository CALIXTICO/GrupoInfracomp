package ejecucion;

public class Identificador {
	
	private int idActual;
	public int idMaximo;
	
	public Identificador (int idMaximo)
	{
		this.idActual = 0;
		this.idMaximo = idMaximo;
	}

	public int getIdActual() {
		return idActual;
	}

	public void sumIdActual() {
		this.idActual+= 1;
	}

}
