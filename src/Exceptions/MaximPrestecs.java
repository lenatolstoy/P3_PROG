package Exceptions;

public class MaximPrestecs extends Exception{
	private static final long serialVersionUID = 1L;

	public MaximPrestecs() {
		super("El soci ha arribat al maxim nombre de prestecs");
	}
}
