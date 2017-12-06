package Exceptions;

import java.util.Date;

public class ReservesDiaSuperior30 extends Exception {
	
	private static final long serialVersionUID = 1L;

public ReservesDiaSuperior30(Date data){
		
		super("En la data " +data+ " ja no es poden reservar més llibres");
	}

}