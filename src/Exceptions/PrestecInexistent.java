package Exceptions;

public class PrestecInexistent extends Exception{
	private static final long serialVersionUID = 1L;

	public PrestecInexistent() 
	  { 
		  super("Prestec no trobat"); 
	  }
}
