package Exceptions;

public class LlibreNoDisponible extends Exception{
	private static final long serialVersionUID = 1L;

	public LlibreNoDisponible(String nomllibre){
		
		super("El llibre " +nomllibre+ " no es troba disponible");
	}
	public LlibreNoDisponible(){
		
		super("El llibre no es troba disponible");
	}
	
}
