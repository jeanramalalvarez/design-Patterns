package ar.edu.utn.model;

import ar.edu.utn.exceptions.NotebookModelInvalidException;

public class LenovoFactoryNotebook extends FactoryNotebook {


	@Override
	public Notebook create(String model) throws NotebookModelInvalidException {
		// TODO Auto-generated method stub
		switch (model) {
		case "G475":
			return new LenovoG475Notebook();			

		case "G575":
			return new LenovoG575Notebook();	
		default:
			throw new NotebookModelInvalidException("Error: Model not exist");
		}
	}

}
