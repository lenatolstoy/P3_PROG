package Exceptions;

public class DataIncorrecta extends Exception{
	private static final long serialVersionUID = 1L;

	public DataIncorrecta() {
		super("La data no es correcta");
	}
}
