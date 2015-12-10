package ar.edu.utn.builder;

public class MacUltraBookPro7Builder extends NotebookBuilder {

	public MacUltraBookPro7Builder() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void definirNotebook() {
		// TODO Auto-generated method stub
		super.setNotebook(new Notebook());
		this.getNotebook().setMarca("Mac");
		this.getNotebook().setModelo("Ultrabook Pro 7");
	}

	@Override
	public void construirPantalla() {
		// TODO Auto-generated method stub
		Pantalla pantalla =  new Pantalla();
		pantalla.setMarca("Mac Display");
		pantalla.setModelo("3D");
		pantalla.setPulgadas(14);
		this.getNotebook().setPantalla(pantalla);
	}

	@Override
	public void construirDisco() {
		// TODO Auto-generated method stub
		DiscoRigido disco = new DiscoRigido();
		disco.setMarca("Mac Hdd");
		disco.setModelo("Hiper Lightweight");
		disco.setCapacidad("32 Tb");
		this.getNotebook().setDisco(disco);
	}

	@Override
	public void construirProcesador() {
		// TODO Auto-generated method stub
		Procesador procesador = new Procesador();
		procesador.setCacheL1("5Mb");
		procesador.setCacheL2("3Mb");
		procesador.setMarca("Mac Processor");
		procesador.setModelo("Hiper Fast T1000");
		procesador.setVelocidad(8.5);
		super.getNotebook().getPlacaMadre().setProcesador(procesador);
	}

	@Override
	public void construirMemoria() {
		// TODO Auto-generated method stub
		Memoria memoria = new Memoria();
		memoria.setCapacidad("32Gb");
		memoria.setMarca("Mac Memory");
		memoria.setModelo("2015");
		this.getNotebook().getPlacaMadre().setMemoria(memoria);
	}

	@Override
	public void construirPlacaMadre() {
		// TODO Auto-generated method stub
		PlacaMadre pm = new PlacaMadre();
		pm.setMarca("Asrock");
		pm.setModelo("747");
		this.getNotebook().setPlacaMadre(pm);
		
	}

}
