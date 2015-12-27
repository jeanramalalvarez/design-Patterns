package ar.edu.utn.strategy;

import java.io.File;

public class RarStrategy implements CompresionStrategy {

	@Override
	public MensajeCompresion comprimir(File archivo) {
		// TODO Auto-generated method stub
		return MensajeCompresion.RAReado_CORRECTAMENTE;
	}

}
