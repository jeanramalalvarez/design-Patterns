package ar.edu.utn.builder;

public class LenovoG475Builder extends NotebookBuilder {

	public LenovoG475Builder() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void definirNotebook() {
		// TODO Auto-generated method stub
		this.setNotebook(new Notebook());
		this.getNotebook().setMarca("Lenovo");
		this.getNotebook().setModelo("G475");
		
	}

	@Override
	public void construirPantalla() {
		// TODO Auto-generated method stub
		Pantalla pantalla =  new Pantalla();
		pantalla.setMarca("Samsung");
		pantalla.setModelo("Led");
		pantalla.setPulgadas(14);
		this.getNotebook().setPantalla(pantalla);
	}

	@Override
	public void construirDisco() {
		// TODO Auto-generated method stub
		DiscoRigido disco = new DiscoRigido();
		disco.setMarca("Samsung");
		disco.setModelo("2016");
		disco.setCapacidad("8 Tb");
		this.getNotebook().setDisco(disco);
	}

	@Override
	public void construirProcesador() {
		// TODO Auto-generated method stub
		Procesador procesador = new Procesador();
		procesador.setCacheL1("3Mb");
		procesador.setCacheL2("1Mb");
		procesador.setMarca("Intel I3");
		procesador.setModelo("2016");
		procesador.setVelocidad(3.5);
		super.getNotebook().getPlacaMadre().setProcesador(procesador);
	}

	@Override
	public void construirMemoria() {
		// TODO Auto-generated method stub
		Memoria memoria = new Memoria();
		memoria.setCapacidad("8Gb");
		memoria.setMarca("Sony");
		memoria.setModelo("DD3");
		this.getNotebook().getPlacaMadre().setMemoria(memoria);
	}

	@Override
	public void construirPlacaMadre() {
		// TODO Auto-generated method stub
		PlacaMadre pm = new PlacaMadre();
		pm.setMarca("Asus");
		pm.setModelo("9000");
		this.getNotebook().setPlacaMadre(pm);
	}

}
