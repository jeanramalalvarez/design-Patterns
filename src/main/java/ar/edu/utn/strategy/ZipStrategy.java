package ar.edu.utn.strategy;

import java.io.File;

public class ZipStrategy implements CompresionStrategy {

	@Override
	public MensajeCompresion comprimir(File archivo) {
		// TODO Auto-generated method stub
		return MensajeCompresion.ZIPedado_CORRECTAMENTE;
	}

}
