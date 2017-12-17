package Exceptions;

public class ErrorGenerarCodi extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ErrorGenerarCodi(){
		super("\nEl codi o autor te un espai.");
	}

}