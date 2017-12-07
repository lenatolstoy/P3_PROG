package Exceptions;

public class PrestecJaExisteix extends Exception{
	private static final long serialVersionUID = 1L;

	public PrestecJaExisteix() 
	  { 
		  super("L'usuari ja te un prestec actiu d'aquest llibre"); 
	  }

}
